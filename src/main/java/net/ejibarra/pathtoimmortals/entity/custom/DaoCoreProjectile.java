package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class DaoCoreProjectile extends Projectile {

    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(DaoCoreProjectile.class, EntityDataSerializers.BOOLEAN);
    private int counter = 0;

    public DaoCoreProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DaoCoreProjectile(Level pLevel, Player player) {
        super(ModEntities.DAO_CORE_PROJECTILE.get(), pLevel);
        setOwner(player);
        BlockPos blockPos = player.blockPosition();
        double d0 = (double)blockPos.getX() + 0.5D;
        double d1 = (double)blockPos.getY() + 1.75D;
        double d2 = (double) blockPos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
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
            this.level().addParticle(ParticleTypes.CLOUD, d0-(d5*2), d1-(d6*2), d2-(d7*2),
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

        if(hitEntity == owner && this.level().isClientSide()) {
            return;
        }

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL,
                4F, 1F);

        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity)owner : null;
        float damage = 1000f;
        boolean hurt = hitEntity.hurt(this.damageSources().mobProjectile(this, livingentity), damage);
        if (hurt) {
            if(hitEntity instanceof LivingEntity livingHitEntity) {
                livingHitEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 3), owner);
                livingHitEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 3), owner);
            }
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!level().isClientSide){
            level().explode(
                    this, getX(),getY(),getZ(), 10.0F, Level.ExplosionInteraction.NONE
            );
        }


        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.level().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(),
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
    protected void defineSynchedData() {
        this.entityData.define(HIT, false);

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
