package net.ejibarra.pathtoimmortals.client;

import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.server.AbilityScaling;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AbilityWheelInfo {
    private AbilityWheelInfo() {}

    public static List<Component> tooltip(AbilityType type, String displayName, int tier) {
        List<Component> lines = new ArrayList<>();

        // Title line
        if (tier < 1) {
            lines.add(Component.literal(displayName + " (Locked)").withStyle(ChatFormatting.RED));
            lines.add(Component.literal("Use a scroll to unlock.").withStyle(ChatFormatting.GRAY));
            return lines;
        }

        lines.add(Component.literal(displayName + "  Tier " + tier).withStyle(ChatFormatting.LIGHT_PURPLE));

        switch (type) {
            case PRESSURE_WAVE -> {
                int cost = AbilityScaling.pressureWaveCost(tier);
                int cd = AbilityScaling.pressureWaveCooldown(tier);
                int count = AbilityScaling.pressureWaveProjectileCount(tier);
                float dmgMul = AbilityScaling.pressureWaveDamageMult(tier);

                lines.add(kv("Cost", cost + " essence"));
                lines.add(kv("Cooldown", fmtSeconds(cd)));
                lines.add(kv("Projectiles", String.valueOf(count)));
                lines.add(kv("Damage", String.format(Locale.ROOT, "%.2fx", dmgMul)));
            }

            case ORIGIN_SWORD -> {
                int cost = AbilityScaling.originSwordCost(tier);
                int cd = AbilityScaling.originSwordCooldown(tier);
                int count = AbilityScaling.originSwordProjectileCount(tier);
                float dmgMul = AbilityScaling.originSwordDamageMult(tier);

                lines.add(kv("Cost", cost + " essence"));
                lines.add(kv("Cooldown", fmtSeconds(cd)));
                lines.add(kv("Projectiles", String.valueOf(count)));
                lines.add(kv("Damage", String.format(Locale.ROOT, "%.2fx", dmgMul)));
            }

            case OVERCHARGED -> {
                int cd = AbilityScaling.overchargedCooldownOnly(tier);
                lines.add(kv("Cooldown", fmtSeconds(cd)));
                // Your ability is % based, so show rule not a fake number
                lines.add(kv("Cost", "25% of max essence"));
                lines.add(kv("Damage", "15% of max essence (AoE)"));
                lines.add(kv("Radius", "3.0 blocks"));
            }

            case HEAL -> {
                int cd = AbilityScaling.healCooldownOnly(tier);
                lines.add(kv("Cooldown", fmtSeconds(cd)));
                // If you later add heal amount scaling, add it here
                lines.add(Component.literal("Heals you (amount handled server-side).").withStyle(ChatFormatting.GRAY));
            }

            case REINFORCED -> {
                int cd = AbilityScaling.reinforcedCooldown(tier);
                double atk = AbilityScaling.reinforcedAttackBonus(tier);
                double arm = AbilityScaling.reinforcedArmorBonus(tier);
                double tough = AbilityScaling.reinforcedToughnessBonus(tier);

                lines.add(kv("Cooldown", fmtSeconds(cd)));
                lines.add(kv("Drain", "4.5% max essence / sec"));
                lines.add(kv("Attack", "+" + trim0(atk)));
                lines.add(kv("Armor", "+" + trim0(arm)));
                lines.add(kv("Toughness", "+" + trim0(tough)));
            }

            case CLOUD_STEP -> {
                int cost = AbilityScaling.cloudStepCost(tier);
                int cd = AbilityScaling.cloudStepCooldown(tier);
                double dist = AbilityScaling.cloudStepMaxDistance(tier);

                lines.add(kv("Cost", cost + " essence"));
                lines.add(kv("Cooldown", fmtSeconds(cd)));
                lines.add(kv("Distance", String.format(Locale.ROOT, "%.1f blocks", dist)));
            }

            case LEVITATION -> {
                lines.add(Component.literal("No tier scaling.").withStyle(ChatFormatting.GRAY));
            }
        }

        return lines;
    }

    private static Component kv(String key, String val) {
        return Component.literal(key + ": ").withStyle(ChatFormatting.GOLD)
                .append(Component.literal(val).withStyle(ChatFormatting.WHITE));
    }

    private static String fmtSeconds(int ticks) {
        if (ticks == Integer.MAX_VALUE) return "-";
        double sec = ticks / 20.0;
        if (sec < 10.0) return String.format(Locale.ROOT, "%.1fs", sec);
        return String.format(Locale.ROOT, "%.0fs", sec);
    }

    private static String trim0(double d) {
        long r = Math.round(d);
        if (Math.abs(d - r) < 1e-9) return Long.toString(r);
        return String.format(Locale.ROOT, "%.2f", d);
    }
}
