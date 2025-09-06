package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.DaoCoreProjectile;
import net.ejibarra.pathtoimmortals.entity.custom.DarklinEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class CastProjectileGoal extends Goal {
    private final DarklinEntity darklin;
    private final double maxLosRange;       // e.g., 64.0 (ghast uses ~64)
    private final double stopAggroRange;    // your hard stop, e.g., 49.0
    private final int chargeUpTicks;        // e.g., 20 (1s)
    private final int cooldownMin;          // e.g., 40 (2s)
    private final int cooldownMax;          // e.g., 80 (4s)

    private int chargeTicks;                // how long we’ve been charging
    private int cooldownTicks;              // time until next possible shot

    public CastProjectileGoal(DarklinEntity darklin,
                                     double maxLosRange,
                                     double stopAggroRange,
                                     int chargeUpTicks,
                                     int cooldownMin,
                                     int cooldownMax) {
        this.darklin = darklin;
        this.maxLosRange = maxLosRange;
        this.stopAggroRange = stopAggroRange;
        this.chargeUpTicks = chargeUpTicks;
        this.cooldownMin = cooldownMin;
        this.cooldownMax = cooldownMax;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity t = darklin.getTarget();
        if (t == null || !t.isAlive()) return false;

        double d2 = darklin.distanceToSqr(t);
        if (d2 > maxLosRange * maxLosRange) return false;

        // Ghast requires LOS to start/keep charging
        return darklin.hasLineOfSight(t);
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity t = darklin.getTarget();
        if (t == null || !t.isAlive()) return false;

        // Your “lose aggro at ≥49 m” rule
        if (darklin.distanceToSqr(t) >= stopAggroRange * stopAggroRange) return false;

        // Keep if we still can see them (ghast logic)
        return darklin.hasLineOfSight(t);
    }

    @Override
    public void start() {
        chargeTicks = 0;
        // Start with random cooldown so groups desync a bit
        cooldownTicks = darklin.getRandom().nextIntBetweenInclusive(cooldownMin, cooldownMax);
        darklin.setCharging(false);
        darklin.getNavigation().stop(); // ghast stands/hovers still
    }

    @Override
    public void stop() {
        darklin.setCharging(false);
        chargeTicks = 0;
    }

    @Override
    public void tick() {
        LivingEntity t = darklin.getTarget();
        if (t == null) return;

        // Always look at the target (ghast turns its face)
        darklin.getLookControl().setLookAt(t, 30.0F, 30.0F);

        // If we can’t see them, reset charging but let cooldown tick
        boolean hasLOS = darklin.hasLineOfSight(t);

        // Handle cooldown first (ghast can’t fire every tick)
        if (cooldownTicks > 0) {
            cooldownTicks--;
            // Not charging during cooldown
            darklin.setCharging(false);
            chargeTicks = 0;
            return;
        }

        // Charge up while we can see the target
        if (hasLOS) {
            darklin.setCharging(true);
            chargeTicks++;

            if (chargeTicks >= chargeUpTicks) {
                // FIRE (mirror ghast’s “shoot once when fully charged”)
                fireOnce(t);

                // Reset to cooldown state
                cooldownTicks = darklin.getRandom().nextIntBetweenInclusive(cooldownMin, cooldownMax);
                chargeTicks = 0;
                darklin.setCharging(false);
            }
        } else {
            // Lost sight: drop charge
            darklin.setCharging(false);
            chargeTicks = 0;
        }
    }

    private void fireOnce(LivingEntity target) {
        if (darklin.level().isClientSide) return;

        // Ghast computes a vector to target and launches a large fireball.
        // We’ll just delegate to your ranged attack hook for clarity:
        float distFactor = (float)(Math.sqrt(darklin.distanceToSqr(target)) / maxLosRange);
        distFactor = net.minecraft.util.Mth.clamp(distFactor, 0.1F, 1.0F);

        darklin.performRangedAttack(target, distFactor);

        // Optional: ghast plays a shoot sound here
        // mob.playSound(SoundEvents.GHAST_SHOOT, 1.0F, 1.0F);
    }
}
