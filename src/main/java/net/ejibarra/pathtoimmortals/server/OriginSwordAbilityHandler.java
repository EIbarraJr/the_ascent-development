package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.entity.custom.OriginSwordProjectileEntity;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.origin.OriginEssence;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class OriginSwordAbilityHandler {
    public static final String NBT_COOLDOWN = "origin_sword_cooldown";

    public static void tryUseAbility(ServerPlayer player) {
        CompoundTag tag = player.getPersistentData();

        // === 1. Check cooldown ===
        int cd = tag.getInt(NBT_COOLDOWN);
        if (cd > 0) {
            int secondsLeft = (cd + 19) / 20;
            player.displayClientMessage(
                    Component.literal("§cAbility on cooldown (" + secondsLeft + "s left)"),
                    true
            );
            return;
        }

        // === 2. Access your capability ===
        OriginEssence cap = OriginEssenceCapability.OriginEssenceHelper.get(player);
        int tier = AbilityScaling.clampTier(cap.getAbilityTier(AbilityType.ORIGIN_SWORD));
        if (tier < 1) return;

        int cost = AbilityScaling.originSwordCost(tier);
        int cooldownTicks = AbilityScaling.originSwordCooldown(tier);

        int current = cap.getCurrent();

            // --- 2a. Check cost ---
            if (current < cost) {
                player.displayClientMessage(
                        Component.literal("§cNot enough Origin Essence! Requires " + cost + "."),
                        true
                );
                return;
            }

            // --- 2b. Spend essence ---
            cap.setCurrent(current - cost); // or ess.add(-ESSENCE_COST);

            // 🔥 This is critical for HUD updating:
            ModMessages.syncOriginEssence(player, cap);

            // --- 3. Spawn projectile ---
            if (player.level() instanceof ServerLevel serverLevel) {
                float dmgMult = AbilityScaling.originSwordDamageMult(tier);

                OriginSwordProjectileEntity.shootFromPlayer(serverLevel, player, dmgMult);
                // Later, if you want tier projectile count:
                // int count = AbilityScaling.originSwordProjectileCount(tier);
                // loop spawn
            }

            // --- 4. Apply cooldown (on player NBT, not the capability) ---
            tag.putInt(NBT_COOLDOWN, cooldownTicks);

            player.displayClientMessage(
                    Component.literal("§dOrigin Sword Ability Activated!"),
                    true
            );
    }
}
