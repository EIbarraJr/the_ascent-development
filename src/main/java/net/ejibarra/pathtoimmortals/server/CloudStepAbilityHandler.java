package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class CloudStepAbilityHandler {
    private static final String NBT_KEY = "pti_cloudstep_cd_end";

    private static final double MAX_DIST = 6.0;

    /** Call this on the SERVER when the ability packet arrives. */
    public static void tryActivate(ServerPlayer player) {
        if (player == null || player.level() == null) return;
        if (player.isSpectator() || !player.isAlive()) return;

        long now = player.level().getGameTime();

        // ===== Cooldown check (absolute end tick like Heal) =====
        CompoundTag data = player.getPersistentData();
        long cdEnd = data.getLong(NBT_KEY);
        if (cdEnd > now) {
            float secondsLeft = (cdEnd - now) / 20.0f;

            player.displayClientMessage(
                    Component.literal("Cloud Step: ")
                            .withStyle(ChatFormatting.GOLD)
                            .append(Component.literal(String.format("%.1fs", secondsLeft))
                                    .withStyle(ChatFormatting.YELLOW))
                            .append(Component.literal(" remaining").withStyle(ChatFormatting.GOLD)),
                    true
            );
            return;
        }

        // ===== Essence check + spend =====
        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(ess -> {
            int tier = AbilityScaling.clampTier(ess.getAbilityTier(AbilityType.CLOUD_STEP));
            if (tier < 1) return;

            int cost = AbilityScaling.cloudStepCost(tier);
            int cdTicks = AbilityScaling.cloudStepCooldown(tier);
            double maxDist = AbilityScaling.cloudStepMaxDistance(tier);
            int current = ess.getCurrent();
            int max = ess.getMax();

            if (current < cost) {
                int missing = cost - current;
                player.displayClientMessage(
                        Component.literal("Not enough Origin Essence! ")
                                .withStyle(ChatFormatting.RED)
                                .append(Component.literal("Missing: " + missing)
                                        .withStyle(ChatFormatting.RED)),
                        true
                );
                return;
            }

            // ===== Compute target based on aim (6 blocks max, shorter if block hit earlier) =====
            Vec3 start = player.getEyePosition();
            Vec3 look = player.getLookAngle();
            Vec3 end = start.add(look.scale(maxDist));

            BlockHitResult hit = player.level().clip(new ClipContext(
                    start, end,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    player
            ));

            Vec3 desired;
            if (hit.getType() == HitResult.Type.BLOCK) {
                // Move to just BEFORE the hit surface to avoid clipping into it
                Vec3 hitLoc = hit.getLocation();
                desired = hitLoc.add(look.scale(-0.35)); // step back slightly from the block
            } else {
                desired = end;
            }

            // Use player’s feet Y as baseline (keeps it feeling like a dash, not a fly)
            desired = new Vec3(desired.x, player.getY(), desired.z);

            Vec3 safe = findSafeTeleportPos(player.level(), player, desired);

            if (safe == null) {
                player.displayClientMessage(
                        Component.literal("Cloud Step failed: ")
                                .withStyle(ChatFormatting.RED)
                                .append(Component.literal("No safe space.")
                                        .withStyle(ChatFormatting.DARK_RED)),
                        true
                );
                return;
            }

            // ===== Spend + sync HUD =====
            ess.setCurrent(current - cost);
            ModMessages.sendToPlayer(new S2C_SyncOriginEssencePacket(ess.getCurrent(), max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()), player);

            // ===== Start cooldown =====
            data.putLong(NBT_KEY, now + cdTicks);

            // ===== FX: particles + sound =====
            if (player.level() instanceof ServerLevel sl) {
                // departure poof
                sl.sendParticles(ParticleTypes.CLOUD,
                        player.getX(), player.getY() + 0.2, player.getZ(),
                        20, 0.25, 0.2, 0.25, 0.02);
            }
            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS,
                    0.8f, 1.25f);

            // ===== Teleport =====
            player.teleportTo((ServerLevel) player.level(), safe.x, safe.y, safe.z, player.getYRot(), player.getXRot());

            if (player.level() instanceof ServerLevel sl) {
                // arrival poof
                sl.sendParticles(
                        ParticleTypes.CLOUD,
                        safe.x, safe.y + 0.2, safe.z,
                        20, 0.25, 0.2, 0.25, 0.02
                );
            }

            player.displayClientMessage(
                    Component.literal("Cloud Step!")
                            .withStyle(ChatFormatting.LIGHT_PURPLE),
                    true
            );
        });
    }

    /**
     * Find a safe spot near desired (not inside blocks; enough room for the player AABB).
     * We try desired, then a few small offsets, then small Y nudges.
     */
    private static Vec3 findSafeTeleportPos(Level level, ServerPlayer player, Vec3 desired) {
        // Snap to a reasonable center to avoid edge-collisions
        double x = desired.x;
        double y = desired.y;
        double z = desired.z;

        // offsets to try (horizontal)
        double[] dx = {0, 0.3, -0.3, 0.6, -0.6};
        double[] dz = {0, 0.3, -0.3, 0.6, -0.6};
        int[] dy = {0, 1, 2, -1};

        for (int oy : dy) {
            for (double ox : dx) {
                for (double oz : dz) {
                    Vec3 candidate = new Vec3(x + ox, y + oy, z + oz);

                    // Clamp to world build limits a bit
                    int minY = level.getMinBuildHeight();
                    int maxY = level.getMaxBuildHeight() - 2;
                    double cy = Mth.clamp(candidate.y, minY, maxY);

                    Vec3 c = new Vec3(candidate.x, cy, candidate.z);

                    if (isSafe(level, player, c)) {
                        return c;
                    }
                }
            }
        }

        return null;
    }

    private static boolean isSafe(Level level, ServerPlayer player, Vec3 pos) {
        // Player-sized box at target
        AABB box = player.getBoundingBox().move(
                pos.x - player.getX(),
                pos.y - player.getY(),
                pos.z - player.getZ()
        );

        // No collision with blocks
        if (!level.noCollision(player, box)) return false;

        // Avoid teleporting into void / too low; optional, but feels nicer
        BlockPos feet = BlockPos.containing(pos.x, pos.y, pos.z);
        if (feet.getY() <= level.getMinBuildHeight() + 1) return false;

        return true;
    }
}
