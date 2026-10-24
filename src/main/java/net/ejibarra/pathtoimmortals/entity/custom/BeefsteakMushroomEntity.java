package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ai.KeepDistanceFromTargetGoal;
import net.ejibarra.pathtoimmortals.entity.ai.SummonMinionsGoal;
import net.ejibarra.pathtoimmortals.entity.ai.WeightedMeleeAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.HashMap;
import java.util.Map;

public class BeefsteakMushroomEntity extends Monster {

    public final AnimationState idleState = new AnimationState();
    public final AnimationState walkState = new AnimationState();
    public final AnimationState jabState = new AnimationState();
    public final AnimationState punchState = new AnimationState();
    public final AnimationState kickState = new AnimationState();

    // === public API for client/model ===
    public enum Move { NONE, JAB, POWER_PUNCH, KICK }
    public Move getMove() {
        int i = this.entityData.get(DATA_MOVE);
        Move[] v = Move.values();
        return (i >= 0 && i < v.length) ? v[i] : Move.NONE;
    }
    public boolean isAttacking() { return this.entityData.get(DATA_ATTACKING); }
    private Move lastMoveClient = Move.NONE;

    // === per-move specs ===
    public static final class Spec {
        public final float dmgMult;
        public final float knockback;
        public final double reach;
        public final int cooldown;
        public final int duration;
        public final int[] hitTicks;
        public Spec(float d, float k, double r, int cd, int dur, int... hits) {
            this.dmgMult = d; this.knockback = k; this.reach = r; this.cooldown = cd; this.duration = dur; this.hitTicks = hits;
        }
    }
    private static final Map<Move, Spec> ATTACK_SPECS = new HashMap<>();
    static {
        // Durations from your AnimationDefinition lengths: JAB=20t (1.0s), POWER_PUNCH=20t, KICK=40t (2.0s)
        ATTACK_SPECS.put(Move.JAB,         new Spec(1.00f, 0.50f, 3.0,  6, 20, 15));
        ATTACK_SPECS.put(Move.POWER_PUNCH, new Spec(1.50f, 1.00f, 3.2, 10, 20, 15));
        ATTACK_SPECS.put(Move.KICK,        new Spec(2.00f, 2.35f, 3.5, 12, 40, 20));
    }

    // === synced data ===
    private static final EntityDataAccessor<Integer> DATA_MOVE =
            SynchedEntityData.defineId(BeefsteakMushroomEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_ATTACKING =
            SynchedEntityData.defineId(BeefsteakMushroomEntity.class, EntityDataSerializers.BOOLEAN);

    private int attackTicks = 0;
    private int globalCooldown = 0;

    public BeefsteakMushroomEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 100;
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MOVE, Move.NONE.ordinal());
        this.entityData.define(DATA_ATTACKING, false);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 250)
                .add(Attributes.ARMOR_TOUGHNESS, 25f)
                .add(Attributes.ARMOR, 40f)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ATTACK_KNOCKBACK, 2.5f)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.MOVEMENT_SPEED, (double)0.33F)
                .add(Attributes.ATTACK_SPEED, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 4f));

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new WeightedMeleeAttackGoal(this, 1.1D, true));

        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            updateClientAnimations(); // <-- drives AnimationState start/stop on the client only
            return;
        }

        if (globalCooldown > 0) globalCooldown--;

        if (isAttacking()) {
            attackTicks++;
            Move mv = getMove();
            Spec spec = ATTACK_SPECS.get(mv);
            if (spec != null) {
                for (int ht : spec.hitTicks) {
                    if (attackTicks == ht) doHit(spec);
                }
                if (attackTicks >= spec.duration) endAttack(spec.cooldown);
            } else {
                endAttack(6);
            }
        }
    }

    private void updateClientAnimations() {
        // If attacking: stop baseline loops and run ONLY the current attack
        if (isAttacking()) {
            // stop baseline
            idleState.stop();
            walkState.stop();

            Move m = getMove();
            if (m != lastMoveClient) {
                // stop any previously running attack
                jabState.stop();
                punchState.stop();
                kickState.stop();

                switch (m) {
                    case JAB          -> jabState.startIfStopped(this.tickCount);
                    case POWER_PUNCH  -> punchState.startIfStopped(this.tickCount);
                    case KICK         -> kickState.startIfStopped(this.tickCount);
                    default -> {}
                }
                lastMoveClient = m;
            }
            return; // IMPORTANT: don’t re-start idle/walk during attacks
        }

        // Not attacking: ensure all attack states are off
        jabState.stop(); punchState.stop(); kickState.stop();
        lastMoveClient = Move.NONE;

        // Baseline: pick ONE of idle / walk
        float horiz = (float) this.getDeltaMovement().horizontalDistance();
        boolean moving = horiz > 0.01f;

        if (moving) {
            if (!walkState.isStarted()) walkState.startIfStopped(this.tickCount);
            idleState.stop();
        } else {
            if (!idleState.isStarted()) idleState.startIfStopped(this.tickCount);
            walkState.stop();
        }
    }

    // === externally call to start an attack ===
    public void beginAttack(Move move) {
        if (isAttacking() || globalCooldown > 0) return;
        this.attackTicks = 0;
        this.entityData.set(DATA_MOVE, move.ordinal());
        this.entityData.set(DATA_ATTACKING, true);
    }

    // === simple weighted chooser you can call from your Goal ===
    public Move chooseWeightedMove(RandomSource r, Move last) {
        int roll = r.nextInt(100); // 0..99
        Move choice = (roll < 40) ? Move.JAB : (roll < 75) ? Move.POWER_PUNCH : Move.KICK;
        if ((choice == Move.POWER_PUNCH || choice == Move.KICK) && choice == last && r.nextFloat() < 0.5F) {
            choice = Move.JAB;
        }
        return choice;
    }

    // === internals ===
    private void endAttack(int cooldown) {
        this.entityData.set(DATA_ATTACKING, false);
        this.entityData.set(DATA_MOVE, Move.NONE.ordinal());
        this.attackTicks = 0;
        this.globalCooldown = cooldown;
    }

    private void doHit(Spec spec) {
        LivingEntity target = this.getTarget();
        if (target == null) return;

        if (this.distanceToSqr(target) <= spec.reach * spec.reach && this.getSensing().hasLineOfSight(target)) {
            float base = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float dmg = base * spec.dmgMult;

            if (target.hurt(this.damageSources().mobAttack(this), dmg)) {
                target.knockback(spec.knockback,
                        Mth.sin(this.getYRot() * (float)Math.PI / 180F),
                        -Mth.cos(this.getYRot() * (float)Math.PI / 180F));
                this.doEnchantDamageEffects(this, target);
            }
        }
    }

    //Loot table
    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("pathtoimmortals", "entities/beefsteak_mushroom");
    }
}
