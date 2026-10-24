package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.ejibarra.pathtoimmortals.network.ModMessages;
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

public class LevitationAbilityHandler {
    private static final float COST_FRACTION_PER_SEC = 0.05f; // 5%
    private static final int COOLDOWN_TICKS = 20 * 10;        // 10 seconds

    private static final String NBT_ACTIVE     = "pti_levitation_active";
    private static final String NBT_CD_END     = "pti_levitation_cd_end";
    private static final String NBT_NEXT_DRAIN = "pti_levitation_next_drain";

    // Remember prior flight state (so we restore properly)
    private static final String NBT_PREV_MAYFLY = "pti_levitation_prev_mayfly";
    private static final String NBT_PREV_FLYING = "pti_levitation_prev_flying";

    /** Call this on SERVER when the ability packet arrives. */
    public static void tryToggle(ServerPlayer player) {
        if (player == null || player.level() == null) return;
        if (player.isSpectator() || !player.isAlive()) return;

        // If they are already creative flight capable (creative), this is redundant.
        if (player.isCreative()) {
            player.displayClientMessage(
                    Component.literal("Levitation: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("You already have flight in Creative.")
                                    .withStyle(ChatFormatting.YELLOW)),
                    true
            );
            return;
        }

        long now = player.level().getGameTime();
        CompoundTag data = player.getPersistentData();

        boolean active = data.getBoolean(NBT_ACTIVE);

        // Toggle OFF
        if (active) {
            deactivate(player, "Levitation ended.");
            return;
        }

        // Cooldown gate
        long cdEnd = data.getLong(NBT_CD_END);
        if (cdEnd > now) {
            float secondsLeft = (cdEnd - now) / 20.0f;
            player.displayClientMessage(
                    Component.literal("Levitation: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal(String.format("%.1fs", secondsLeft))
                                    .withStyle(ChatFormatting.YELLOW))
                            .append(Component.literal(" remaining").withStyle(ChatFormatting.GOLD)),
                    true
            );
            return;
        }

        // Must afford at least the first second
        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(ess -> {
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

            // Mark active and schedule drain
            data.putBoolean(NBT_ACTIVE, true);
            data.putLong(NBT_NEXT_DRAIN, now + 20);

            // Save previous ability states (survival players generally false/false)
            data.putBoolean(NBT_PREV_MAYFLY, player.getAbilities().mayfly);
            data.putBoolean(NBT_PREV_FLYING, player.getAbilities().flying);

            // Enable flight now
            enableFlight(player);
            addStatusIcon(player, ModEffects.FLIGHT_ACTIVE.get());

            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.BEACON_POWER_SELECT, SoundSource.PLAYERS,
                    0.8f, 1.2f);

            player.displayClientMessage(
                    Component.literal("Levitation active!").withStyle(ChatFormatting.LIGHT_PURPLE),
                    true
            );
        });
    }

    /** Call this every SERVER tick from your existing AbilityCooldownTicker. */
    public static void serverTick(ServerPlayer player) {
        if (player == null || player.level() == null) return;

        // If they died/logged weirdly, force clean
        if (!player.isAlive() || player.isSpectator()) {
            forceOff(player);
            return;
        }

        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_ACTIVE)) return;

        // If they switched to creative while active, just stop managing it
        if (player.isCreative()) {
            // keep NBT clean
            data.putBoolean(NBT_ACTIVE, false);
            return;
        }

        long now = player.level().getGameTime();
        long nextDrain = data.getLong(NBT_NEXT_DRAIN);

        // Ensure flight stays enabled while active
        ensureFlightEnabled(player);
        // Ensure the HUD icon stays while active
        if (!player.hasEffect(ModEffects.FLIGHT_ACTIVE.get())) {
            addStatusIcon(player, ModEffects.FLIGHT_ACTIVE.get());
        }

        if (nextDrain > now) return;

        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(ess -> {
            int current = ess.getCurrent();
            int max = ess.getMax();

            int perSecondCost = computePerSecondCost(max);

            if (current < perSecondCost) {
                deactivate(player, "Levitation ended (not enough essence).");
                return;
            }

            // Spend and sync HUD
            ess.setCurrent(current - perSecondCost);
            ModMessages.sendToPlayer(new S2C_SyncOriginEssencePacket(ess.getCurrent(), max, ess.getUnlockedMask(), ess.getAbilityTiersCopy()), player);

            data.putLong(NBT_NEXT_DRAIN, now + 20);
        });
    }

    private static int computePerSecondCost(int maxEssence) {
        int cost = (int) Math.ceil(maxEssence * COST_FRACTION_PER_SEC);
        return Math.max(1, cost);
    }

    private static void enableFlight(ServerPlayer player) {
        player.getAbilities().mayfly = true;
        // optional: immediately start flying when toggled on
        // player.getAbilities().flying = true;

        player.onUpdateAbilities();
    }

    private static void ensureFlightEnabled(ServerPlayer player) {
        if (!player.getAbilities().mayfly) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    private static void deactivate(ServerPlayer player, String msg) {
        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_ACTIVE)) return;

        data.putBoolean(NBT_ACTIVE, false);
        removeStatusIcon(player, ModEffects.FLIGHT_ACTIVE.get());

        // Restore previous flight flags (but never break creative/spectator)
        boolean prevMayfly = data.getBoolean(NBT_PREV_MAYFLY);
        boolean prevFlying = data.getBoolean(NBT_PREV_FLYING);

        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().mayfly = prevMayfly;
            player.getAbilities().flying = prevFlying;

            // If we are turning flight off, force them out of flying state
            if (!player.getAbilities().mayfly) {
                player.getAbilities().flying = false;
            }

            player.onUpdateAbilities();
        }

        long now = player.level().getGameTime();
        data.putLong(NBT_CD_END, now + COOLDOWN_TICKS);

        player.level().playSound(null, player.blockPosition(),
                SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS,
                0.7f, 1.1f);

        player.displayClientMessage(
                Component.literal(msg).withStyle(ChatFormatting.GOLD),
                true
        );
    }

    /** Hard cleanup without starting cooldown (used for death/spectator edge cases) */
    public static void forceOff(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_ACTIVE)) return;

        data.putBoolean(NBT_ACTIVE, false);
        removeStatusIcon(player, ModEffects.FLIGHT_ACTIVE.get());

        if (!player.isCreative() && !player.isSpectator()) {
            boolean prevMayfly = data.getBoolean(NBT_PREV_MAYFLY);
            boolean prevFlying = data.getBoolean(NBT_PREV_FLYING);

            player.getAbilities().mayfly = prevMayfly;
            player.getAbilities().flying = prevFlying;

            if (!player.getAbilities().mayfly) player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }

    private static final int LONG_DURATION = 20 * 60 * 60 * 24; // 24 hours in ticks

    //Effects to show in HUD + Gameplay
    private static void addStatusIcon(ServerPlayer p, MobEffect effect) {
        // ambient=true makes it look like a passive buff
        // showParticles=false (no particles)
        // showIcon=true (we WANT icon)
        p.addEffect(new MobEffectInstance(effect, LONG_DURATION, 0, true, false, true));
    }

    private static void removeStatusIcon(ServerPlayer p, MobEffect effect) {
        p.removeEffect(effect);
    }
}
