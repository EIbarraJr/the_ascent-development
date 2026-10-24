package net.ejibarra.pathtoimmortals.worldgen.features;

import com.mojang.serialization.Codec;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TwinCapMushroomFeature extends Feature<NoneFeatureConfiguration> {
    private static final BlockState STEM = Blocks.MUSHROOM_STEM.defaultBlockState();
    private static final BlockState CAP  = ModBlocks.POISON_MUSHROOM_BLOCK.get().defaultBlockState();

    public TwinCapMushroomFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos origin = surface(level, ctx.origin());

        // Check biome via holder
        Holder<Biome> biomeHolder = level.getBiome(origin);
        if (biomeHolder.is(BiomeTags.IS_OCEAN) || biomeHolder.is(BiomeTags.IS_BEACH)) return false;

        // Require flat, dry 3x3 pad
        if (!hasFlatPad3x3(level, origin)) return false;

        // Skip if directly above water
        if (!level.getFluidState(origin.below()).isEmpty()) return false;

        // --- Stem A (diagonal west) ---
        BlockPos a0 = origin;
        set(level, a0, STEM);
        BlockPos a1 = a0.offset(-1, +1, 0);
        set(level, a1, STEM);
        BlockPos a2 = a1.offset(-1, +1, 0);
        set(level, a2, STEM);
        BlockPos a3 = a2.offset(-1, +1, 0);
        set(level, a3, STEM);

        // small cap (3x3x1)
        placeSquare(level, a3.above(), 3, 1, CAP);

        // --- Stem B (vertical north) ---
        BlockPos bBase = origin.north(1);
        for (int i = 0; i < 6; i++) {
            set(level, bBase.above(i), STEM);
        }

        // large cap (5x5x1)
        placeSquare(level, bBase.above(6), 5, 1, CAP);

        return true;
    }

    // --------------------------------------------------

    private static BlockPos surface(LevelAccessor level, BlockPos pos) {
        int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
        return new BlockPos(pos.getX(), y, pos.getZ());
    }

    /** Ensures a 3×3 perfectly flat, dry, replaceable pad with solid support below */
    private static boolean hasFlatPad3x3(LevelAccessor level, BlockPos centerSurface) {
        int cx = centerSurface.getX(), cz = centerSurface.getZ();
        int y0 = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, cx, cz);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                int x = cx + dx, z = cz + dz;
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                if (y != y0) return false;
                BlockPos pos = new BlockPos(x, y, z);
                BlockPos below = pos.below();
                if (!level.getFluidState(pos).isEmpty()) return false;
                if (!level.getBlockState(pos).canBeReplaced()) return false;
                if (!level.getBlockState(below).isFaceSturdy(level, below, Direction.UP)) return false;
            }
        }
        return true;
    }

    private static void placeSquare(LevelAccessor level, BlockPos center, int size, int height, BlockState state) {
        int half = size / 2;
        int cx = center.getX(), cy = center.getY(), cz = center.getZ();
        for (int dy = 0; dy < height; dy++) {
            int y = cy + dy;
            for (int dx = -half; dx <= half; dx++) {
                for (int dz = -half; dz <= half; dz++) {
                    set(level, new BlockPos(cx + dx, y, cz + dz), state);
                }
            }
        }
    }

    private static void set(LevelAccessor level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, 2 | 16);
    }
}
