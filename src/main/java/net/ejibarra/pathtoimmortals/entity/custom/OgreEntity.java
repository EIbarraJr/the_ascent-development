package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.attributes.ModAttributes;
import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.ai.OgreAttackGoal;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
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

public class OgreEntity extends Monster implements Enemy {
    public OgreEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired(); // vanilla flag: never despawn naturally
        this.xpReward = 100;
    }
    // --- client-side animation state holders ---
    // ---- animation states (client uses these) ----
    public final AnimationState idleState       = new AnimationState();
    public final AnimationState screamState       = new AnimationState();
    public final AnimationState whackState      = new AnimationState();
    public final AnimationState slamState   = new AnimationState();
    public final AnimationState walkState = new AnimationState();// loop while true

    // --- Attack states for your renderer/model ---
    public static final byte ATTACK_NONE  = 0;
    public static final byte ATTACK_WACK  = 1; // single-target quick hit
    public static final byte ATTACK_SLAM  = 2; // AOE ground slam

    private static final EntityDataAccessor<Byte> ATTACK_STATE =
            SynchedEntityData.defineId(OgreEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> WINDUP_TICKS_LEFT =
            SynchedEntityData.defineId(OgreEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIM_TICKS_LEFT =
            SynchedEntityData.defineId(OgreEntity.class, EntityDataSerializers.INT);

    public void setAttackState(byte state, int windupTicks, int animTicks) {
        this.entityData.set(ATTACK_STATE, state);
        this.entityData.set(WINDUP_TICKS_LEFT, windupTicks);
        this.entityData.set(ANIM_TICKS_LEFT, animTicks);
    }
    public int  getAnimTicksLeft() { return this.entityData.get(ANIM_TICKS_LEFT); }
    public byte getAttackState() { return this.entityData.get(ATTACK_STATE); }
    public int  getWindupTicksLeft() { return this.entityData.get(WINDUP_TICKS_LEFT); }
    private byte lastClientAttackState = ATTACK_NONE;

    private void driveClientAnimations() {
        byte state = getAttackState();

        if (state != lastClientAttackState) {
            // stop all, then start the right one
            idleState.stop(); screamState.stop(); whackState.stop(); slamState.stop();
            if (state == ATTACK_WACK)       whackState.start(this.tickCount);
            else if (state == ATTACK_SLAM)  slamState.start(this.tickCount);
            else                             idleState.start(this.tickCount);
            lastClientAttackState = state;
        }
    }

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
        }

        // Count down both timers every tick (runs on both sides safely)
        int wind = getWindupTicksLeft();
        int anim = getAnimTicksLeft();

        if (wind > 0) this.entityData.set(WINDUP_TICKS_LEFT, wind - 1);
        if (anim > 0) this.entityData.set(ANIM_TICKS_LEFT,  anim - 1);

        // Only clear ATTACK_STATE when the *animation* is done (not at impact)
        if (getAnimTicksLeft() <= 0 && getAttackState() != ATTACK_NONE) {
            this.entityData.set(ATTACK_STATE, ATTACK_NONE);
        }

        if (level().isClientSide) {
            driveClientAnimations();
        }
    }

    // === OgreEntity.java additions ===
    private static final float ENRAGE_THRESHOLD = 0.40f; // 40% HP
    private static final int   REGEN4_TICKS = 220;       // ~11s, just keep reapplying
    private static final int   REGEN6_TICKS = 220;

    private static final UUID ENRAGE_DAMAGE_UUID = UUID.fromString("8e13d2bf-0c9f-41a4-9b8f-0a5b5a1b1f55"); // any stable UUID
    private static final UUID ENRAGED_REGEN_UUID = UUID.fromString("c989f516-0f7d-4e0c-9a35-0d2bc2e2e3c1");


    private boolean enraged = false; // server-side phase flag

    /** Call every tick to maintain regen + check phase. */
    private void updateRegenAndPhase() {
        if (level().isClientSide) return;

        float hpRatio = this.getHealth() / this.getMaxHealth();

        // --- Maintain baseline Regen II ---
        /*MobEffectInstance cur = this.getEffect(MobEffects.REGENERATION);
        int wantAmp = enraged ? 20 : 6; // amp: 1=Regen VII, 2=Regen XXI
        int wantDur = enraged ? REGEN4_TICKS : REGEN6_TICKS;

        // If missing OR weaker OR about to expire, (re)apply
        if (cur == null || cur.getAmplifier() < wantAmp || cur.getDuration() < 60) {
            this.addEffect(new MobEffectInstance(
                    MobEffects.REGENERATION, wantDur, wantAmp, true, true, true));
        }*/

        // --- Phase switch at 40% ---
        if (!enraged && hpRatio <= ENRAGE_THRESHOLD) {
            enterEnraged();
        } else if (enraged && hpRatio > ENRAGE_THRESHOLD + 0.03f) {
            // small hysteresis to avoid rapid toggling
            exitEnraged();
        }
    }

    private void enterEnraged() {
        enraged = true;
        bossEvent.setColor(BossEvent.BossBarColor.RED);

        // Upgrade to Regen III immediately
        //this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, REGEN6_TICKS, 5, true, true, true));

        // Add damage modifier (+35% total attack damage)
        AttributeInstance atk = this.getAttribute(Attributes.ATTACK_DAMAGE);
        if (atk != null && atk.getModifier(ENRAGE_DAMAGE_UUID) == null) {
            atk.addPermanentModifier(new AttributeModifier(
                    ENRAGE_DAMAGE_UUID, "ogre.enraged.damage",
                    0.35D, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        AttributeInstance increasedRegen = this.getAttribute(ModAttributes.PERCENT_REGEN.get());
        if (increasedRegen != null && increasedRegen.getModifier(ENRAGED_REGEN_UUID) == null){
            increasedRegen.addPermanentModifier(new AttributeModifier(
                    ENRAGED_REGEN_UUID, "ogre.enraged.regen", 0.2D, AttributeModifier.Operation.ADDITION));
        }

        // Optional: sound/particles + scream animation state
        this.playSound(SoundEvents.RAVAGER_ROAR, 1.0f, 0.75f + this.getRandom().nextFloat() * 0.25f);
        if (level() instanceof ServerLevel sl) {
            sl.sendParticles(ParticleTypes.ANGRY_VILLAGER, getX(), getY() + 2.2, getZ(), 12, 0.6, 0.4, 0.6, 0.01);
        }
        // If you want to visually show it:
        if (level().isClientSide) this.screamState.start(this.tickCount);
    }

    private void exitEnraged() {
        enraged = false;
        bossEvent.setColor(BossEvent.BossBarColor.YELLOW);

        // Remove damage bonus
        AttributeInstance atk = this.getAttribute(Attributes.ATTACK_DAMAGE);
        if (atk != null && atk.getModifier(ENRAGE_DAMAGE_UUID) != null) {
            atk.removeModifier(ENRAGE_DAMAGE_UUID);
        }
        // Regen will be maintained at II by updateRegenAndPhase()
    }

    // Expose whether enraged for other code (e.g., renderer/goal)
    public boolean isEnraged() { return enraged; }

    // If you want your custom goal damage to scale with phase:
    public float getWhackDamage() { return enraged ? 55.0F : 110.0F; }
    public float getSlamDamage()  { return enraged ? 69.0F : 169.0F; }

    /*Boss Bar*/

    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Ogre"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_12);

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 1200)
                .add(Attributes.ARMOR_TOUGHNESS, 15f)
                .add(Attributes.ARMOR, 40f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 3.0f)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.MOVEMENT_SPEED, (double)0.28F)
                .add(ModAttributes.PERCENT_REGEN.get(), 0.075D)
                .add(Attributes.ATTACK_SPEED, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, ATTACK_NONE);
        this.entityData.define(WINDUP_TICKS_LEFT, 0);
        this.entityData.define(ANIM_TICKS_LEFT, 0);
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

        this.goalSelector.addGoal(1, new OgreAttackGoal(this, 1.1D, true));

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
        updateRegenAndPhase(); // keep regen + handle 40% phase
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
}
