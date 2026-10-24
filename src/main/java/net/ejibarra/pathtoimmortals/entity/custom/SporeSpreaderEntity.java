package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.ai.HereticAttackGoal;
import net.ejibarra.pathtoimmortals.entity.ai.KeepDistanceFromTargetGoal;
import net.ejibarra.pathtoimmortals.entity.ai.SummonMinionsGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;

public class SporeSpreaderEntity extends Monster implements Enemy {
    private static final EntityDataAccessor<Boolean> SUMMONING =
            SynchedEntityData.defineId(SporeSpreaderEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleState = new AnimationState();
    public final AnimationState summoningState = new AnimationState();
    public final AnimationState walkState = new AnimationState();
    private int summonCooldownTicks = 0;

    /*Boss Bar*/
    /*private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Heretic Pawn"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_12);*/ //can reenable here and three spots in the bottom

    public SporeSpreaderEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 100;
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_OTHER, 0.0F);
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(false);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 75)
                .add(Attributes.ARMOR_TOUGHNESS, 12f)
                .add(Attributes.ARMOR, 25f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 2.5f)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.MOVEMENT_SPEED, (double)0.33F)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    public boolean isSummoning() { return this.entityData.get(SUMMONING); }
    public void setSummoning(boolean v) { this.entityData.set(SUMMONING, v); }
    public int getSummonCooldownTicks() { return summonCooldownTicks; }
    public void setSummonCooldownTicks(int ticks) { this.summonCooldownTicks = Math.max(0, ticks); }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SUMMONING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        //this.goalSelector.addGoal(1, new HereticAttackGoal(this, 1.1D, true));
        this.goalSelector.addGoal(3, new KeepDistanceFromTargetGoal(this, 12.0F, 16.0F, 1.15D, 1.3D));
// Summons up to 4 Fungi Swordsmen when player is in range and cooldown ready
        this.goalSelector.addGoal(1, new SummonMinionsGoal(this, 30.0F, 4, 20*4 /*12s*/, 32 /*scatter radius*/));

        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    /*Boss Bar*/

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        //this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        //this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide && summonCooldownTicks > 0) {
            summonCooldownTicks--; // server cooldown
        }
        //this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
    // === Client anim driver ===
    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            updateAnimationsClient();
        }
    }


    private void updateAnimationsClient() {
// Priority: summoning > walk > idle
        if (this.isSummoning()) {
            if (!summoningState.isStarted()) summoningState.start(this.tickCount);
            walkState.stop();
            idleState.stop();
        } else {
            summoningState.stop();
            boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 0.0005D;
            if (isMoving) {
                if (!walkState.isStarted()) walkState.start(this.tickCount);
                idleState.stop();
            } else {
                walkState.stop();
                if (!idleState.isStarted()) idleState.start(this.tickCount);
            }
        }
    }

    //Loot table
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("pathtoimmortals", "entities/spore_spreader");
    }


}
