package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ai.FungiSwordsmanAttackGoal;
import net.ejibarra.pathtoimmortals.entity.ai.HereticAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
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

import javax.annotation.Nullable;
import java.util.UUID;

public class FungiSwordsmanEntity extends Monster implements Enemy {
    private static final String NBT_SUMMONER = "Summoner";
    @Nullable
    private UUID summonerUUID;

    // --- NEW: summoner API ---
    public void setSummoner(SporeSpreaderEntity owner) { this.summonerUUID = owner.getUUID(); }
    @Nullable public UUID getSummonerUUID() { return this.summonerUUID; }

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(FungiSwordsmanEntity.class, EntityDataSerializers.BOOLEAN);

    /*public final AnimationState idleHereticState = new AnimationState();
    private int idleHereticTimeout = 0;*/

    public final AnimationState fungiAttackingState = new AnimationState();
    public int fungiAttackingTimeout;
    public final AnimationState walkState = new AnimationState();

    /*Boss Bar*/
    /*private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Heretic Pawn"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_12);*/ //can reenable here and three spots in the bottom

    public FungiSwordsmanEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 25;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR_TOUGHNESS, 12f)
                .add(Attributes.ARMOR, 15f)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0f)
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.MOVEMENT_SPEED, (double)0.30F)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    private void setupAnimationStates() {
        // Handle attack stab
        if (this.isAttacking()) {
            if (fungiAttackingTimeout <= 0) {
                fungiAttackingTimeout = 20; // length of stab anim (~1s)
                fungiAttackingState.start(this.tickCount);
            }
            fungiAttackingTimeout--;
        } else {
            fungiAttackingState.stop();
        }

        // Handle walk state
        if (this.walkAnimation.speed() > 0.01f && !this.isAttacking()) {
            walkState.startIfStopped(this.tickCount);
        } else {
            walkState.stop();
        }
    }
    @Override
    public void tick() {
        super.tick();
        if (fungiAttackingTimeout > 0) fungiAttackingTimeout--;
        if (fungiAttackingTimeout <= 0) setAttacking(false);
    }
    public void triggerStab() {
        this.setAttacking(true);
        this.fungiAttackingTimeout = 20; // ~1s
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

        this.goalSelector.addGoal(1, new FungiSwordsmanAttackGoal(this, 1.2D, true));

        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

        // Ignore our summoner (and spectators), still attack players & golems
        java.util.function.Predicate<net.minecraft.world.entity.LivingEntity> notSummoner =
                le -> !(le instanceof SporeSpreaderEntity s) || !java.util.Objects.equals(s.getUUID(), this.summonerUUID);

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
        setupAnimationStates();
        //this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (summonerUUID != null) tag.putUUID(NBT_SUMMONER, summonerUUID);
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID(NBT_SUMMONER)) this.summonerUUID = tag.getUUID(NBT_SUMMONER);
    }

    //Loot table
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("pathtoimmortals", "entities/fungi_swordsman");
    }
}
