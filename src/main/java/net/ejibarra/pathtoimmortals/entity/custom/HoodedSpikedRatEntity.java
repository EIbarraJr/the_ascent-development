package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.ai.CastProjectileGoal;
import net.ejibarra.pathtoimmortals.entity.ai.RangedChargeShootGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HoodedSpikedRatEntity extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> CHARGING =
            SynchedEntityData.defineId(HoodedSpikedRatEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleHoodedRatState = new AnimationState();
    private int idleHoodedRatTimeout = 0;

    public final AnimationState hoodedRatAttackingState = new AnimationState();
    public int hoodedRatAttackingTimeout;
    public final AnimationState walkState = new AnimationState();

    /*Boss Bar*/
    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Hooded Rat"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_12);

    public HoodedSpikedRatEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 50;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)
                .add(Attributes.ARMOR, 28f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 2.2f)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .add(Attributes.MOVEMENT_SPEED, (double)0.28F)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    private void setupAnimationStates() {
        if (this.idleHoodedRatTimeout <= 0) {
            this.idleHoodedRatTimeout = this.random.nextInt(40) + 80;
            this.idleHoodedRatState.start(this.tickCount);
        } else {
            --this.idleHoodedRatTimeout;
        }

        if (this.isCharging() && hoodedRatAttackingTimeout <= 0){
            hoodedRatAttackingTimeout = 41;
            hoodedRatAttackingState.start(this.tickCount);
        } else {
            --this.hoodedRatAttackingTimeout;
        }

        if (!this.isCharging()){
            hoodedRatAttackingState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 2.0E-5; // nice debounce
            if (moving) {
                if (!walkState.isStarted()) walkState.start(this.tickCount);
                idleHoodedRatState.stop();
            } else {
                if (!idleHoodedRatState.isStarted()) idleHoodedRatState.start(this.tickCount);
                walkState.stop();
            }
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHARGING, false);
    }

    public void setCharging(boolean charging){this.entityData.set(CHARGING, charging);}
    public boolean isCharging(){return  this.entityData.get(CHARGING);}

    // RangedAttackMob contract (called from the goal when it’s time to shoot)
    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        SpikeProjectileEntity proj = new SpikeProjectileEntity(ModEntities.SPIKE_PROJECTILE.get(), level());
        // 2) Set owner and spawn position (from shooter’s eyes)
        proj.setOwner(this);
        proj.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());

         float dmg = 45f;
        proj.setBaseDamage(dmg);

        // 3) Now aim from the projectile’s position to the target
        double dx = target.getX()     - proj.getX();
        double dy = target.getEyeY()  - proj.getY();
        double dz = target.getZ()     - proj.getZ();

        // 4) Launch
        proj.shoot(dx, dy, dz, 1.8f, 0.1f);

        // 5) Spawn server-side
        level().addFreshEntity(proj);

        this.playSound(net.minecraft.sounds.SoundEvents.BLAZE_SHOOT, 1.0f, 1.0f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new RangedChargeShootGoal.Builder<>(this).maxLosRange(56).stopAggroRange(45).chargeUpTicks(15).cooldown(20,40).stopNavigationWhileFiring(false).onCharging(this::setCharging).build());

        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    /*Boss Bar*/

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
