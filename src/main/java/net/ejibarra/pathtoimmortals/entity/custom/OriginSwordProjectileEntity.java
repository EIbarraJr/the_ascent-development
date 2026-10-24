package net.ejibarra.pathtoimmortals.entity.custom;

import net.ejibarra.pathtoimmortals.enchantment.GrievousWoundsEvents;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class OriginSwordProjectileEntity extends ThrowableItemProjectile {
    private static final int MAX_LIFETIME_TICKS = 60; // 2 seconds

    // tier scaling (server sets right after spawn)
    private float damageMultiplier = 1.0F;

    public void setDamageMultiplier(float mult) {
        if (Float.isNaN(mult) || Float.isInfinite(mult) || mult <= 0.0F) mult = 1.0F;
        this.damageMultiplier = mult;
    }

    public OriginSwordProjectileEntity(EntityType<? extends OriginSwordProjectileEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public OriginSwordProjectileEntity(Level level, LivingEntity owner) {
        super(ModEntities.ORIGIN_SWORD_PROJECTILE.get(), owner, level);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();  // let ThrowableItemProjectile handle movement + collision

        //Client Side Particle
        if(this.level().isClientSide){
            spawnTrailParticles();
        }



        // Lifetime kill
        if (!this.level().isClientSide && this.tickCount > MAX_LIFETIME_TICKS) {
            this.discard();
        }
    }

    @Override
    protected float getGravity() {
        return 0.0F; // straight line, no drop
    }

    // ==== HIT LOGIC ON ENTITY ====

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (this.level().isClientSide) return;

        Entity hit = result.getEntity();
        Entity owner = this.getOwner();

        DamageSource src;
        float damage;

        if (owner instanceof LivingEntity livingOwner) {
            // Use indirect magic (like before) so it still counts as ability/magic damage
            src = this.damageSources().indirectMagic(this, livingOwner);

            // === 1. Get total attack damage attribute (base + modifiers) ===
            double rawAttackDamage = livingOwner.getAttributeValue(Attributes.ATTACK_DAMAGE);

            if (rawAttackDamage <= 0) {
                rawAttackDamage = 1.0D; // safety fallback
            }

            /*//Debug
            if (livingOwner instanceof ServerPlayer sp) {
                sp.sendSystemMessage(Component.literal("ATTACK_DAMAGE raw = " + rawAttackDamage));
            }*/

            // === 2. Apply your formula: dmg + 2.75 * dmg ===
            double abilityDamage = rawAttackDamage + 1.25 * rawAttackDamage;
            // double abilityDamage = rawAttackDamage * 3.75;  // same thing, shorter

            damage = (float) abilityDamage;
            // apply tier scaling
            damage *= this.damageMultiplier;
        } else {
            // No living owner? use a default so it still does something.
            src = this.damageSources().magic();
            damage = 1.0F;
        }

        hit.hurt(src, damage);

        if (hit instanceof LivingEntity livingTarget) {
            // 👉 Apply Grievous Wound I via your event system
            GrievousWoundsEvents.applyGrievousWound(livingTarget, 1);

            // 👉 Apply Bleed I if your Bleed is a normal MobEffect
            //int bleedDuration = 1 * 20; // 8 seconds
            //BleedSystem.applyBleed(livingTarget, 1, bleedDuration);
        }

        this.discard();
    }

    // ==== HIT LOGIC ON BLOCK / ANYTHING ELSE ====

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    // ==== NETWORK SPAWN PACKET ====

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    // ==== HELPER: SPAWN TO RIGHT OF PLAYER + SHOOT FORWARD ====

    public static void shootFromPlayer(ServerLevel level, Player player, float dmgMult) {
        OriginSwordProjectileEntity sword = new OriginSwordProjectileEntity(ModEntities.ORIGIN_SWORD_PROJECTILE.get(), level);
        sword.setOwner(player);

        sword.setDamageMultiplier(dmgMult);

        Vec3 look = player.getLookAngle().normalize();
        Vec3 up   = new Vec3(0, 1, 0);//0,1,0
        Vec3 right = up.cross(look).normalize();

        Vec3 startPos = player.position()
                .add(0, player.getEyeHeight() * 0.8, 0)//0, 0.75,
                .add(right.scale(0.0))
                .add(look.scale(1.75));

        sword.setPos(startPos.x, startPos.y, startPos.z);

        float speed = 2.1F;
        float inaccuracy = 0.0F;
        sword.shoot(look.x, look.y, look.z, speed, inaccuracy);

        level.addFreshEntity(sword);
    }

    private void spawnTrailParticles() {
        // How many particles per tick (tweak)
        int count = 2;

        // Your current motion vector
        var v = this.getDeltaMovement();

        // Spawn slightly behind the projectile (opposite of motion)
        double backX = -v.x * 0.35;
        double backY = -v.y * 0.35;
        double backZ = -v.z * 0.35;

        for (int i = 0; i < count; i++) {
            // small random jitter so it's not a perfectly straight line
            double jx = (this.random.nextDouble() - 0.5) * 0.06;
            double jy = (this.random.nextDouble() - 0.5) * 0.06;
            double jz = (this.random.nextDouble() - 0.5) * 0.06;

            this.level().addParticle(
                    ParticleTypes.END_ROD,
                    this.getX() + backX + jx,
                    this.getY() + this.getBbHeight() * 0.5 + backY + jy,
                    this.getZ() + backZ + jz,
                    0.0, 0.0, 0.0   // particle initial velocity
            );
        }
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }
}
