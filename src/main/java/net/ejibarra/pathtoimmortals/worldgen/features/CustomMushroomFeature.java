package net.ejibarra.pathtoimmortals.worldgen.features;

import com.mojang.serialization.Codec;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CustomMushroomFeature extends Feature<NoneFeatureConfiguration> {
    // Change these to your custom blocks if you want
    private static final BlockState STEM = Blocks.MUSHROOM_STEM.defaultBlockState();
    private static final BlockState CAP  = ModBlocks.POISON_MUSHROOM_BLOCK.get().defaultBlockState();

    public CustomMushroomFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource rand   = ctx.random();
        BlockPos originXZ   = ctx.origin();

        // NEW: search for a valid, flat 5x5 pad:
        BlockPos anchor = validatedAnchor(level, originXZ, rand);
        if (anchor == null) return false;

        int y = anchor.getY();

        // -----------------------------
        // STEM (centered boxes, with shifts)
        // dimensions are length (X) × width (Z) × height (Y)

        // 5x5x2 (centered)
        y = fillCenteredBox(level, anchor, 5, 5, 6, y, 0); // returns new top+1

        // 3x3x5 (centered)
        y = fillCenteredBox(level, anchor, 3, 3, 5, y, 0);

        // 3x3x4 shifted 1 west (negative X)
        y = fillCenteredBox(level, anchor.offset(-1, 0, 0), 3, 3, 5, y, 0);

        // 3x3x7 shifted 1 west again (total 2 west)
        y = fillCenteredBox(level, anchor.offset(-2, 0, 0), 3, 3, 5, y, 0);

        // 3x3x5 shifted 1 east (so now total 1 west from original center)
        y = fillCenteredBox(level, anchor.offset(-1, 0, 0), 3, 3, 3, y, 0);

        int stemTopY = y - 1; // last filled Y
        int capBaseY = stemTopY + 1;

        // -----------------------------
        // CAP (stacked filled disks)
        // Layers: r=16 h=1; r=13 h=2; r=9 h=1; r=5 h=2; r=3 h=1
        int cy = capBaseY;

        cy = fillDisk(level, anchor, 16, 1, cy); // +1 high

        cy = fillDisk(level, anchor, 13, 1, cy); // +2 high

        cy = fillDisk(level, anchor, 9, 1,  cy); // +1 high

        cy = fillDisk(level, anchor, 5,  1,  cy); // +2 high

        fillDisk(level, anchor, 3,  1,  cy);      // +1 high (final)

        return true;
    }

    // ---------------------------------------
    // Helpers

    /** World-surface anchor centered on the column where we place the mushroom. */
    private static BlockPos surfaceCenter(LevelAccessor level, BlockPos pos) {
        int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
        return new BlockPos(pos.getX(), y, pos.getZ());
    }

    /** True if block at pos can support a structure above (solid top face). */
    private static boolean canStand(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos).isFaceSturdy(level, pos, Direction.UP);
    }

    /**
     * Fill a centered box at (centerX, centerZ) with size (sx × sz) and height h,
     * starting at Y=baseY, using STEM blocks.
     * Returns baseY + h (the next Y to continue building at).
     */
    private int fillCenteredBox(LevelAccessor level, BlockPos center, int sx, int sz, int h, int baseY, int yPadding) {
        int halfX = sx / 2;
        int halfZ = sz / 2;

        int startY = baseY;
        int endY   = baseY + h - 1;

        for (int y = startY; y <= endY; y++) {
            for (int dx = -halfX; dx <= halfX; dx++) {
                for (int dz = -halfZ; dz <= halfZ; dz++) {
                    BlockPos p = new BlockPos(center.getX() + dx, y, center.getZ() + dz);
                    level.setBlock(p, STEM, 2 | 16);
                }
            }
        }
        return endY + 1 + yPadding;
    }

    /**
     * Fill a horizontal filled disk (circle) of CAP blocks centered at 'center' for 'height' layers,
     * starting at Y=baseY. Uses equation dx^2 + dz^2 <= r^2.
     * Returns baseY + height (the next Y to continue building at).
     */
    private int fillDisk(LevelAccessor level, BlockPos center, int radius, int height, int baseY) {
        int r2 = radius * radius;
        int endY = baseY + height - 1;

        for (int y = baseY; y <= endY; y++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= r2) {
                        BlockPos p = new BlockPos(center.getX() + dx, y, center.getZ() + dz);
                        level.setBlock(p, CAP, 2 | 16);
                    }
                }
            }
        }
        return endY + 1;
    }
    /** Try a few nearby spots to find a valid 5x5 pad; returns the anchored center or null. */
    private BlockPos findValidAnchor(WorldGenLevel level, BlockPos origin, RandomSource rand) {
        for (int attempt = 0; attempt < 12; attempt++) {
            BlockPos candidate = surfaceCenter(level,
                    origin.offset(rand.nextInt(9) - 4, 0, rand.nextInt(9) - 4)); // search ±4 blocks

            if (hasSolidFlatPad5x5(level, candidate)) {
                return candidate;
            }
        }
        return null;
    }

    /** True if the 5x5 centered on 'center' is perfectly flat, dry, supported, and replaceable at surface Y. */
    private boolean hasSolidFlatPad5x5(LevelAccessor level, BlockPos center) {
        final int cx = center.getX();
        final int cz = center.getZ();

        // Get the surface Y at the center
        final int y0 = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, cx, cz);

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                int x = cx + dx;
                int z = cz + dz;

                // Surface Y for this column must match center exactly (strictly flat pad)
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                if (y != y0) return false;

                BlockPos pos   = new BlockPos(x, y, z);       // where we'll place the base layer
                BlockPos below = pos.below();

                // No fluids in the base cell
                if (!level.getFluidState(pos).isEmpty()) return false;

                // Base cell must be replaceable/air
                if (!level.getBlockState(pos).canBeReplaced()) return false;

                // The block below must support from the top face
                if (!level.getBlockState(below).isFaceSturdy(level, below, Direction.UP)) return false;
            }
        }
        return true;
    }

    /** Replace your old anchor getter with this search + validation. */
    private BlockPos validatedAnchor(WorldGenLevel level, BlockPos origin, RandomSource rand) {
        return findValidAnchor(level, origin, rand);
    }
}
