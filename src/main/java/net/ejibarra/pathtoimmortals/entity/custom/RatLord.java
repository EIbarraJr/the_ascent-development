package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ModEntities;

import net.ejibarra.pathtoimmortals.entity.ai.RangedChargeShootGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class RatLord extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> CHARGING =
            SynchedEntityData.defineId(RatLord.class, EntityDataSerializers.BOOLEAN);

    // --- client-side animation state holders ---
    // ---- animation states (client uses these) ----
    public final AnimationState idleState       = new AnimationState();
    public final AnimationState chargingState   = new AnimationState();
    public final AnimationState cheeseCastState = new AnimationState();
    public final AnimationState swipeState      = new AnimationState();
    public final AnimationState walkState = new AnimationState();
    public final AnimationState uppercutState   = new AnimationState();       // loop while true

    // ---- tiny event IDs sent from server → client ----
    private static final byte EVT_RANGED_CAST = 5;
    private static final byte EVT_SWIPE       = 6;
    private static final byte EVT_UPPERCUT    = 7;

    public void triggerSwipeAnim()   { this.level().broadcastEntityEvent(this, EVT_SWIPE); }
    public void triggerUppercutAnim(){ this.level().broadcastEntityEvent(this, EVT_UPPERCUT); }
    public void triggerRangedCastAnim(){ this.level().broadcastEntityEvent(this, EVT_RANGED_CAST); }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == EVT_RANGED_CAST) { this.cheeseCastState.start(this.tickCount); return; }
        if (id == EVT_SWIPE)       { this.swipeState.start(this.tickCount);      return; }
        if (id == EVT_UPPERCUT)    { this.uppercutState.start(this.tickCount);    return; }
        super.handleEntityEvent(id);
    }

    @Override
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
        if (this.level().isClientSide) {
            this.idleState.startIfStopped(this.tickCount);
            if (this.isCharging()) this.chargingState.startIfStopped(this.tickCount);
            else this.chargingState.stop();
        }
    }

    public void setCharging(boolean charging){this.entityData.set(CHARGING, charging);}
    public boolean isCharging(){return  this.entityData.get(CHARGING);}
    /*Boss Bar*/

    private final ServerBossEvent bossEvent=
            new ServerBossEvent(Component.literal("The Rat King"), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_12);

    public RatLord(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPersistenceRequired(); // vanilla flag: never despawn naturally
        this.xpReward = 50;
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.ARMOR_TOUGHNESS, 12f)
                .add(Attributes.ARMOR, 55f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 2.2f)
                .add(Attributes.ATTACK_DAMAGE, 50)
                .add(Attributes.MOVEMENT_SPEED, (double)0.28F)
                .add(Attributes.ATTACK_SPEED, 1.3);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHARGING, false);
    }

    // RangedAttackMob contract (called from the goal when it’s time to shoot)
    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        CheeseProjectileEntity proj = new CheeseProjectileEntity(ModEntities.CHEESE_PROJECTILE.get(), level());
        // 2) Set owner and spawn position (from shooter’s eyes)
        proj.setOwner(this);
        proj.setPos(this.getX() , this.getEyeY() - 0.1, this.getZ());


        float dmg = 60f;
        proj.setBaseDamage(dmg);

        // 3) Now aim from the projectile’s position to the target
        double dx = target.getX()     - proj.getX();
        double dy = target.getEyeY()  - proj.getY();
        double dz = target.getZ()     - proj.getZ();

        // 4) Launch
        proj.shoot(dx, dy, dz, 1.80f, 0.2f);

        // 5) Spawn server-side
        level().addFreshEntity(proj);

        this.playSound(net.minecraft.sounds.SoundEvents.BLAZE_SHOOT, 1.0f, 1.0f);
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

        this.goalSelector.addGoal(1, new net.ejibarra.pathtoimmortals.entity.ai.RatLordAttackGoal(
                this,
                1.2,   // move speed while chasing
                10.0,  // farther than this → force ranged
                2.5,   // melee reach (blocks)
                10,    // global cooldown between attacks
                10,    // melee wind-up
                5,     // melee aftercast
                10,    // ranged wind-up (charging pose)
                5      // ranged aftercast
        ));
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

        //new
        if (!this.level().isClientSide) {
            float pct = this.getHealth() / this.getMaxHealth();

            if (!spawned25 && pct <= 0.25f) {
                spawnReinforcements(3, ModEntities.HOODEDRAT.get()); // <- replace with your minion type
                spawnReinforcements(5, ModEntities.SPIKEDRAT.get()); // <- replace with your minion type
                spawned25 = true;
            }
            /*if (!spawned10 && pct <= 0.10f) {
                spawnReinforcements(5, ModEntities.RAT_MINION.get()); // <- or a different type
                spawned10 = true;
            }*/
        }
    }

    // fields in RatLord
    private boolean spawned25 = false;
    private boolean spawned10 = false;

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Spawned25", spawned25);
        tag.putBoolean("Spawned10", spawned10);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        spawned25 = tag.getBoolean("Spawned25");
        spawned10 = tag.getBoolean("Spawned10");
    }

    private void spawnReinforcements(int count, EntityType<? extends Mob> type) {
        if (!(this.level() instanceof ServerLevel server)) return;
        if (server.getDifficulty() == net.minecraft.world.Difficulty.PEACEFUL) return;

        LivingEntity target = this.getTarget();

        for (int i = 0; i < count; i++) {
            Mob minion = type.create(server);
            if (minion == null) continue;

            BlockPos pos = findSpawnPosNear(server, this.blockPosition(), 4, 8); // 4–8 block ring
            if (pos == null) continue;

            minion.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, this.getYRot(), 0);
            // Only spawn if it actually fits
            if (!server.noCollision(minion)) continue;

            minion.finalizeSpawn(
                    server,
                    server.getCurrentDifficultyAt(pos),
                    MobSpawnType.MOB_SUMMONED,
                    null,
                    null
            );

            if (target != null) minion.setTarget(target);
            server.addFreshEntity(minion);
        }

        // Optional flair
        this.playSound(net.minecraft.sounds.SoundEvents.RAVAGER_ROAR, 1.0f, 1.0f);
    }

    @org.jetbrains.annotations.Nullable
    private BlockPos findSpawnPosNear(ServerLevel server, BlockPos center, int minRadius, int maxRadius) {
        var rand = this.getRandom();
        for (int attempts = 0; attempts < 16; attempts++) {
            double angle = rand.nextDouble() * Math.PI * 2.0;
            int r = minRadius + rand.nextInt(Math.max(1, maxRadius - minRadius + 1));
            int x = center.getX() + net.minecraft.util.Mth.floor(Math.cos(angle) * r);
            int z = center.getZ() + net.minecraft.util.Mth.floor(Math.sin(angle) * r);
            int y = server.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
            BlockPos pos = new BlockPos(x, y, z);

            // Must be air and standable ground beneath
            if (!server.isEmptyBlock(pos)) continue;
            if (server.getBlockState(pos.below()).isAir()) continue;

            return pos;
        }
        return null;
    }


    // Also prevent distance-based removal:
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    //Loot table
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("pathtoimmortals", "entities/rat_lord");
    }
}
