package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.ai.CastProjectileGoal;
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

public class DarklinEntity extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> CHARGING =
            SynchedEntityData.defineId(DarklinEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleDarklinState = new AnimationState();
    private int idleDarklinTimeout = 0;

    public final AnimationState darklinAttackingState = new AnimationState();
    public int darklinAttackingTimeout;

    /*Boss Bar*/
    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("Darklin"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_12);

    public DarklinEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 50;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)
                .add(Attributes.ARMOR, 20f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 2.2f)
                .add(Attributes.ATTACK_DAMAGE, 23)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    private void setupAnimationStates() {
        if (this.idleDarklinTimeout <= 0) {
            this.idleDarklinTimeout = this.random.nextInt(40) + 80;
            this.idleDarklinState.start(this.tickCount);
        } else {
            --this.idleDarklinTimeout;
        }

        if (this.isCharging() && darklinAttackingTimeout <= 0){
            darklinAttackingTimeout = 41;
            darklinAttackingState.start(this.tickCount);
        } else {
            --this.darklinAttackingTimeout;
        }

        if (!this.isCharging()){
            darklinAttackingState.stop();
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.45F);
    }

    @Override
    public void tick() {
        super.tick();

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
        DaoCoreProjectile proj = new DaoCoreProjectile(ModEntities.DAO_CORE_PROJECTILE.get(), level());
        // 2) Set owner and spawn position (from shooter’s eyes)
        proj.setOwner(this);
        proj.setPos(this.getX(), this.getEyeY() - 0.1, this.getZ());

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
   // public boolean isCasting() { return this.entityData.get(ATTACKING); }
    //public void setCasting(boolean v) { this.entityData.set(ATTACKING, v); }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new CastProjectileGoal(this, 64, 40, 20, 40, 80));

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
        LivingEntity t = getTarget();
        if (t != null && this.distanceToSqr(t) >= 49.0 * 49.0) {
            setTarget(null);
            getNavigation().stop();
        }
    }
}
