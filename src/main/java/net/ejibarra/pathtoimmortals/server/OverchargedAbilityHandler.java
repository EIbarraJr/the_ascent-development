package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.S2C_OverchargedBurstPacket;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class OverchargedAbilityHandler {
    //private static final int COOLDOWN_TICKS = 20 * 60; // 1 minutes = 1200
    private static final double RADIUS = 3.0D;
    private static final String NBT_KEY = "pti_overcharge_cd_end";

    /** Call this on the SERVER when the keybind/ability packet arrives. */
    public static void tryActivate(ServerPlayer player) {
        if (player == null || player.level() == null) return;
        if (player.isSpectator() || !player.isAlive()) return;

        long now = player.level().getGameTime();

        // ===== Cooldown check (with exact remaining time message) =====
        CompoundTag data = player.getPersistentData();
        long cdEnd = data.getLong(NBT_KEY);
        if (cdEnd > now) {
            long ticksLeft = cdEnd - now;
            float secondsLeft = ticksLeft / 20.0f;

            player.displayClientMessage(
                    Component.literal("Overcharge: ")
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
            int tier = ess.getAbilityTier(AbilityType.OVERCHARGED);
            if (tier < 1) return;

            int cooldownTicks = AbilityScaling.overchargedCooldownOnly(tier);
            int current = ess.getCurrent();
            int max = ess.getMax();

            // Cost = EXACTLY 20% of MAX essence
            int cost = (int) Math.ceil(max * 0.20D);
            if (cost <= 0) cost = 1;

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

            // Damage = 15% of MAX essence
            float damage = (float) (max * 0.15D);
            if (damage < 1.0F) damage = 1.0F;

            // Spend + sync HUD
            ess.setCurrent(current - cost);
            ModMessages.sendToPlayer(new S2C_SyncOriginEssencePacket(ess.getCurrent(), max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()), player);

            // Set cooldown end tick
            data.putLong(NBT_KEY, now + cooldownTicks);

            // Visual burst (client-side velocity particles)
            ModMessages.sendToTrackingAndSelf(new S2C_OverchargedBurstPacket(player.getId()), player);

            // ===== Apply AoE push + damage =====
            AABB box = player.getBoundingBox().inflate(RADIUS);
            List<LivingEntity> targets = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    box,
                    e -> e.isAlive() && e != player && !e.isAlliedTo(player)
            );

            DamageSource src = player.damageSources().playerAttack(player);

            for (LivingEntity e : targets) {
                double dx = e.getX() - player.getX();
                double dz = e.getZ() - player.getZ();
                double dist = Math.sqrt(dx * dx + dz * dz);
                if (dist < 1.0E-4) dist = 1.0;

                dx /= dist;
                dz /= dist;

                double horiz = 1.8D;
                double yBoost = 0.55D;

                e.push(dx * horiz, yBoost, dz * horiz);
                e.hurtMarked = true;

                e.hurt(src, damage);
            }

            // Sound + Activated message
            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);

            player.displayClientMessage(
                    Component.literal("Overcharge activated!")
                            .withStyle(ChatFormatting.LIGHT_PURPLE),
                    true
            );
        });
    }
}
