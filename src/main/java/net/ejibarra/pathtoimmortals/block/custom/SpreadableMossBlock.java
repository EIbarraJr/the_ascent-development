package net.ejibarra.pathtoimmortals.block.custom;


import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import org.joml.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static net.minecraft.core.registries.Registries.BLOCK;

public class SpreadableMossBlock extends Block implements BonemealableBlock {
    /** Weighted flora entry (weight can be any positive float; they don't need to sum to 100). */
    public static class FloraEntry {
        public final Supplier<Block> block;
        public final float weight;

        public FloraEntry(Supplier<Block> block, float weight) {
            this.block = block;
            this.weight = weight;
        }
    }

    /** Config per variant. */
    public static class Config {
        public final Supplier<Block> self;              // this block (deferred)
        public final Supplier<Block> carpet;            // thin layer block (optional, can be null)
        public final TagKey<Block> spreadableTag;       // what blocks we can convert
        public final int radiusXZ;                      // spread radius horizontally
        public final int radiusY;                       // spread radius vertically
        public final int attemptsPerBonemeal;           // random tries per bonemeal use
        public final float carpetChance;                // chance to place carpet above
        public final Vector3f particleRgb;              // particle color (keep/null if unused)

        // NEW: flora knobs
        public final float floraChance;                 // per-spot chance to try flora (0..1)
        public final List<FloraEntry> floraWeighted;    // weighted plant candidates

        public Config(Supplier<Block> self,
                      Supplier<Block> carpet,
                      TagKey<Block> spreadableTag,
                      int radiusXZ, int radiusY, int attemptsPerBonemeal,
                      float carpetChance, Vector3f particleRgb,
                      float floraChance,
                      List<FloraEntry> floraWeighted) {
            this.self = self;
            this.carpet = carpet;
            this.spreadableTag = spreadableTag;
            this.radiusXZ = radiusXZ;
            this.radiusY = radiusY;
            this.attemptsPerBonemeal = attemptsPerBonemeal;
            this.carpetChance = carpetChance;
            this.particleRgb = particleRgb;
            this.floraChance = floraChance;
            this.floraWeighted = floraWeighted;
        }
    }

    private final Config cfg;

    public SpreadableMossBlock(Properties props, Config config) {
        super(props);
        this.cfg = Objects.requireNonNull(config);
    }

    // ===== Bonemeal hooks =====
    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        // quick scan: if there is at least one replaceable neighbor in AABB, allow bonemeal
        AABB scan = new AABB(
                pPos.offset(-1, -1, -1),
                pPos.offset( 1,  1,  1)
        );
        for (BlockPos p : BlockPos.betweenClosed((int) scan.minX, (int) scan.minY, (int) scan.minZ,
                (int) scan.maxX, (int) scan.maxY, (int) scan.maxZ)) {
            if (pLevel.getBlockState(p).is(cfg.spreadableTag)) return true;
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos origin, BlockState state) {
        spawnParticles(level, origin, 16);

        for (int i = 0; i < cfg.attemptsPerBonemeal; i++) {
            BlockPos p = origin.offset(
                    rand.nextIntBetweenInclusive(-cfg.radiusXZ, cfg.radiusXZ),
                    rand.nextIntBetweenInclusive(-cfg.radiusY,  cfg.radiusY),
                    rand.nextIntBetweenInclusive(-cfg.radiusXZ, cfg.radiusXZ)
            );
            if (p.distSqr(origin) > (cfg.radiusXZ + 0.5) * (cfg.radiusXZ + 0.5)) continue;
            trySpreadInto(level, p, rand);
        }
    }

    /*// ===== Optional passive spread (toggle by giving .randomTicks() in Properties) =====
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        // very subtle ambient spread; tune or remove
        if (rand.nextInt(24) == 0) {
            BlockPos p = pos.offset(
                    rand.nextIntBetweenInclusive(-1, 1),
                    rand.nextIntBetweenInclusive(-1, 1),
                    rand.nextIntBetweenInclusive(-1, 1)
            );
            trySpreadInto(level, p, rand);
        }
    }*/

    // ===== Internals =====

    private void trySpreadInto(ServerLevel level, BlockPos pos, RandomSource rand) {
        BlockState target = level.getBlockState(pos);
        if (!target.is(cfg.spreadableTag)) return;

        // Replace with self
        level.setBlock(pos, cfg.self.get().defaultBlockState(), Block.UPDATE_ALL);
        BlockPos above = pos.above();
        boolean placedTop = false;


        // --- Flora first (weighted) ---
        if (cfg.floraWeighted != null && !cfg.floraWeighted.isEmpty()
                && rand.nextFloat() < clamp01(cfg.floraChance)
                && level.isEmptyBlock(above)) {
            Block floraBlock = pickWeightedFlora(rand);
            if (floraBlock != null) {
                BlockState plant = floraBlock.defaultBlockState();
                if (plant.canSurvive(level, above)) {
                    level.setBlock(above, plant, Block.UPDATE_ALL);
                    placedTop = true;
                }
            }
        }

        // --- Carpet if flora didn't place ---
        if (!placedTop && cfg.carpet != null && rand.nextFloat() < clamp01(cfg.carpetChance) && level.isEmptyBlock(above)) {
            level.setBlock(above, cfg.carpet.get().defaultBlockState(), Block.UPDATE_ALL);
            placedTop = true;
        }

        if (cfg.particleRgb != null) spawnParticles(level, pos, 4 + rand.nextInt(4));
    }
    private Block pickWeightedFlora(RandomSource rand) {
        float total = 0f;
        for (FloraEntry e : cfg.floraWeighted) total += Math.max(0f, e.weight);
        if (total <= 0f) return null;

        float r = rand.nextFloat() * total, acc = 0f;
        for (FloraEntry e : cfg.floraWeighted) {
            float w = Math.max(0f, e.weight);
            acc += w;
            if (r <= acc) return e.block.get();
        }
        // fallback
        return cfg.floraWeighted.get(cfg.floraWeighted.size() - 1).block.get();
    }

    private static float clamp01(float v) { return v < 0f ? 0f : (v > 1f ? 1f : v); }

    private void spawnParticles(ServerLevel level, BlockPos pos, int count) {
        DustParticleOptions dust = new DustParticleOptions(new Vector3f(0.0F, 1.0F, 1.0F), 1.0F);

        for (int i = 0; i < count; i++) {
            double x = pos.getX() + level.random.nextDouble();
            double y = pos.getY() + 1.0D + level.random.nextDouble() * 0.5D;
            double z = pos.getZ() + level.random.nextDouble();
            level.sendParticles(dust, x, y, z, 1, 0, 0, 0, 0);
        }
    }

    // Nice-to-have: make it soft like moss
    @Override public PushReaction getPistonPushReaction(BlockState state) { return PushReaction.DESTROY; }
    @Override public float getExplosionResistance() { return 0.2F; }
    @Override public SoundType getSoundType(BlockState state) { return SoundType.MOSS; }
}

