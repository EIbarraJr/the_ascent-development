package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.AssassainEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AssassainAttackGoal extends MeleeAttackGoal {
    private final AssassainEntity assassain;

    // How long (in ticks) to keep the ATTACKING flag true after a hit
    private int attackAnimTicks;

    public AssassainAttackGoal(AssassainEntity assassain, double speedModifier, boolean longMemory) {
        super(assassain, speedModifier, longMemory);
        this.assassain = assassain;
    }

    @Override
    public void start() {
        super.start();
        attackAnimTicks = 0;
        assassain.setAttacking(false);
    }

    @Override
    public void stop() {
        super.stop();
        assassain.setAttacking(false);
        attackAnimTicks = 0;
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.assassain.getTarget();
        if (target == null) {
            assassain.setAttacking(false);
            attackAnimTicks = 0;
            return;
        }

        // Face target
        this.assassain.getLookControl().setLookAt(target, 30.0F, 30.0F);

        double distSq = this.assassain.distanceToSqr(target.getX(), target.getY(), target.getZ());
        double reachSq = getAttackReachSqr(target);

        // When within reach and cooldown ready, swing & mark attacking
        // Use isTimeToAttack() + resetAttackCooldown(), like vanilla
        if (distSq <= reachSq && this.isTimeToAttack()) {
            this.resetAttackCooldown();

            this.assassain.getNavigation().stop();

            // 🔥 Trigger attack animation on the server
            this.assassain.setAttacking(true);
            this.attackAnimTicks = 15; // match/approx your attack anim length (10 ticks ~ 0.5s)

            this.assassain.swing(InteractionHand.MAIN_HAND);
            this.assassain.doHurtTarget(target);
        }

        // Count down the animation window
        if (attackAnimTicks > 0) {
            attackAnimTicks--;
            if (attackAnimTicks == 0) {
                // When timer runs out, shut off the flag
                this.assassain.setAttacking(false);
            }
        }
    }

    @Override
    protected double getAttackReachSqr(LivingEntity target) {
        // very similar to vanilla Zombie: reach based on width
        return (double)(this.assassain.getBbWidth() * 2.0F * this.assassain.getBbWidth() * 2.0F + target.getBbWidth());
    }
}
