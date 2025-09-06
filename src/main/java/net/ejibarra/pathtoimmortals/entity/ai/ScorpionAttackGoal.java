
package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.ScorpionEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ScorpionAttackGoal extends Goal {
    private final ScorpionEntity mob;
    private final double speed;
    private LivingEntity target;

    // Alternation
    private boolean nextIsPinch = true;

    // Tuning (ticks are 20/sec)
    private static final int PINCH_WINDUP   = 15;
    private static final int PINCH_RECOVERY = 6;
    private static final int STING_WINDUP   = 10;
    private static final int STING_RECOVERY = 5;

    private static final int COOLDOWN_TICKS = 12;

    private static final double PINCH_REACH = 2.0; // blocks
    private static final double STING_REACH = 3.0;

    private static final float PINCH_DAMAGE = 30.0f;
    private static final float STING_DAMAGE = 60.0f;

    private static final int STING_POISON_DURATION = 140; // 7s
    private static final int STING_POISON_AMPL     = 3;   // Poison IV

    // State machine for one attack cycle
    private enum Phase { CHASING, WINDUP, HIT, RECOVERY, COOLDOWN }
    private Phase phase = Phase.CHASING;
    private int phaseTicks = 0;
    private boolean didHitThisCycle = false;
    private boolean isPinchThisCycle = true;

    public ScorpionAttackGoal(ScorpionEntity mob, double speed) {
        this.mob = mob;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity t = mob.getTarget();
        if (t == null || !t.isAlive()) return false;
        this.target = t;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity t = mob.getTarget();
        return t != null && t.isAlive();
    }

    @Override
    public void start() {
        phase = Phase.CHASING;
        phaseTicks = 0;
        didHitThisCycle = false;
        // pick the next attack based on alternation
        isPinchThisCycle = nextIsPinch;
        mob.setAttackState(ScorpionEntity.ATTACK_IDLE);
    }

    @Override
    public void stop() {
        mob.getNavigation().stop();
        mob.setAttackState(ScorpionEntity.ATTACK_IDLE);
        target = null;
    }

    @Override
    public void tick() {
        if (target == null) return;

        // Always face target
        mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        switch (phase) {
            case CHASING -> tickChasing();
            case WINDUP  -> tickWindup();
            case HIT     -> tickHit();
            case RECOVERY-> tickRecovery();
            case COOLDOWN-> tickCooldown();
        }
    }

    private void tickChasing() {
        double reach = isPinchThisCycle ? PINCH_REACH : STING_REACH;
        double dist = mob.distanceToSqr(target);
        double reachSqr = reach * reach;

        // Path toward target
        mob.getNavigation().moveTo(target, speed);

        if (dist <= reachSqr && mob.hasLineOfSight(target)) {
            // Start windup and fire the client animation
            phase = Phase.WINDUP;
            phaseTicks = 0;
            didHitThisCycle = false;
            if (isPinchThisCycle) {
                mob.setAttackState(ScorpionEntity.ATTACK_PINCH);
                mob.playPinchSound();
            } else {
                mob.setAttackState(ScorpionEntity.ATTACK_STING);
                mob.playStingSound();
            }
        }
    }

    private void tickWindup() {
        int needed = isPinchThisCycle ? PINCH_WINDUP : STING_WINDUP;
        phaseTicks++;
        // During windup, keep close to target if they backpedal a bit
        mob.getNavigation().moveTo(target, speed);

        if (phaseTicks >= needed) {
            phase = Phase.HIT;
            phaseTicks = 0;
        }
    }

    private void tickHit() {
        if (!didHitThisCycle) {
            // Only attempt hit once per cycle
            double reach = isPinchThisCycle ? PINCH_REACH : STING_REACH;
            if (mob.distanceToSqr(target) <= reach * reach && mob.hasLineOfSight(target)) {
                // Apply damage/effect
                if (isPinchThisCycle) {
                    doPinch();
                } else {
                    doSting();
                }
            }
            didHitThisCycle = true;
        }
        // Immediately go to recovery
        phase = Phase.RECOVERY;
        phaseTicks = 0;
        // Return state to idle so the one-shot animation can end client-side
        mob.setAttackState(ScorpionEntity.ATTACK_IDLE);
    }

    private void tickRecovery() {
        int needed = isPinchThisCycle ? PINCH_RECOVERY : STING_RECOVERY;
        phaseTicks++;
        if (phaseTicks >= needed) {
            // Begin cooldown, then flip alternation
            phase = Phase.COOLDOWN;
            phaseTicks = 0;
            nextIsPinch = !isPinchThisCycle;
        }
    }

    private void tickCooldown() {
        phaseTicks++;
        if (phaseTicks >= COOLDOWN_TICKS) {
            // New cycle: chase again (the other attack is selected in start() next time)
            phase = Phase.CHASING;
            phaseTicks = 0;
            // Lock in which attack this new cycle will use
            isPinchThisCycle = nextIsPinch;
        } else {
            // light pressure during cooldown so it doesn’t stall
            mob.getNavigation().moveTo(target, speed);
        }
    }

    private void doPinch() {
        // Slightly stronger, short reach
        float dmg = PINCH_DAMAGE;
        var ds = mob.damageSources().mobAttack(mob);
        target.hurt(ds, dmg);

        // gentle knockback in facing direction
        Vec3 kb = new Vec3(
                -Mth.sin(mob.getYRot() * ((float)Math.PI / 180F)) * 0.3,
                0.1,
                Mth.cos(mob.getYRot() * ((float)Math.PI / 180F)) * 0.3
        );
        target.push(kb.x, kb.y, kb.z);
        target.hurtMarked = true;
    }

    private void doSting() {
        float dmg = STING_DAMAGE;
        var ds = mob.damageSources().mobAttack(mob);

        // hurt() returns true only if damage went through
        boolean damaged = target.hurt(ds, dmg);

        if (damaged && target instanceof LivingEntity ) {
            LivingEntity living = target;
            living.addEffect(new MobEffectInstance(
                    MobEffects.POISON,
                    STING_POISON_DURATION,
                    STING_POISON_AMPL
            ));
        }
    }
}
