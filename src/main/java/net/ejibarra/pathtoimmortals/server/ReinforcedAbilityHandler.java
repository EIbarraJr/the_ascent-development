package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class ReinforcedAbilityHandler {
    // ===== Cost =====
    private static final float COST_FRACTION_PER_SEC = 0.045f; // 4.5%

    // ===== NBT keys =====
    private static final String NBT_ACTIVE = "pti_reinforce_active";
    private static final String NBT_CD_END = "pti_reinforce_cd_end";
    private static final String NBT_NEXT_DRAIN = "pti_reinforce_next_drain";

    // ===== Attribute modifier UUIDs (must be stable) =====
    private static final UUID MOD_ATTACK_UUID  = UUID.fromString("9f3c2f3b-1c36-4a2b-9b20-8f3c2a6c7c01");
    private static final UUID MOD_ARMOR_UUID   = UUID.fromString("9f3c2f3b-1c36-4a2b-9b20-8f3c2a6c7c02");
    private static final UUID MOD_TOUGH_UUID   = UUID.fromString("9f3c2f3b-1c36-4a2b-9b20-8f3c2a6c7c03");

    /** Called on SERVER when the ability keybind packet arrives. */
    public static void tryToggle(ServerPlayer player) {
        if (player == null || player.level() == null) return;
        if (player.isSpectator() || !player.isAlive()) return;

        long now = player.level().getGameTime();
        CompoundTag data = player.getPersistentData();

        boolean active = data.getBoolean(NBT_ACTIVE);

        // If active -> turn OFF
        if (active) {
            deactivate(player, "Reinforce ended.");
            return;
        }

        // If not active -> cooldown gate
        long cdEnd = data.getLong(NBT_CD_END);
        if (cdEnd > now) {
            float secondsLeft = (cdEnd - now) / 20.0f;
            player.displayClientMessage(
                    Component.literal("Reinforce: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal(String.format("%.1fs", secondsLeft)).withStyle(ChatFormatting.YELLOW))
                            .append(Component.literal(" remaining").withStyle(ChatFormatting.GOLD)),
                    true
            );
            return;
        }

        // Try activate (needs enough essence for at least the first second)
        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(ess -> {
            int tier = AbilityScaling.clampTier(ess.getAbilityTier(AbilityType.REINFORCED));
            if (tier < 1) return;

            int current = ess.getCurrent();
            int max = ess.getMax();

            int perSecondCost = computePerSecondCost(max);
            if (current < perSecondCost) {
                int missing = perSecondCost - current;
                player.displayClientMessage(
                        Component.literal("Not enough Origin Essence! ").withStyle(ChatFormatting.RED)
                                .append(Component.literal("Missing: " + missing).withStyle(ChatFormatting.RED)),
                        true
                );
                return;
            }

            // Mark active + schedule first drain (1s from now)
            data.putBoolean(NBT_ACTIVE, true);
            data.putLong(NBT_NEXT_DRAIN, now + 20);

            applyModifiers(player, tier);
            addStatusIcon(player, ModEffects.REINFORCED.get());

            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.BEACON_POWER_SELECT, SoundSource.PLAYERS,
                    0.8f, 1.2f);

            player.displayClientMessage(
                    Component.literal("Reinforce active! (Tier " + tier + ")").withStyle(ChatFormatting.LIGHT_PURPLE),
                    true
            );

            ModMessages.sendToPlayer(
                    new S2C_SyncOriginEssencePacket(current, max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()),
                    player
            );
        });
    }

    /** Call this every SERVER tick. */
    public static void serverTick(ServerPlayer player) {
        if (player == null || player.level() == null) return;
        if (player.isSpectator() || !player.isAlive()) {
            forceOff(player);
            return;
        }

        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_ACTIVE)) return;

        long now = player.level().getGameTime();
        long nextDrain = data.getLong(NBT_NEXT_DRAIN);

        // Ensure tiered modifiers remain applied (in case something removed them)
        // We need the tier to know the correct modifier values.
        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(ess -> {
            int tier = AbilityScaling.clampTier(ess.getAbilityTier(AbilityType.REINFORCED));
            if (tier < 1) {
                deactivate(player, "Reinforce ended (tier lost).");
                return;
            }

            ensureModifiers(player, tier);

            // Ensures the effect stays in event of clearing with milk
            if (!player.hasEffect(ModEffects.REINFORCED.get())) {
                addStatusIcon(player, ModEffects.REINFORCED.get());
            }

            if (nextDrain > now) return;

            int current = ess.getCurrent();
            int max = ess.getMax();
            int perSecondCost = computePerSecondCost(max);

            if (current < perSecondCost) {
                deactivate(player, "Reinforce ended (not enough essence).");
                return;
            }

            // Spend and reschedule
            ess.setCurrent(current - perSecondCost);
            ModMessages.sendToPlayer(
                    new S2C_SyncOriginEssencePacket(ess.getCurrent(), max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()),
                    player
            );

            data.putLong(NBT_NEXT_DRAIN, now + 20);
        });
    }

    private static int computePerSecondCost(int maxEssence) {
        int cost = (int) Math.ceil(maxEssence * COST_FRACTION_PER_SEC);
        return Math.max(1, cost);
    }

    private static void deactivate(ServerPlayer player, String msg) {
        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_ACTIVE)) return;

        data.putBoolean(NBT_ACTIVE, false);
        removeModifiers(player);
        removeStatusIcon(player, ModEffects.REINFORCED.get());

        long now = player.level().getGameTime();

        // Tier-based cooldown: read tier at deactivation time (fallback tier=1 if missing)
        int tier = 1;
        try {
            tier = AbilityScaling.clampTier(OriginEssenceCapability.OriginEssenceHelper.get(player).getAbilityTier(AbilityType.REINFORCED));
            if (tier < 1) tier = 1;
        } catch (Exception ignored) {}

        int cdTicks = AbilityScaling.reinforcedCooldown(tier);
        data.putLong(NBT_CD_END, now + cdTicks);

        player.level().playSound(null, player.blockPosition(),
                SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS,
                0.7f, 1.1f);

        player.displayClientMessage(
                Component.literal(msg).withStyle(ChatFormatting.GOLD),
                true
        );
    }

    private static void forceOff(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_ACTIVE)) return;
        data.putBoolean(NBT_ACTIVE, false);
        removeModifiers(player);
    }

    // ===== Tiered modifiers =====

    private static AttributeModifier modAttackForTier(int tier) {
        return new AttributeModifier(MOD_ATTACK_UUID, "pti_reinforce_attack",
                AbilityScaling.reinforcedAttackBonus(tier), AttributeModifier.Operation.ADDITION);
    }

    private static AttributeModifier modArmorForTier(int tier) {
        return new AttributeModifier(MOD_ARMOR_UUID, "pti_reinforce_armor",
                AbilityScaling.reinforcedArmorBonus(tier), AttributeModifier.Operation.ADDITION);
    }

    private static AttributeModifier modToughForTier(int tier) {
        return new AttributeModifier(MOD_TOUGH_UUID, "pti_reinforce_toughness",
                AbilityScaling.reinforcedToughnessBonus(tier), AttributeModifier.Operation.ADDITION);
    }

    private static void applyModifiers(ServerPlayer player, int tier) {
        addTransient(player.getAttribute(Attributes.ATTACK_DAMAGE), modAttackForTier(tier));
        addTransient(player.getAttribute(Attributes.ARMOR), modArmorForTier(tier));
        addTransient(player.getAttribute(Attributes.ARMOR_TOUGHNESS), modToughForTier(tier));
    }

    private static void ensureModifiers(ServerPlayer player, int tier) {
        ensureTransient(player.getAttribute(Attributes.ATTACK_DAMAGE), MOD_ATTACK_UUID, modAttackForTier(tier));
        ensureTransient(player.getAttribute(Attributes.ARMOR), MOD_ARMOR_UUID, modArmorForTier(tier));
        ensureTransient(player.getAttribute(Attributes.ARMOR_TOUGHNESS), MOD_TOUGH_UUID, modToughForTier(tier));
    }

    private static void removeModifiers(ServerPlayer player) {
        removeByUuid(player.getAttribute(Attributes.ATTACK_DAMAGE), MOD_ATTACK_UUID);
        removeByUuid(player.getAttribute(Attributes.ARMOR), MOD_ARMOR_UUID);
        removeByUuid(player.getAttribute(Attributes.ARMOR_TOUGHNESS), MOD_TOUGH_UUID);
    }

    private static void addTransient(AttributeInstance inst, AttributeModifier mod) {
        if (inst == null) return;
        inst.removeModifier(mod.getId());
        inst.addTransientModifier(mod);
    }

    private static void ensureTransient(AttributeInstance inst, UUID id, AttributeModifier mod) {
        if (inst == null) return;
        if (inst.getModifier(id) == null) {
            inst.addTransientModifier(mod);
        }
    }

    private static void removeByUuid(AttributeInstance inst, UUID id) {
        if (inst == null) return;
        inst.removeModifier(id);
    }

    // ===== HUD effect icon =====
    private static final int LONG_DURATION = 20 * 60 * 60 * 24; // 24 hours in ticks

    private static void addStatusIcon(ServerPlayer p, MobEffect effect) {
        p.addEffect(new MobEffectInstance(effect, LONG_DURATION, 0, true, false, true));
    }

    private static void removeStatusIcon(ServerPlayer p, MobEffect effect) {
        p.removeEffect(effect);
    }
}
