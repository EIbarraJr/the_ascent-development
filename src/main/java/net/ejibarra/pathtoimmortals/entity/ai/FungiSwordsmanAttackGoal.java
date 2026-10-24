package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.custom.FungiSwordsmanEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class FungiSwordsmanAttackGoal extends MeleeAttackGoal {
    private final FungiSwordsmanEntity swordsman;
    private final double moveSpeed;
    private int attackCooldown = 0;
    private int stabWindup = -1;

    public FungiSwordsmanAttackGoal(FungiSwordsmanEntity swordsman, double speedModifier, boolean longMemory) {
        super(swordsman, speedModifier, longMemory);
        this.swordsman = swordsman;
        this.moveSpeed = speedModifier;
    }

    @Override
    public void tick() {
        LivingEntity target = this.swordsman.getTarget();
        if (target == null) return;

        this.swordsman.getLookControl().setLookAt(target, 30.0F, 30.0F);
        double distSq = this.swordsman.distanceToSqr(target.getX(), target.getY(), target.getZ());

        // Countdowns
        if (attackCooldown > 0) attackCooldown--;
        if (stabWindup >= 0) stabWindup--;

        // Handle active stab wind-up (damage timing)
        if (stabWindup == 7) { // roughly halfway through 0.75 s animation
            swordsman.doHurtTarget(target);
        }

        // Trigger new stab when ready and close enough
        if (distSq <= this.getAttackReachSqr(target)) {
            if (attackCooldown == 0 && stabWindup < 0) {
                swordsman.swing(swordsman.getUsedItemHand());
                swordsman.triggerStab();     // start animation
                stabWindup = 15;             // 0.75 s stab animation length
                attackCooldown = 20;         // short delay before next stab
            }
        } else {
            swordsman.getNavigation().moveTo(target, this.moveSpeed);
        }
    }
    @Override
    protected double getAttackReachSqr(LivingEntity target) {
        // Default reach for melee mobs is about 2.0 blocks; increase as needed
        return 3.0D; // 4, current 3 blocks square rooted = ~2.00 blocks reach, curr 1.75
    }

    @Override
    public void stop() {
        super.stop();
        swordsman.setAttacking(false);
        stabWindup = -1;
    }
}
