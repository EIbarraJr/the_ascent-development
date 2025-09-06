package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ai.HereticAttackGoal;
import net.ejibarra.pathtoimmortals.entity.ai.SpikedRatAttackGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SpikedRatEntity extends Monster implements Enemy {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(SpikedRatEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleSpikedRateState = new AnimationState();
    private int idleSpikedRatTimeout = 0;

    public final AnimationState spikedratAttackingState = new AnimationState();
    public int spikedratAttackingTimeout;
    public final AnimationState walkState = new AnimationState();

    /*Boss Bar*/
    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Spiked Rate"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_12);

    public SpikedRatEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 60;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)//10
                .add(Attributes.ARMOR, 40f)//33
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0f)
                .add(Attributes.ATTACK_DAMAGE, 42)
                .add(Attributes.MOVEMENT_SPEED, (double)0.26F)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    private void setupAnimationStates() {
        if (this.idleSpikedRatTimeout <= 0) {
            this.idleSpikedRatTimeout = this.random.nextInt(40) + 80;
            this.idleSpikedRateState.start(this.tickCount);
        } else {
            --this.idleSpikedRatTimeout;
        }

        if (this.isAttacking() && spikedratAttackingTimeout <= 0){
            spikedratAttackingTimeout = 20;
            spikedratAttackingState.start(this.tickCount);
        } else {
            --this.spikedratAttackingTimeout;
        }

        if (!this.isAttacking()){
            spikedratAttackingState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 2.0E-5; // nice debounce
            if (moving) {
                if (!walkState.isStarted()) walkState.start(this.tickCount);
                idleSpikedRateState.stop();
            } else {
                if (!idleSpikedRateState.isStarted()) idleSpikedRateState.start(this.tickCount);
                walkState.stop();
            }
        }

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new SpikedRatAttackGoal(this, 1.1D, true));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());

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
