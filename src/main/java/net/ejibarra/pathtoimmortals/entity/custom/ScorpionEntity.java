package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.attributes.ModAttributes;
import net.ejibarra.pathtoimmortals.entity.ai.OgreAttackGoal;
import net.ejibarra.pathtoimmortals.entity.ai.ScorpionAttackGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
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

public class ScorpionEntity extends Monster implements Enemy {
    // === synced attack state for client animations ===
    // 0 = idle, 1 = PINCH, 2 = STING
    private static final EntityDataAccessor<Byte> ATTACK_STATE =
            SynchedEntityData.defineId(ScorpionEntity.class, EntityDataSerializers.BYTE);

    public static final byte ATTACK_IDLE  = 0;
    public static final byte ATTACK_PINCH = 1;
    public static final byte ATTACK_STING = 2;

    private byte lastClientAttackState = ATTACK_IDLE;
    public ScorpionEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 100;
    }
    // --- client-side animation state holders ---
    // ---- animation states (client uses these) ----
    public final AnimationState idleState       = new AnimationState();
    public final AnimationState pinchState      = new AnimationState();
    public final AnimationState stingState   = new AnimationState();
    public final AnimationState walkState = new AnimationState();// loop while true

    public void tick() {
        super.tick();
        if (level().isClientSide) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 2.0E-5; // nice debounce
            if (moving) {
                if (!walkState.isStarted()) walkState.start(this.tickCount);
                idleState.stop();
            } else {
                if (!idleState.isStarted()) idleState.start(this.tickCount);
                walkState.stop();
            }
            // Attack one-shots triggered by synced state
            byte cur = getAttackState();
            if (cur != lastClientAttackState) {
                // stop any previous one-shot
                pinchState.stop();
                stingState.stop();

                if (cur == ATTACK_PINCH) {
                    pinchState.start(this.tickCount);
                } else if (cur == ATTACK_STING) {
                    stingState.start(this.tickCount);
                }
                lastClientAttackState = cur;
            }
        }
    }

    public void setAttackState(byte state) {
        this.entityData.set(ATTACK_STATE, state);
    }
    public byte getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    /*Boss Bar*/

    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Scorpion"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_12);

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 600)
                .add(Attributes.ARMOR_TOUGHNESS, 15f)
                .add(Attributes.ARMOR, 40f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 3.0f)
                .add(Attributes.ATTACK_DAMAGE, 50)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F)
                .add(ModAttributes.PERCENT_REGEN.get(), 0.05D)
                .add(Attributes.ATTACK_SPEED, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, ATTACK_IDLE);
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

        this.goalSelector.addGoal(1, new ScorpionAttackGoal(this, 1.0D));

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
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth()); // keep regen + handle 40% phase
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
    // helper for sounds (optional)
    public void playPinchSound() { this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0f, 1.0f); }
    public void playStingSound() { this.playSound(SoundEvents.BEE_STING, 1.0f, 1.0f); }
}
