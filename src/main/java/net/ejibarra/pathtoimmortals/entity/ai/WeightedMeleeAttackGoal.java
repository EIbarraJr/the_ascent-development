package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.BeefsteakMushroomEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class WeightedMeleeAttackGoal extends Goal {
    private final BeefsteakMushroomEntity mob;
    private final double speed;
    private final boolean followEvenIfNotSeen;
    private BeefsteakMushroomEntity.Move lastMove = BeefsteakMushroomEntity.Move.NONE;
    private int pathRecalcTimer = 0;

    public WeightedMeleeAttackGoal(BeefsteakMushroomEntity mob, double speed, boolean followEvenIfNotSeen) {
        this.mob = mob;
        this.speed = speed;
        this.followEvenIfNotSeen = followEvenIfNotSeen;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = mob.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = mob.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void start() {
        pathRecalcTimer = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = mob.getTarget();
        if (target == null) return;

        mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        // Recalculate path occasionally
        if (--pathRecalcTimer <= 0) {
            pathRecalcTimer = 10;
            mob.getNavigation().moveTo(target, speed);
        }

        double reachSqr = 3.5D * 3.5D; // ~3.5 block range
        boolean inSight = followEvenIfNotSeen || mob.getSensing().hasLineOfSight(target);

        if (mob.distanceToSqr(target) <= reachSqr && inSight) {
            // When in range, choose and perform a weighted attack if idle
            if (!mob.isAttacking()) {
                BeefsteakMushroomEntity.Move next = mob.chooseWeightedMove(mob.getRandom(), lastMove);
                lastMove = next;
                mob.beginAttack(next);
            }
        }
    }

    @Override
    public void stop() {
        // Optional: stop movement when the goal ends
        mob.getNavigation().stop();
    }
}
