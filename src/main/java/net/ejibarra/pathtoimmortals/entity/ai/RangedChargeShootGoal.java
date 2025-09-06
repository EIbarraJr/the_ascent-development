package net.ejibarra.pathtoimmortals.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;

import java.util.EnumSet;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class RangedChargeShootGoal <T extends PathfinderMob & RangedAttackMob> extends Goal {
    private final T mob;

    // Config
    private final double maxLosRangeSqr;
    private final double stopAggroRangeSqr;
    private final int chargeUpTicks;
    private final int cooldownMin;
    private final int cooldownMax;
    private final boolean requireLOS;
    private final boolean stopNavigationWhileFiring;
    private final BooleanSupplier extraCanUse; // optional extra gating (e.g., time of day, phase, etc.)

    // 🔧 NEW: optional animation hook
    private final Consumer<Boolean> onCharging; // may be null

    // State
    private int chargeTicks;
    private int cooldownTicks;

    public static class Builder<U extends PathfinderMob & RangedAttackMob> {
        private final U mob;
        private double maxLosRange = 64.0;
        private double stopAggroRange = 49.0;
        private int chargeUpTicks = 20;
        private int cooldownMin = 40;
        private int cooldownMax = 80;
        private boolean requireLOS = true;
        private boolean stopNavigationWhileFiring = true;
        private BooleanSupplier extraCanUse = () -> true;
        private Consumer<Boolean> onCharging = null; // 🔧 NEW

        public Builder(U mob) { this.mob = mob; }

        public Builder<U> maxLosRange(double v) { this.maxLosRange = v; return this; }
        public Builder<U> stopAggroRange(double v) { this.stopAggroRange = v; return this; }
        public Builder<U> chargeUpTicks(int v) { this.chargeUpTicks = v; return this; }
        public Builder<U> cooldown(int min, int max) { this.cooldownMin = min; this.cooldownMax = max; return this; }
        public Builder<U> requireLOS(boolean v) { this.requireLOS = v; return this; }
        public Builder<U> stopNavigationWhileFiring(boolean v) { this.stopNavigationWhileFiring = v; return this; }
        public Builder<U> extraCanUse(BooleanSupplier fn) { this.extraCanUse = fn; return this; }

        /** 🔧 Pass an animation setter, e.g. `this::setCharging` inside your entity */
        public Builder<U> onCharging(Consumer<Boolean> setter){ this.onCharging = setter; return this; }


        public RangedChargeShootGoal<U> build() {
            return new RangedChargeShootGoal<>(mob, maxLosRange, stopAggroRange, chargeUpTicks,
                    cooldownMin, cooldownMax, requireLOS, stopNavigationWhileFiring, extraCanUse, onCharging);
        }
    }

    public RangedChargeShootGoal(T mob,
                                        double maxLosRange,
                                        double stopAggroRange,
                                        int chargeUpTicks,
                                        int cooldownMin,
                                        int cooldownMax,
                                        boolean requireLOS,
                                        boolean stopNavigationWhileFiring,
                                        BooleanSupplier extraCanUse, Consumer<Boolean> onCharging) {
        this.mob = mob;
        this.maxLosRangeSqr = maxLosRange * maxLosRange;
        this.stopAggroRangeSqr = stopAggroRange * stopAggroRange;
        this.chargeUpTicks = chargeUpTicks;
        this.cooldownMin = cooldownMin;
        this.cooldownMax = cooldownMax;
        this.requireLOS = requireLOS;
        this.stopNavigationWhileFiring = stopNavigationWhileFiring;
        this.extraCanUse = extraCanUse != null ? extraCanUse : () -> true;
        this.onCharging = onCharging; // may be null
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!extraCanUse.getAsBoolean()) return false;
        LivingEntity t = mob.getTarget();
        if (t == null || !t.isAlive()) return false;

        double d2 = mob.distanceToSqr(t);
        if (d2 > maxLosRangeSqr) return false;

        return !requireLOS || mob.hasLineOfSight(t);
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity t = mob.getTarget();
        if (t == null || !t.isAlive()) return false;

        if (mob.distanceToSqr(t) >= stopAggroRangeSqr) return false;
        return !requireLOS || mob.hasLineOfSight(t);
    }

    @Override
    public void start() {
        chargeTicks = 0;
        cooldownTicks = mob.getRandom().nextIntBetweenInclusive(cooldownMin, cooldownMax);
        if (stopNavigationWhileFiring) mob.getNavigation().stop();
        setCharging(false);
    }

    @Override
    public void stop() {
        setCharging(false);
        chargeTicks = 0;
    }

    @Override
    public void tick() {
        LivingEntity t = mob.getTarget();
        if (t == null) return;

        mob.getLookControl().setLookAt(t, 30.0F, 30.0F);
        boolean hasLOS = !requireLOS || mob.hasLineOfSight(t);

        if (cooldownTicks > 0) {
            cooldownTicks--;
            setCharging(false);
            chargeTicks = 0;
            return;
        }

        if (hasLOS) {
            setCharging(true);
            chargeTicks++;

            if (chargeTicks >= chargeUpTicks) {
                fireOnce(t);
                cooldownTicks = mob.getRandom().nextIntBetweenInclusive(cooldownMin, cooldownMax);
                chargeTicks = 0;
                setCharging(false);
            }
        } else {
            setCharging(false);
            chargeTicks = 0;
        }
    }

    private void fireOnce(LivingEntity target) {
        if (mob.level().isClientSide) return;

        float distFactor = (float)(Math.sqrt(mob.distanceToSqr(target)) / Math.sqrt(maxLosRangeSqr));
        distFactor = Mth.clamp(distFactor, 0.1F, 1.0F);

        // Delegate the actual projectile to the mob itself:
        mob.performRangedAttack(target, distFactor);
    }
    private void setCharging(boolean charging) {
        if (onCharging != null) onCharging.accept(charging);
    }

    /*Example
    * public class SunMageEntity extends Monster implements RangedAttackMob {
    // attributes, ctor…

    @Override
    public void performRangedAttack(LivingEntity target, float df) {
        SunBoltEntity bolt = new SunBoltEntity(ModEntities.SUN_BOLT.get(), level());
        bolt.setOwner(this);
        bolt.setPos(getX(), getEyeY() - 0.1, getZ());
        double dx = target.getX() - bolt.getX();
        double dy = target.getEyeY() - bolt.getY();
        double dz = target.getZ() - bolt.getZ();
        bolt.shoot(dx, dy, dz, 1.6f + 0.6f * df, 0.0f);
        level().addFreshEntity(bolt);
        playSound(net.minecraft.sounds.SoundEvents.BLAZE_SHOOT, 1f, 1f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new GenericRangedChargeShootGoal.Builder<>(this)
                .maxLosRange(56.0)
                .stopAggroRange(45.0)
                .chargeUpTicks(15)
                .cooldown(30, 60)
                .stopNavigationWhileFiring(false) //this allows pathing while aiming
                .build());
        // other goals...
    }
}*/
}
