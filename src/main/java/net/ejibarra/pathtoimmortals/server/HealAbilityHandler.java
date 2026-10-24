package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.S2C_OverchargedBurstPacket;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class HealAbilityHandler {
    private static final int COOLDOWN_TICKS = 20 * 30; // 30 seconds
    private static final String NBT_KEY = "pti_heal_cd_end";

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
                    Component.literal("Heal: ")
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
            int tier = ess.getAbilityTier(AbilityType.HEAL);
            if (tier < 1) return;

            int cooldownTicks = AbilityScaling.healCooldownOnly(tier);
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

            // Spend + sync HUD
            ess.setCurrent(current - cost);
            ModMessages.sendToPlayer(new S2C_SyncOriginEssencePacket(ess.getCurrent(), max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()), player);

            // Set cooldown end tick
            data.putLong(NBT_KEY, now + cooldownTicks);

            //Heal 50% of max health
            float maxHp = player.getMaxHealth();
            float healAmount = maxHp * 0.60f;

            float before = player.getHealth();
            player.heal(healAmount);
            float after = player.getHealth();

            int healed = Math.max(0, Math.round(after - before));

            //Particles + sound
            player.level().playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.9f, 1.2f);
            if (player.level() instanceof ServerLevel sl){
                sl.sendParticles(ParticleTypes.HEART, player.getX(), player.getY() + 1, player.getZ(), 20, 0.6, 0.8, 0.6, 0.1);
            }

            player.displayClientMessage(
                    Component.literal("Heal activated!")
                            .withStyle(ChatFormatting.LIGHT_PURPLE).append(Component.literal("(+" + healed + " HP)").withStyle(ChatFormatting.LIGHT_PURPLE)),
                    true
            );
        });
    }
}
