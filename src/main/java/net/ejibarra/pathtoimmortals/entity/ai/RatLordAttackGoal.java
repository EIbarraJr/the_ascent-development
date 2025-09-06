package net.ejibarra.pathtoimmortals.entity.ai;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.util.Mth;
import net.ejibarra.pathtoimmortals.entity.custom.RatLord; // adjust package

import java.util.EnumSet;
import java.util.Random;

public class RatLordAttackGoal extends Goal {

    private enum Attack { NONE, RANGED_CHEESE, MELEE_SWIPE, MELEE_UPPERCUT }

    private final RatLord mob;               // we need animation hooks on RatLord
    private final PathNavigation nav;
    private final double moveSpeed;

    // Tuning
    private final double rangedOnlyDistance;  // if target farther than this -> only ranged
    private final double meleeReach;          // linear blocks
    private final int attackCooldownTicks;    // time between attacks (global)
    private final int meleeWindupTicks;       // stand still before melee hit
    private final int meleeAftercastTicks;    // hold after melee
    private final int rangedWindupTicks;      // stand still before ranged shot
    private final int rangedAftercastTicks;   // hold after ranged shot

    // State
    private Attack current = Attack.NONE;
    private int windup = 0;
    private int aftercast = 0;
    private int cooldown = 0;
    private final Random rng = new Random();
    private boolean didDamageThisAttack = false;

    public RatLordAttackGoal(
            RatLord mob,
            double moveSpeed,
            double rangedOnlyDistance,
            double meleeReach,
            int attackCooldownTicks,
            int meleeWindupTicks,
            int meleeAftercastTicks,
            int rangedWindupTicks,
            int rangedAftercastTicks
    ) {
        this.mob = mob;
        this.nav = mob.getNavigation();
        this.moveSpeed = moveSpeed;

        this.rangedOnlyDistance = Math.max(1.0, rangedOnlyDistance);
        this.meleeReach = Math.max(0.5, meleeReach);
        this.attackCooldownTicks = Math.max(1, attackCooldownTicks);
        this.meleeWindupTicks = Math.max(0, meleeWindupTicks);
        this.meleeAftercastTicks = Math.max(0, meleeAftercastTicks);
        this.rangedWindupTicks = Math.max(0, rangedWindupTicks);
        this.rangedAftercastTicks = Math.max(0, rangedAftercastTicks);

        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity t = mob.getTarget();
        return t != null && t.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        return canUse();
    }

    @Override
    public void start() {
        cooldown = 0;
        current = Attack.NONE;
        windup = 0;
        aftercast = 0;
        didDamageThisAttack = false;
        mob.setCharging(false);
    }

    @Override
    public void stop() {
        nav.stop();
        mob.setCharging(false);
        current = Attack.NONE;
        windup = 0;
        aftercast = 0;
        didDamageThisAttack = false;
    }

    @Override
    public void tick() {
        LivingEntity target = mob.getTarget();
        if (target == null) return;

        // Look at target
        mob.getLookControl().setLookAt(target, 30f, 30f);

        double distSqr = mob.distanceToSqr(target);
        double dist = Math.sqrt(distSqr);
        boolean canSee = mob.getSensing().hasLineOfSight(target);

        // Handle aftercast (stationary)
        if (aftercast > 0) {
            nav.stop();
            aftercast--;
            if (aftercast == 0) {
                cooldown = attackCooldownTicks;
                current = Attack.NONE;
            }
            return;
        }

        // Global cooldown – chase a bit while waiting
        if (cooldown > 0) {
            cooldown--;
            chaseToward(target);
            return;
        }

        // If no attack chosen yet, pick one (respect distance)
        if (current == Attack.NONE) {
            current = pickNextAttack(dist);
            didDamageThisAttack = false;

            if (current == Attack.RANGED_CHEESE) {
                windup = rangedWindupTicks;
                mob.setCharging(true); // charging loop starts now
            } else {
                windup = meleeWindupTicks;
                mob.setCharging(false);
            }
        }

        // If attack is melee but not in reach, keep closing until we can wind-up
        if (isMelee(current)) {
            double reachSqr = getMeleeReachSqr(target);
            if (distSqr > reachSqr || !canSee) {
                chaseToward(target);
                // don't tick down windup until in position and LoS
                return;
            }
        } else {
            // ranged – if no LoS, close in
            if (!canSee) {
                chaseToward(target);
                return;
            }
        }

        // Wind-up phase (stationary)
        if (windup > 0) {
            nav.stop();
            windup--;
            if (windup > 0) return; // still winding up

            // Windup finished → execute
            if (current == Attack.RANGED_CHEESE) {
                float power = (float) Mth.clamp(dist / 20.0, 0.25, 1.0);
                mob.performRangedAttack(target, power);
                mob.setCharging(false);
                mob.triggerRangedCastAnim(); // one-shot cheese animation
                aftercast = rangedAftercastTicks;
                return;
            }

            // Melee: play swing anim now; apply damage this tick or a beat later
            if (current == Attack.MELEE_SWIPE) {
                mob.swing(mob.getUsedItemHand());
                mob.triggerSwipeAnim();
            } else { // UPPERCUT
                mob.swing(mob.getUsedItemHand());
                mob.triggerUppercutAnim();
            }

            // Apply damage immediately when windup ends (can offset by a few ticks if you want)
            if (!didDamageThisAttack) {
                mob.doHurtTarget(target);
                didDamageThisAttack = true;
            }
            aftercast = meleeAftercastTicks;
            return;
        }

        // Safety: if we somehow reach here without windup/aftercast, reset
        cooldown = attackCooldownTicks;
        current = Attack.NONE;
    }

    /* ---------------- helpers ---------------- */

    private void chaseToward(LivingEntity target) {
        nav.moveTo(target, moveSpeed);
    }

    private boolean isMelee(Attack a) {
        return a == Attack.MELEE_SWIPE || a == Attack.MELEE_UPPERCUT;
    }

    private Attack pickNextAttack(double dist) {
        // Force ranged if too far
        if (dist > rangedOnlyDistance) return Attack.RANGED_CHEESE;

        // Otherwise weighted random among 3 (tweak as you like)
        int r = rng.nextInt(100);
        if (r < 34) return Attack.RANGED_CHEESE;
        if (r < 67) return Attack.MELEE_SWIPE;
        return Attack.MELEE_UPPERCUT;
    }

    private double getMeleeReachSqr(LivingEntity target) {
        double reach = this.meleeReach + (double)target.getBbWidth() * 0.25;
        return reach * reach;
    }
}


