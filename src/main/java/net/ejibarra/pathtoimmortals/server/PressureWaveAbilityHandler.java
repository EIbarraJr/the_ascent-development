package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.custom.PressureWaveProjectile;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class PressureWaveAbilityHandler {
    private static final String NBT_KEY = "pti_pressure_wave_cd_end"; // stored as gameTime tick

    /** Call this on the SERVER when the keybind/ability packet arrives. */
    public static void tryCast(ServerPlayer player) {
        if (player == null || player.level() == null) return;

        long now = player.level().getGameTime();

        // ===== Cooldown check (with exact remaining time message) =====
        CompoundTag data = player.getPersistentData();
        long cdEnd = data.getLong(NBT_KEY);
        if (cdEnd > now) {
            long ticksLeft = cdEnd - now;

            // show as seconds with 1 decimal (e.g., 0.6s)
            float secondsLeft = ticksLeft / 20.0f;

            player.displayClientMessage(
                    Component.literal("Pressure Wave: ")
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
            int tier = ess.getAbilityTier(AbilityType.PRESSURE_WAVE);
            tier = AbilityScaling.clampTier(tier);
            if (tier < 1) return; // server safety

            int cost = AbilityScaling.pressureWaveCost(tier);
            int cdTicks = AbilityScaling.pressureWaveCooldown(tier);
            int current = ess.getCurrent();
            int max = ess.getMax();

            if (current < cost) {
                player.displayClientMessage(
                        Component.literal("Not enough Origin Essence! ")
                                .withStyle(ChatFormatting.RED)
                                .append(Component.literal("(" + current + "/" + cost + ")")
                                        .withStyle(ChatFormatting.DARK_RED)),
                        true
                );
                return;
            }

            // spend + sync HUD
            ess.setCurrent(current - cost);
            ModMessages.sendToPlayer(new S2C_SyncOriginEssencePacket(ess.getCurrent(), max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()), player);

            // set cooldown end tick
            data.putLong(NBT_KEY, now + cdTicks);

            // ===== Spawn projectile =====
            PressureWaveProjectile proj = new PressureWaveProjectile(ModEntities.PRESSURE_WAVE.get(), player.level());
            proj.setOwner(player);

            proj.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            proj.setDamageMultiplier(AbilityScaling.pressureWaveDamageMult(tier));

            Vec3 look = player.getLookAngle();
            proj.shoot(look.x, look.y, look.z, 2.0f, 0.0f); // speed/inaccuracy

            player.level().addFreshEntity(proj);

            // ===== Spawn projectile(s) (tiered count) ===== Future?
            /*int count = AbilityScaling.pressureWaveProjectileCount(tier);
            if (count < 1) count = 1;

            Vec3 look = player.getLookAngle();
            float spreadDeg = (count <= 1) ? 0f : 10f;

            for (int i = 0; i < count; i++) {
                float yawOffset = (count == 1) ? 0f : (i - (count - 1) / 2f) * spreadDeg;

                PressureWaveProjectile proj = new PressureWaveProjectile(ModEntities.PRESSURE_WAVE.get(), player.level());
                proj.setOwner(player);
                proj.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

                // Optional: if you add a setter on projectile later
                // proj.setDamageMultiplier(AbilityScaling.pressureWaveDamageMult(tier));

                Vec3 dir = look.yRot((float) Math.toRadians(-yawOffset));
                proj.shoot(dir.x, dir.y, dir.z, 2.0f, 0.0f);

                player.level().addFreshEntity(proj);
            }*/

            // ===== Activated message =====
            player.displayClientMessage(
                    Component.literal("Pressure Wave activated! (Tier " + tier + ")")
                            .withStyle(ChatFormatting.LIGHT_PURPLE),
                    true
            );
        });
    }
}
