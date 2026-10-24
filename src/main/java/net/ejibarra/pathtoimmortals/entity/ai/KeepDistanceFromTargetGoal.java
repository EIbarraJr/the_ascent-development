package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.SporeSpreaderEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class KeepDistanceFromTargetGoal extends Goal {
    private final SporeSpreaderEntity mob;
    private final float minDist; // retreat if below
    private final float maxDist; // approach if above
    private final double retreatSpeed;
    private final double approachSpeed;


    private Player focus;


    public KeepDistanceFromTargetGoal(SporeSpreaderEntity mob, float minDist, float maxDist, double retreatSpeed, double approachSpeed) {
        this.mob = mob;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.retreatSpeed = retreatSpeed;
        this.approachSpeed = approachSpeed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }


    @Override
    public boolean canUse() {
        Player nearest = mob.level().getNearestPlayer(mob, mob.getAttributeValue(Attributes.FOLLOW_RANGE));
        if (nearest == null || nearest.isSpectator() || !nearest.isAlive()) return false;
        this.focus = nearest;
        return true;
    }


    @Override
    public boolean canContinueToUse() {
        return focus != null && focus.isAlive() && focus.distanceTo(mob) <= mob.getAttributeValue(Attributes.FOLLOW_RANGE) + 4.0D;
    }


    @Override
    public void stop() {
        this.focus = null;
        mob.getNavigation().stop();
    }


    @Override
    public void tick() {
        if (focus == null) return;
        mob.getLookControl().setLookAt(focus, 30.0F, 30.0F);


        double dist = mob.distanceTo(focus);
        Vec3 mobPos = mob.position();
        Vec3 targetPos = focus.position();
        Vec3 toMob = mobPos.subtract(targetPos);
        Vec3 awayDir = toMob.lengthSqr() < 1.0e-4 ? new Vec3(1, 0, 0) : toMob.normalize();


        if (dist < minDist) {
// Retreat away from player
            Vec3 desired = mobPos.add(awayDir.scale(2.5));
            mob.getNavigation().moveTo(desired.x, desired.y, desired.z, retreatSpeed);
        } else if (dist > maxDist) {
// Approach to keep pressure but outside melee
            Vec3 toward = targetPos.subtract(mobPos).normalize();
            Vec3 desired = mobPos.add(toward.scale(2.0));
            mob.getNavigation().moveTo(desired.x, desired.y, desired.z, approachSpeed);
        } else {
// Maintain distance by gentle orbiting
            double orbit = (mob.tickCount % 40) < 20 ? 1 : -1; // switch direction every 1s
            Vec3 lateral = new Vec3(-awayDir.z, 0, awayDir.x).normalize().scale(1.2 * orbit);
            Vec3 desired = mobPos.add(lateral);
            mob.getNavigation().moveTo(desired.x, desired.y, desired.z, 1.05D);
        }
    }
}
