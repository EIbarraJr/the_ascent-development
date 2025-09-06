package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class CheeseProjectileEntity extends Projectile {
    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(CheeseProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private int counter = 0;

    // If you want client to know damage for VFX, use an EntityDataAccessor<Float> instead.
    private float baseDamage = 30f; // default, overridden by shooter

    public void setBaseDamage(float dmg) { this.baseDamage = dmg; }
    public float getBaseDamage() { return this.baseDamage; }

    public CheeseProjectileEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    // Prefer a LivingEntity ctor so any mob can shoot it
    public CheeseProjectileEntity(Level level, LivingEntity shooter) {
        this(ModEntities.CHEESE_PROJECTILE.get(), level);
        setOwner(shooter);
        setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(HIT, false);
        // If you want client sync for damage:
        // this.entityData.define(DAMAGE, 30f);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("BaseDamage", this.baseDamage);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("BaseDamage")) this.baseDamage = tag.getFloat("BaseDamage");
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entityData.get(HIT)) {
            if(this.tickCount >= counter) {
                this.discard();
            }
        }

        if (this.tickCount >= 300) {
            this.remove(RemovalReason.DISCARDED);
        }

        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, hitresult))
            this.onHit(hitresult);

        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;

        for(int i = 1; i < 5; ++i) {
            this.level().addParticle(ParticleTypes.DRIPPING_HONEY, d0-(d5*2), d1-(d6*2), d2-(d7*2),
                    -d5, -d6 - 0.1D, -d7);
        }


        if (this.level().getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        } else if (this.isInWaterOrBubble()) {
            this.discard();
        } else {
            this.setDeltaMovement(vec3.scale(0.99F));
            this.setPos(d0, d1, d2);
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity hitEntity = hitResult.getEntity();
        Entity owner = this.getOwner();

        if (hitEntity == owner && this.level().isClientSide()) return;

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 4F, 1F);

        LivingEntity shooter = owner instanceof LivingEntity le ? le : null;


        // 🔧 use configurable damage instead of hardcoded 30f
        float damage = this.baseDamage;

        boolean hurt = hitEntity.hurt(this.damageSources().mobProjectile(this, shooter), damage);
        if (hurt && hitEntity instanceof LivingEntity livingHit) {
            livingHit.addEffect(new MobEffectInstance(ModEffects.CHEESE_TOUCH.get(), 20*15, 0), owner);
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.level().addParticle(ParticleTypes.FALLING_HONEY, this.getX(), this.getY(), this.getZ(),
                        Math.cos(x*20) * 0.15d, Math.cos(y*20) * 0.15d, Math.sin(x*20) * 0.15d);
            }
        }

        if(this.level().isClientSide()) {
            return;
        }

        if(hitResult.getType() == HitResult.Type.ENTITY && hitResult instanceof EntityHitResult entityHitResult) {
            Entity hit = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            if(owner != hit) {
                this.entityData.set(HIT, true);
                counter = this.tickCount + 5;
            }
        } else {
            this.entityData.set(HIT, true);
            counter = this.tickCount + 5;
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
