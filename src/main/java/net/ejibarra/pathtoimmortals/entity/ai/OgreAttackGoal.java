package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.OgreEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class OgreAttackGoal extends Goal {
    private final OgreEntity ogre;
    private final double speed;
    private final boolean longMemory;

    private PathNavigation nav;
    private LivingEntity target;

    private int globalCooldown = 0;      // cooldown between *starting* attacks
    private int windupTicks = 0;         // local server countdown until we hit
    private boolean lastWasSlam = false; // alternation toggle
    private Runnable pendingHit = null;  // executes when windup reaches 0

    // Tunables from your clips
    private static final int SLAM_WINDUP = 15;   // 1.0s
    private static final int SLAM_TOTAL  = 22;   // 1.1s
    private static final int WACK_WINDUP = 20;   // 1.5s
    private static final int WACK_TOTAL  = 35;   // 1.75s
    private static final int ATTACK_COOLDOWN = 12; // after the animation finishes

    public OgreAttackGoal(OgreEntity ogre, double speed, boolean longMemory) {
        this.ogre = ogre;
        this.speed = speed;
        this.longMemory = longMemory;
        this.nav = ogre.getNavigation();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity t = ogre.getTarget();
        if (t == null || !t.isAlive()) return false;
        this.target = t;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return target != null && target.isAlive() && (longMemory || ogre.distanceToSqr(target) < 900.0);
    }

    @Override
    public void stop() {
        target = null;
        pendingHit = null;
        windupTicks = 0;
        ogre.setAttackState(OgreEntity.ATTACK_NONE, 0, 0);
    }

    private void startWack(LivingEntity tgt) {
        windupTicks = WACK_WINDUP;
        ogre.setAttackState(OgreEntity.ATTACK_WACK, WACK_WINDUP, WACK_TOTAL);
        ogre.swing(InteractionHand.MAIN_HAND);

        final UUID tgtId = tgt.getUUID();
        pendingHit = () -> {
            LivingEntity live = getById(tgtId);
            if (live != null && live.isAlive() &&
                    ogre.distanceToSqr(live) <= getAttackReachSqr(ogre, live) + 1.0) {
                live.hurt(ogre.damageSources().mobAttack(ogre), ogre.getWhackDamage());
                live.knockback(03.0F, ogre.getX() - live.getX(), ogre.getZ() - live.getZ());
            }
        };
    }

    private void startSlam(LivingEntity tgt) {
        windupTicks = SLAM_WINDUP;
        ogre.setAttackState(OgreEntity.ATTACK_SLAM, SLAM_WINDUP, SLAM_TOTAL);
        ogre.swing(InteractionHand.MAIN_HAND);

        pendingHit = () -> {
            double radius = 3.5;
            AABB box = ogre.getBoundingBox().inflate(radius, 1.0, radius);
            List<LivingEntity> victims = ogre.level().getEntitiesOfClass(
                    LivingEntity.class, box, e -> e != ogre && e.isAlive() && ogre.hasLineOfSight(e));

            for (LivingEntity e : victims) {
                e.hurt(ogre.damageSources().mobAttack(ogre), ogre.getSlamDamage());
                e.knockback(3.0F, ogre.getX() - e.getX(), ogre.getZ() - e.getZ());
            }

            if (ogre.level() instanceof ServerLevel sl) {
                sl.sendParticles(ParticleTypes.POOF, ogre.getX(), ogre.getY(), ogre.getZ(),
                        12, 0.3, 0.1, 0.3, 0.02);
            }
            ogre.playSound(SoundEvents.ANVIL_LAND, 1.0F, 0.7F + ogre.getRandom().nextFloat() * 0.3F);
        };
    }

    @Override
    public void tick() {
        if (target == null) return;

        // keep moving/looking even during windup
        ogre.getLookControl().setLookAt(target, 30.0F, 30.0F);
        nav.moveTo(target, speed);

        if (globalCooldown > 0) globalCooldown--;

        if (windupTicks > 0) {
            windupTicks--;
            // keep ANIM_TICKS_LEFT counting via entity.tick(); just handle impact here
            if (windupTicks == 0 && !ogre.level().isClientSide && pendingHit != null) {
                pendingHit.run();
                pendingHit = null;
                // wait until the animation fully finishes before allowing a new start
                globalCooldown = Math.max(globalCooldown, ATTACK_COOLDOWN);
            }
            return;
        }

        // only start a new attack AFTER the last animation finished
        if (ogre.getAnimTicksLeft() > 0 || globalCooldown > 0) return;

        double reachSqr = getAttackReachSqr(ogre, target);
        double distSqr  = ogre.distanceToSqr(target);
        if (distSqr <= reachSqr) {
            if (lastWasSlam) startWack(target);
            else             startSlam(target);
            lastWasSlam = !lastWasSlam;
        }
    }
    private static final float BASE_REACH_MULT   = 1.6F; // was ~1.2F; bigger = longer arms
    private static final float EXTRA_REACH_BLOCKS = 2.0F; // add N blocks beyond width-based reach

    private static double getAttackReachSqr(Mob attacker, LivingEntity target) {
        float widthReach = attacker.getBbWidth() * BASE_REACH_MULT;
        double extra     = EXTRA_REACH_BLOCKS;
        // (widthReach + extra)^2, plus a small pad for target width
        return (double)((widthReach + extra) * (widthReach + extra)) + target.getBbWidth();
    }

    private LivingEntity getById(UUID id) {
        if (id == null) return null;
        if (!(ogre.level() instanceof ServerLevel sl)) return null;
        Entity e = sl.getEntity(id);
        return (e instanceof LivingEntity le) ? le : null;
    }

}

