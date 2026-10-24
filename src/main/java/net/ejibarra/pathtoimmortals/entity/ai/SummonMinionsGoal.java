package net.ejibarra.pathtoimmortals.entity.ai;

import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.custom.FungiSwordsmanEntity;
import net.ejibarra.pathtoimmortals.entity.custom.SporeSpreaderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class SummonMinionsGoal extends Goal {
    private final SporeSpreaderEntity caster;
    private final float triggerRange;     // design range to look for players
    private final int minionsPerCast;     // exact number spawned each cast
    private final int cooldownTicks;      // e.g., 240 = 12s
    private final int scatterRadius;      // not used for counting anymore, but OK to keep

    private int animTicks;
    private Player cachedTarget;

    // Flip to false when you're done debugging
    private static final boolean DEBUG = false;
    private boolean ended;

    public SummonMinionsGoal(SporeSpreaderEntity caster, float triggerRange, int minionsPerCast, int cooldownTicks, int scatterRadius) {
        this.caster = caster;
        this.triggerRange = triggerRange;
        this.minionsPerCast = Math.max(1, minionsPerCast);
        this.cooldownTicks = cooldownTicks;
        this.scatterRadius = scatterRadius;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (caster.level().isClientSide) return false;
        if (!(caster.level() instanceof ServerLevel sLevel)) return false;
        if (sLevel.getDifficulty() == Difficulty.PEACEFUL) return false;
        if (caster.getSummonCooldownTicks() > 0) return false;

        // CHANGED: Require an ACTUAL player target that the Spore Spreader has “located”
        // i.e., the TargetGoal chose a player, within range, and visible.
        LivingEntity tgt = caster.getTarget();
        if (!(tgt instanceof Player p) || p.isSpectator() || !p.isAlive()) return false;

        double follow = caster.getAttributeValue(Attributes.FOLLOW_RANGE);
        double range = Math.max(triggerRange, follow);
        if (caster.distanceToSqr(p) > range * range) return false;      // CHANGED: within range
        if (!caster.hasLineOfSight(p)) return false;                    // CHANGED: must see the player

        this.cachedTarget = p;
        if (DEBUG) broadcast(sLevel, "[SporeSpreader] canUse=true (has target, in range, LOS)");
        return true;
    }
    @Override
    public boolean canContinueToUse() {
        // CHANGED: keep running only while anim is active AND target is still valid/in range/LOS
        if (!caster.isSummoning() || animTicks >= 20 || cachedTarget == null) return false;
        if (!cachedTarget.isAlive() || cachedTarget.isSpectator()) return false;

        double follow = caster.getAttributeValue(Attributes.FOLLOW_RANGE);
        double range = Math.max(triggerRange, follow);
        if (caster.distanceToSqr(cachedTarget) > range * range) return false; // out of range
        if (!caster.hasLineOfSight(cachedTarget)) return false;               // lost LOS
        return true;
    }

    @Override
    public void start() {
        animTicks = 0;
        ended = false;                     // CHANGED: prevent double-stop
        caster.setSummoning(true);
        caster.getNavigation().stop();
        if (DEBUG && caster.level() instanceof ServerLevel sLevel) {
            broadcast(sLevel, "[SporeSpreader] START summoning animation");
        }
    }

    @Override
    public void stop() {
        if (ended) return;                 // CHANGED: prevent double-stop
        ended = true;

        caster.setSummoning(false);
        caster.setSummonCooldownTicks(cooldownTicks);
        cachedTarget = null;

        if (DEBUG && caster.level() instanceof ServerLevel sLevel) {
            broadcast(sLevel, "[SporeSpreader] STOP summoning; cooldown=" + cooldownTicks);
        }
    }

    @Override
    public void tick() {
        animTicks++;

        // CHANGED: re-validate target before spawn tick; abort quietly if lost
        if (cachedTarget != null) {
            caster.getLookControl().setLookAt(cachedTarget, 30, 30);

            double follow = caster.getAttributeValue(Attributes.FOLLOW_RANGE);
            double range = Math.max(triggerRange, follow);
            boolean valid =
                    cachedTarget.isAlive() &&
                            !cachedTarget.isSpectator() &&
                            caster.distanceToSqr(cachedTarget) <= range * range &&
                            caster.hasLineOfSight(cachedTarget);

            // If target becomes invalid before the spawn moment, cancel this cast (short grace, no spam)
            if (!valid && animTicks < 10) {
                // Optional: shorter cooldown when cancelled mid-cast
                caster.setSummonCooldownTicks(Math.min(cooldownTicks, 40));
                stop();
                return;
            }
        }

        if (caster.level() instanceof ServerLevel sLevel) {
            if (animTicks == 6 || animTicks == 8) spawnPreSummonParticles(sLevel);
            if (animTicks == 10) spawnExactWave(sLevel, minionsPerCast, cachedTarget);
        }

        if (animTicks >= 20) stop();
    }

    // particles
    private void spawnPreSummonParticles(ServerLevel sLevel) {
        RandomSource rng = caster.getRandom();
        double y = caster.getY() + 1.0D;
        double r = 2.0D;
        int points = 24;
        for (int i = 0; i < points; i++) {
            double angle = (Math.PI * 2 * i) / points + rng.nextDouble() * 0.06;
            double x = caster.getX() + Math.cos(angle) * r + (rng.nextGaussian() * 0.05);
            double z = caster.getZ() + Math.sin(angle) * r + (rng.nextGaussian() * 0.05);
            sLevel.sendParticles(ParticleTypes.SPORE_BLOSSOM_AIR, x, y, z, 1, 0.0D, 0.01D, 0.0D, 0.0D);
        }
    }

    // spawn helpers (same robust version you’re using)
    private int spawnExactWave(ServerLevel sLevel, int count, Player target) {
        int ok = 0;
        RandomSource rng = caster.getRandom();

        for (int i = 0; i < count; i++) {
            boolean spawnedThisOne = false;

            for (int attempt = 0; attempt < 12 && !spawnedThisOne; attempt++) {
                double angle = rng.nextDouble() * (Math.PI * 2);
                double radius = 2.2D + rng.nextDouble() * 2.8D;
                double sx = caster.getX() + Math.cos(angle) * radius;
                double sz = caster.getZ() + Math.sin(angle) * radius;

                BlockPos base = BlockPos.containing(sx, caster.getY(), sz);
                BlockPos spawnPos = findSafeSpawnPos(sLevel, base);

                if (spawnPos == null) continue;

                var type = ModEntities.FUNGI_SWORDSMAN.get();
                if (type == null) break;

                var minion = type.create(sLevel);
                if (minion == null) break;

                minion.moveTo(spawnPos, caster.getYRot(), 0.0F);
                if (!sLevel.noCollision(minion)) {
                    minion.setPos(minion.getX(), minion.getY() + 0.5D, minion.getZ());
                    if (!sLevel.noCollision(minion)) {
                        minion.setPos(minion.getX(), minion.getY() + 0.5D, minion.getZ());
                    }
                }

                minion.finalizeSpawn(sLevel, sLevel.getCurrentDifficultyAt(spawnPos), MobSpawnType.MOB_SUMMONED, null, null);
                minion.setSummoner(caster);
                if (target != null) {
                    minion.setTarget(target);
                    minion.setAggressive(true);
                }

                if (sLevel.addFreshEntity(minion)) {
                    ok++;
                    spawnedThisOne = true;
                }
            }

            if (!spawnedThisOne) {
                BlockPos fallback = findSafeSpawnPos(sLevel, caster.blockPosition().above());
                if (fallback != null) {
                    var type = ModEntities.FUNGI_SWORDSMAN.get();
                    var minion = type != null ? type.create(sLevel) : null;
                    if (minion != null) {
                        minion.moveTo(fallback, caster.getYRot(), 0.0F);
                        if (sLevel.noCollision(minion) && sLevel.addFreshEntity(minion)) {
                            minion.finalizeSpawn(sLevel, sLevel.getCurrentDifficultyAt(fallback), MobSpawnType.MOB_SUMMONED, null, null);
                            minion.setSummoner(caster);
                            if (target != null) {
                                minion.setTarget(target);
                                minion.setAggressive(true);
                            }
                            ok++;
                        }
                    }
                }
            }
        }
        return ok;
    }

    private BlockPos findSafeSpawnPos(ServerLevel sLevel, BlockPos base) {
        for (int dy = -1; dy <= 4; dy++) {
            BlockPos pos = base.offset(0, dy, 0);
            boolean feetEmpty = sLevel.getBlockState(pos).getCollisionShape(sLevel, pos).isEmpty();
            boolean headEmpty = sLevel.getBlockState(pos.above()).getCollisionShape(sLevel, pos.above()).isEmpty();
            if (feetEmpty && headEmpty) {
                BlockPos below = pos.below();
                var belowState = sLevel.getBlockState(below);
                if (belowState.isFaceSturdy(sLevel, below, net.minecraft.core.Direction.UP)) {
                    return pos;
                }
                // Or allow falling spawns by returning pos here
            }
        }
        return null;
    }

    // debug broadcast helper
    private void broadcast(ServerLevel sLevel, String msg) {
        if (!DEBUG) return;
        MinecraftServer server = sLevel.getServer();
        if (server == null) return;
        Component c = Component.literal(msg);
        for (Player p : sLevel.players()) p.sendSystemMessage(c);
    }
}

