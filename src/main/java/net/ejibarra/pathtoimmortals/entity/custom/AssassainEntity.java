package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ai.AssassainAttackGoal;
import net.ejibarra.pathtoimmortals.entity.ai.WeightedMeleeAttackGoal;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class AssassainEntity extends Monster {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(AssassainEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleState = new AnimationState();
    public final AnimationState walkState = new AnimationState();
    public final AnimationState attackState = new AnimationState();

    //private int attackTimeout = 0;

    public AssassainEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 100;
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F); // Never drop sword
    }

    // ==== Attributes ====
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D)
                .add(Attributes.ARMOR, 12.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }

    // ==== Synced ATTACKING flag ====
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        //this.goalSelector.addGoal(1, new WeightedMeleeAttackGoal(this, 1.1D, true));
        this.goalSelector.addGoal(1, new AssassainAttackGoal(this, 1.1D, true));

        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    private enum HumanGearTier {
        LOWER,
        MEDIUM,
        UPPER,
        DIVINE
    }

    private HumanGearTier getRandomGearTier(RandomSource random, DifficultyInstance difficulty) {
        // You can tune weights however you like
        float f = random.nextFloat();

        // Example weights:
        // 0.0 – 0.55  -> LOWER (55%)
        // 0.55 – 0.80 -> MEDIUM (25%)
        // 0.80 – 0.95 -> UPPER (15%)
        // 0.95 – 1.00 -> DIVINE (5%)
        if (f < 0.55F) {
            return HumanGearTier.LOWER;
        } else if (f < 0.80F) {
            return HumanGearTier.MEDIUM;
        } else if (f < 0.95F) {
            return HumanGearTier.UPPER;
        } else {
            return HumanGearTier.DIVINE;
        }
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        HumanGearTier tier = getRandomGearTier(random, difficulty);

        switch (tier) {
            case LOWER -> {
                // Sword
                this.setItemSlot(EquipmentSlot.MAINHAND,
                        new ItemStack(ModItems.LOWER_HUMAN_GRADE_SABER.get()));

                // Armor
                this.setItemSlot(EquipmentSlot.HEAD,
                        new ItemStack(ModItems.HUMAN_GRADE_HELMET.get()));
                this.setItemSlot(EquipmentSlot.CHEST,
                        new ItemStack(ModItems.HUMAN_GRADE_CHESTPLATE.get()));
                this.setItemSlot(EquipmentSlot.LEGS,
                        new ItemStack(ModItems.HUMAN_GRADE_LEGGINGS.get()));
                this.setItemSlot(EquipmentSlot.FEET,
                        new ItemStack(ModItems.HUMAN_GRADE_BOOTS.get()));
            }
            case MEDIUM -> {
                this.setItemSlot(EquipmentSlot.MAINHAND,
                        new ItemStack(ModItems.MEDIUM_HUMAN_GRADE_SABER.get()));

                this.setItemSlot(EquipmentSlot.HEAD,
                        new ItemStack(ModItems.MEDIUM_HUMAN_GRADE_HELMET.get()));
                this.setItemSlot(EquipmentSlot.CHEST,
                        new ItemStack(ModItems.MEDIUM_HUMAN_GRADE_CHESTPLATE.get()));
                this.setItemSlot(EquipmentSlot.LEGS,
                        new ItemStack(ModItems.MEDIUM_HUMAN_GRADE_LEGGINGS.get()));
                this.setItemSlot(EquipmentSlot.FEET,
                        new ItemStack(ModItems.MEDIUM_HUMAN_GRADE_BOOTS.get()));
            }
            case UPPER -> {
                this.setItemSlot(EquipmentSlot.MAINHAND,
                        new ItemStack(ModItems.UPPER_HUMAN_GRADE_SABER.get()));

                this.setItemSlot(EquipmentSlot.HEAD,
                        new ItemStack(ModItems.UPPER_HUMAN_GRADE_HELMET.get()));
                this.setItemSlot(EquipmentSlot.CHEST,
                        new ItemStack(ModItems.UPPER_HUMAN_GRADE_CHESTPLATE.get()));
                this.setItemSlot(EquipmentSlot.LEGS,
                        new ItemStack(ModItems.UPPER_HUMAN_GRADE_LEGGINGS.get()));
                this.setItemSlot(EquipmentSlot.FEET,
                        new ItemStack(ModItems.UPPER_HUMAN_GRADE_BOOTS.get()));
            }
            case DIVINE -> {
                this.setItemSlot(EquipmentSlot.MAINHAND,
                        new ItemStack(ModItems.DIVINE_HUMAN_GRADE_SABER.get()));

                this.setItemSlot(EquipmentSlot.HEAD,
                        new ItemStack(ModItems.DIVINE_HUMAN_GRADE_HELMET.get()));
                this.setItemSlot(EquipmentSlot.CHEST,
                        new ItemStack(ModItems.DIVINE_HUMAN_GRADE_CHESTPLATE.get()));
                this.setItemSlot(EquipmentSlot.LEGS,
                        new ItemStack(ModItems.DIVINE_HUMAN_GRADE_LEGGINGS.get()));
                this.setItemSlot(EquipmentSlot.FEET,
                        new ItemStack(ModItems.DIVINE_HUMAN_GRADE_BOOTS.get()));
            }
        }

        // Optional: no offhand at all
        this.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);

        // Optional: control drop chances (0.0F = never, 1.0F = always)
        this.setDropChance(EquipmentSlot.MAINHAND, 0.05F); // 5% sword drop
        this.setDropChance(EquipmentSlot.HEAD, 0.02F);
        this.setDropChance(EquipmentSlot.CHEST, 0.02F);
        this.setDropChance(EquipmentSlot.LEGS, 0.02F);
        this.setDropChance(EquipmentSlot.FEET, 0.02F);
    }

    @Override
    protected void populateDefaultEquipmentEnchantments(RandomSource random, DifficultyInstance difficulty) {
        // you can enchant sword/armor here, similar to Zombie
        super.populateDefaultEquipmentEnchantments(random, difficulty);
    }

    // ==== Animation driver (client-side) ====

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            updateAnimationStates();
        }
    }

    private void updateAnimationStates() {
        // --- ATTACK ---
        // Attack should win over everything else
        this.attackState.animateWhen(this.isAttacking(), this.tickCount);

        // --- WALK / IDLE ---
        boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 0.0004D;

        // Only play walk/idle when NOT attacking
        if (!this.isAttacking()) {
            this.walkState.animateWhen(isMoving, this.tickCount);
            this.idleState.animateWhen(!isMoving, this.tickCount);
        } else {
            this.walkState.stop();
            this.idleState.stop();
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty, MobSpawnType reason,
                                        @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        SpawnGroupData data = super.finalizeSpawn(accessor, difficulty, reason, spawnData, tag);
        this.populateDefaultEquipmentSlots(accessor.getRandom(), difficulty);
        this.populateDefaultEquipmentEnchantments(accessor.getRandom(), difficulty);
        return data;
    }

    @Override
    public HumanoidArm getMainArm() {
        // Always right-handed
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean isLeftHanded() {
        // Just to be extra explicit
        return false;
    }

    //Loot table
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("pathtoimmortals", "entities/assassain");
    }
}
