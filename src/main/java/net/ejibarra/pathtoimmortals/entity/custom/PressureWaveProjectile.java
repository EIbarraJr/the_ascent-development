package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.enchantment.GrievousWoundsEvents;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class PressureWaveProjectile extends Projectile {
    private int life;

    // Tier scaling (server sets this right after spawning)
    private float damageMultiplier = 1.0F;

    public PressureWaveProjectile(EntityType<? extends PressureWaveProjectile> type, net.minecraft.world.level.Level level) {
        super(type, level);
        this.noPhysics = false;
    }

    public PressureWaveProjectile(Level level, LivingEntity owner) {
        this(ModEntities.PRESSURE_WAVE.get(), level);
        setOwner(owner);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public void tick() {
        super.tick();

        // lifetime
        life++;
        if (life > 60) { // 2 seconds; you can lower/raise
            discard();
            return;
        }

        // move straight (Forge/vanilla projectile style)
        Vec3 motion = getDeltaMovement();
        Vec3 nextPos = position().add(motion);

        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            onHit(hit);
        }

        setPos(nextPos.x, nextPos.y, nextPos.z);

        // optional: tiny particles trail
        if (level().isClientSide) {
            level().addParticle(ParticleTypes.END_ROD, getX(), getY(), getZ(), 0, 0, 0);
        }

        // keep rotation aligned to velocity (so it “points forward”)
        if (motion.lengthSqr() > 1.0E-7) {
            float yaw = (float)(Math.atan2(motion.x, motion.z) * (180F / Math.PI));
            float pitch = (float)(Math.atan2(motion.y, motion.horizontalDistance()) * (180F / Math.PI));
            setYRot(yaw);
            setXRot(pitch);
            yRotO = yaw;
            xRotO = pitch;
        }
    }

    @Override
    protected void onHit(HitResult hit) {
        super.onHit(hit);

        if (hit.getType() == HitResult.Type.ENTITY) {
            onHitEntity((EntityHitResult) hit);
        } else if (hit.getType() == HitResult.Type.BLOCK) {
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (this.level().isClientSide) return;

        Entity hit = result.getEntity();
        Entity owner = this.getOwner();

        DamageSource src;
        float damage;

        if (owner instanceof LivingEntity livingOwner) {

            // ✅ Physical damage (armor/toughness/prot/shields apply)
            if (livingOwner instanceof Player p) {
                src = this.damageSources().playerAttack(p);//src = this.damageSources().arrow(this, livingOwner); if you want projectile
            } else {
                src = this.damageSources().mobAttack(livingOwner);
            }

            // 1) total ATTACK_DAMAGE (includes weapon modifiers)
            double rawAttackDamage = livingOwner.getAttributeValue(Attributes.ATTACK_DAMAGE);
            if (rawAttackDamage <= 0) rawAttackDamage = 1.0D;

            // 2) Add enchant bonus from MAINHAND weapon (Sharpness/Smite/Bane etc.)
            float enchBonus = 0.0F;
            if (hit instanceof LivingEntity livingTarget) {
                ItemStack weapon = livingOwner.getMainHandItem();
                enchBonus = EnchantmentHelper.getDamageBonus(weapon, livingTarget.getMobType());
            }

            // 3) Pressure Wave formula (tune as you like)
            double basePlusEnch = rawAttackDamage + enchBonus;
            double abilityDamage = basePlusEnch + 0.85D * basePlusEnch;

            damage = (float) abilityDamage;
            damage *= this.damageMultiplier;

        } else {
            // fallback (rare)
            src = this.damageSources().generic();
            damage = 1.0F;
        }

        hit.hurt(src, damage);

        // Status effects like Origin Sword
        if (hit instanceof LivingEntity livingTarget) {
            GrievousWoundsEvents.applyGrievousWound(livingTarget, 1);

            // If your bleed is driven by a system call, do it here:
            // int bleedDuration = 8 * 20;
            // BleedSystem.applyBleed(livingTarget, 1, bleedDuration);
        }

        this.discard();
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double dist) {
        return dist < 64 * 64;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        life = tag.getInt("Life");
        if (tag.contains("DmgMul")) damageMultiplier = tag.getFloat("DmgMul");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("Life", life);
        tag.putFloat("DmgMul", damageMultiplier);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setDamageMultiplier(float mult) {
        // safety clamp so bad values don't explode
        if (Float.isNaN(mult) || Float.isInfinite(mult) || mult <= 0.0F) mult = 1.0F;
        this.damageMultiplier = mult;
    }
}
