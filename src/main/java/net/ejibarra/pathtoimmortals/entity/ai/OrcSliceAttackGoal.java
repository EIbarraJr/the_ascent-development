package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.OrcWarrior;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class OrcSliceAttackGoal extends Goal {
    private final OrcWarrior orc;
    private final double chaseSpeed;

    // Tune these to match your actual animations (ticks @ 20 TPS)
    private static final int CHARGE_DURATION = 20;   // charge animation length
    private static final int SLICE_DURATION  = 20;   // slice animation length

    // Windows inside SLICE where movement + hit happen
    private static final int LUNGE_START_TICK = 2;
    private static final int LUNGE_END_TICK   = 6;
    private static final int HIT_TICK         = 15;

    private static final int COOLDOWN_TICKS   = 5;

    private int cooldown;
    private boolean didHit;

    public OrcSliceAttackGoal(OrcWarrior orc, double chaseSpeed) {
        this.orc = orc;
        this.chaseSpeed = chaseSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = orc.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = orc.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void start() {
        didHit = false;
        // don’t force an attack immediately; we'll chase until in range
    }

    @Override
    public void stop() {
        orc.setAttackState(OrcWarrior.ATTACK_NONE);
        orc.setAttackTick(0);
        didHit = false;
        orc.getNavigation().stop();
    }

    private static final double ATTACK_REACH_BLOCKS = 4.0D;

    private double getAttackReachSqr(LivingEntity target) {
        // Fixed reach (3.5 blocks), slightly adjusted for target size so big targets still feel right
        double reach = ATTACK_REACH_BLOCKS + (target.getBbWidth() * 0.5D);
        return reach * reach;
    }

    @Override
    public void tick() {
        LivingEntity target = orc.getTarget();
        if (target == null) return;

        if (cooldown > 0) cooldown--;

        // Keep looking at the target
        orc.getLookControl().setLookAt(target, 30.0F, 30.0F);

        int state = orc.getAttackState();

        // === STATE 0: Not attacking -> chase until close enough, then start charge ===
        if (state == OrcWarrior.ATTACK_NONE) {
            double distSqr = orc.distanceToSqr(target);

            // Use bounding-box based reach so big entities feel right
            double reach = getAttackReachSqr(target);

            if (distSqr <= reach) {
                // Start CHARGE
                orc.getNavigation().stop();
                orc.setAttackState(OrcWarrior.ATTACK_CHARGE);
                orc.setAttackTick(0);
                didHit = false;
                return;
            }

            // Chase
            orc.getNavigation().moveTo(target, chaseSpeed);
            return;
        }

        // increment attack timer
        orc.incrementAttackTick();
        int t = orc.getAttackTick();

        // === STATE 1: CHARGE ===
        if (state == OrcWarrior.ATTACK_CHARGE) {
            orc.getNavigation().stop(); // plant feet during charge

            // Smoothly face target while charging
            faceTargetSoft(target);

            if (t >= CHARGE_DURATION) {
                // Switch to SLICE
                orc.setAttackState(OrcWarrior.ATTACK_SLICE);
                orc.setAttackTick(0);
                didHit = false;
            }
            return;
        }

        // === STATE 2: SLICE ===
        if (state == OrcWarrior.ATTACK_SLICE) {
            orc.getNavigation().stop();
            faceTargetSoft(target);

            // Lunge forward during a short window so it doesn't look rooted
            if (t >= LUNGE_START_TICK && t <= LUNGE_END_TICK) {
                lungeToward(target, 0.18D); // tweak strength
            }

            // Single hit on HIT_TICK if still in reach
            if (!didHit && t >= HIT_TICK) {
                if (orc.distanceToSqr(target) <= getAttackReachSqr(target) + 0.5D) {
                    orc.doHurtTarget(target);
                }
                didHit = true;
            }

            // End slice -> cooldown, reset
            if (t >= SLICE_DURATION) {
                orc.setAttackState(OrcWarrior.ATTACK_NONE);
                orc.setAttackTick(0);
                cooldown = COOLDOWN_TICKS;
            }
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    private void faceTargetSoft(LivingEntity target) {
        Vec3 to = target.position().subtract(orc.position());
        double yaw = Mth.atan2(to.z, to.x) * (180D / Math.PI) - 90D;
        float newYaw = (float) Mth.approachDegrees(orc.getYRot(), (float) yaw, 12.0F);
        orc.setYRot(newYaw);
        orc.yBodyRot = newYaw;
    }

    private void lungeToward(LivingEntity target, double strength) {
        Vec3 dir = target.position().subtract(orc.position());
        dir = new Vec3(dir.x, 0, dir.z);
        if (dir.lengthSqr() < 1.0E-6) return;

        Vec3 n = dir.normalize().scale(strength);

        // Preserve vertical motion; add controlled horizontal push
        Vec3 cur = orc.getDeltaMovement();
        orc.setDeltaMovement(cur.x * 0.4 + n.x, cur.y, cur.z * 0.4 + n.z);
        orc.hasImpulse = true;
    }
}
