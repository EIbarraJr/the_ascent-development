package net.ejibarra.pathtoimmortals.server;

import net.ejibarra.pathtoimmortals.network.packet.AbilityType;

public class AbilityScaling {
    private AbilityScaling() {}

    public static final int MAX_TIER = 4;

    /** Clamp to [0..4] so you never crash on bad data or future changes. */
    public static int clampTier(int tier) {
        if (tier < 0) return 0;
        if (tier > MAX_TIER) return MAX_TIER;
        return tier;
    }

    /** Pressure Wave: cost/cd + later you can add damage/projectiles. */
    public static int pressureWaveCost(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 50;
            case 2 -> 100;
            case 3 -> 250;
            case 4 -> 450;
            default -> Integer.MAX_VALUE; // locked tier 0
        };
    }

    /** Pressure Wave: cooldown in ticks */
    public static int pressureWaveCooldown(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 20; // 1.0s (your current)
            case 2 -> 16; // 0.8s
            case 3 -> 12; // 0.6s
            case 4 -> 8; // 0.4s
            default -> Integer.MAX_VALUE;
        };
    }

    /** Optional future hooks (leave for later) */
    public static float pressureWaveDamageMult(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 1.00f;
            case 2 -> 1.10f;
            case 3 -> 1.20f;
            case 4 -> 1.35f;
            default -> 0.0f;
        };
    }

    /** Pressure Wave: cost/cd + later you can add damage/projectiles. */
    public static int originSwordCost(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 200;
            case 2 -> 500;
            case 3 -> 1250;
            case 4 -> 2500;
            default -> Integer.MAX_VALUE; // locked tier 0
        };
    }

    /** Pressure Wave: cooldown in ticks */
    public static int originSwordCooldown(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 20 * 10; // 10.0s (your current)
            case 2 -> 20 * 8; // 8s
            case 3 -> 20 * 6; // 6s
            case 4 -> 20 * 5; // 5s
            default -> Integer.MAX_VALUE;
        };
    }

    /** Optional future hooks (leave for later) */
    public static float originSwordDamageMult(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 1.00f;
            case 2 -> 2.00f;
            case 3 -> 3.00f;
            case 4 -> 4.00f;
            default -> 0.0f;
        };
    }

    /** Overcharged/Heal: cooldown-only scaling (ticks). */
    public static int healCooldownOnly(int tierRaw) {
        int tier = clampTier(tierRaw);
        // Tier 1 = base. Tier 4 = noticeably shorter.
        // Adjust these to taste.
        return switch (tier) {
            case 1 -> 30;
            case 2 -> 24;
            case 3 -> 18;
            case 4 -> 12;
            default -> Integer.MAX_VALUE;
        };
    }

    /** Overcharged/Heal: cooldown-only scaling (ticks). */
    public static int overchargedCooldownOnly( int tierRaw) {
        int tier = clampTier(tierRaw);
        // Tier 1 = base. Tier 4 = noticeably shorter.
        // Adjust these to taste.
        return switch (tier) {
            case 1 -> 20 * 60;
            case 2 -> 20 * 45;
            case 3 -> 20 * 30;
            case 4 -> 20 * 15;
            default -> Integer.MAX_VALUE;
        };
    }

    public static int pressureWaveProjectileCount(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 1;
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            default -> 0;
        };
    }

    public static int originSwordProjectileCount(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 1;
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            default -> 0;
        };
    }

    public static int cloudStepCost(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 100;
            case 2 -> 250;
            case 3 -> 500;
            case 4 -> 750;
            default -> Integer.MAX_VALUE;
        };
    }

    public static int cloudStepCooldown(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 20 * 5;  // 5s (your current baseline)
            case 2 -> 20 * 4;  // 4s
            case 3 -> 20 * 3;  // 3s
            case 4 -> 20 * 1;  // 2s
            default -> Integer.MAX_VALUE;
        };
    }

    public static double cloudStepMaxDistance(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 6.0D;   // your current MAX_DIST
            case 2 -> 8.0D;
            case 3 -> 12.0D;
            case 4 -> 16.0D;
            default -> 0.0D;
        };
    }

    public static int reinforcedCooldown(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 20 * 90; // your current 90s baseline
            case 2 -> 20 * 75;
            case 3 -> 20 * 60;
            case 4 -> 20 * 45;
            default -> Integer.MAX_VALUE;
        };
    }

    public static double reinforcedAttackBonus(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 6.0;
            case 2 -> 12.0;
            case 3 -> 18.0;
            case 4 -> 24.0;
            default -> 0.0;
        };
    }

    public static double reinforcedArmorBonus(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 8.0;
            case 2 -> 12.0;
            case 3 -> 16.0;
            case 4 -> 20.0;
            default -> 0.0;
        };
    }

    public static double reinforcedToughnessBonus(int tierRaw) {
        int tier = clampTier(tierRaw);
        return switch (tier) {
            case 1 -> 2.0;
            case 2 -> 4.0;
            case 3 -> 6.0;
            case 4 -> 8.0;
            default -> 0.0;
        };
    }

}
