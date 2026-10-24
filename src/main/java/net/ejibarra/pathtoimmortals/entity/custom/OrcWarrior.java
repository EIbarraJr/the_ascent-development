package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.attributes.ModAttributes;
import net.ejibarra.pathtoimmortals.entity.ai.OgreAttackGoal;
import net.ejibarra.pathtoimmortals.entity.ai.OrcSliceAttackGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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

import java.util.UUID;

public class OrcWarrior extends Monster implements Enemy {
    public OrcWarrior(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired(); // vanilla flag: never despawn naturally
        this.xpReward = 100;
    }

    // Attack states (synced to client)
    public static final int ATTACK_NONE   = 0;
    public static final int ATTACK_CHARGE = 1;
    public static final int ATTACK_SLICE  = 2;

    private static final EntityDataAccessor<Integer> ATTACK_STATE =
            SynchedEntityData.defineId(OrcWarrior.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACK_TICK =
            SynchedEntityData.defineId(OrcWarrior.class, EntityDataSerializers.INT);

    // ---- animation states (client uses these) ----
    public final AnimationState idleState       = new AnimationState();
    public final AnimationState chargeState       = new AnimationState();
    public final AnimationState sliceState      = new AnimationState();
    public final AnimationState walkState = new AnimationState();// loop while true

    /*Boss Bar*/

    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Orc Warrior"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_12);

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 800)
                .add(Attributes.ARMOR_TOUGHNESS, 12f)
                .add(Attributes.ARMOR, 10f)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 3.5f)
                .add(Attributes.ATTACK_DAMAGE, 60)
                .add(Attributes.MOVEMENT_SPEED, (double)0.28F)
                .add(ModAttributes.PERCENT_REGEN.get(), 0.075D)
                .add(Attributes.ATTACK_SPEED, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, ATTACK_NONE);
        this.entityData.define(ATTACK_TICK, 0);
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public int getAttackTick() {
        return this.entityData.get(ATTACK_TICK);
    }

    public void setAttackState(int state) {
        this.entityData.set(ATTACK_STATE, state);
    }

    public void setAttackTick(int tick) {
        this.entityData.set(ATTACK_TICK, tick);
    }

    public void incrementAttackTick() {
        this.entityData.set(ATTACK_TICK, this.getAttackTick() + 1);
    }

    public boolean isInAttack() {
        return this.getAttackState() != ATTACK_NONE;
    }

    @Override
    public void tick() {
        super.tick();

        // Drive client animations from synced state
        if (this.level().isClientSide) {
            setupAnimationStates();
        }
    }

    // client-only
    private int lastAttackStateClient = -1;
    private boolean lastWasMovingClient = false;

    private void setupAnimationStates() {
        int state = getAttackState();
        boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-4D;

        // ---- Attack states override locomotion ----
        if (state == ATTACK_CHARGE) {
            if (lastAttackStateClient != ATTACK_CHARGE) {
                stopAllBut(chargeState);
                chargeState.start(this.tickCount);
                lastAttackStateClient = ATTACK_CHARGE;
            }
            return;
        }

        if (state == ATTACK_SLICE) {
            if (lastAttackStateClient != ATTACK_SLICE) {
                stopAllBut(sliceState);
                sliceState.start(this.tickCount);
                lastAttackStateClient = ATTACK_SLICE;
            }
            return;
        }

        // ---- Not attacking: reset attack cache ----
        if (lastAttackStateClient != ATTACK_NONE) {
            // leaving attack state, stop attack anims
            chargeState.stop();
            sliceState.stop();
            lastAttackStateClient = ATTACK_NONE;
        }

        // ---- Locomotion (only restart if changed) ----
        if (moving) {
            if (!lastWasMovingClient) {
                stopAllBut(walkState);
                walkState.start(this.tickCount);
                lastWasMovingClient = true;
            }
        } else {
            if (lastWasMovingClient) {
                stopAllBut(idleState);
                idleState.start(this.tickCount);
                lastWasMovingClient = false;
            }
            // also ensure idle is started at least once (when spawning)
            if (!idleState.isStarted()) {
                stopAllBut(idleState);
                idleState.start(this.tickCount);
            }
        }
    }

    private void stopAllBut(AnimationState keep) {
        if (keep != idleState) idleState.stop();
        if (keep != walkState) walkState.stop();
        if (keep != chargeState) chargeState.stop();
        if (keep != sliceState) sliceState.stop();
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 12f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

        this.goalSelector.addGoal(1, new OrcSliceAttackGoal(this, 1.1D));

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

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    // Also prevent distance-based removal:
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    //Loot table
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("pathtoimmortals", "entities/orc_warrior");
    }
}
