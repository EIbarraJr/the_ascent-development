package net.ejibarra.pathtoimmortals.worldgen.features;// AmethystGoldPillarFeature.java  (patched parts)

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
// ...imports unchanged...

public class AmethystGoldPillarFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState AMETHYST = Blocks.AMETHYST_BLOCK.defaultBlockState();
    private static final BlockState GOLD     = Blocks.GOLD_BLOCK.defaultBlockState();

    // Tweak this to control gold amount (0.10f = 10% gold)
    private static final float GOLD_CHANCE = 0.12f;

    public AmethystGoldPillarFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource rand   = ctx.random();

        // Try up to N nearby positions to find a valid land spot
        BlockPos tried = ctx.origin();
        BlockPos origin = null;
        for (int attempt = 0; attempt < 8; attempt++) {
            BlockPos candidate = surfaceAt(level, tried.offset(
                    rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2
            ));

            // Exclude oceans & beaches
            if (level.getBiome(candidate).is(BiomeTags.IS_OCEAN)) continue;
            if (level.getBiome(candidate).is(BiomeTags.IS_BEACH)) continue;

            // Validate base (dry, supported, reasonably flat)
            if (isValidBase(level, candidate)) {
                origin = candidate;
                break;
            }
        }
        if (origin == null) return false;

        int baseY = origin.getY();

        // Amethyst-biased: ~ (1 - GOLD_CHANCE) amethyst, GOLD_CHANCE gold
        java.util.function.Supplier<BlockState> mix = () ->
                rand.nextFloat() < GOLD_CHANCE ? GOLD : AMETHYST;

        // --- Base 4×4×1 ---
        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                BlockPos p = origin.offset(dx, 0, dz);
                boolean corner = (dx == 0 || dx == 3) && (dz == 0 || dz == 3);
                if (corner) {
                    // Only if the block below can support it; otherwise fallback to solid block
                    if (canSupportFacing(level, p.below(), Direction.UP)) {
                        setCluster(level, p, Direction.UP);
                    } else {
                        setBlock(level, p, AMETHYST); // safe fallback
                    }
                } else {
                    setBlock(level, p, mix.get());
                }
            }
        }

        // --- 2×2×3 column (centered at x=1..2, z=1..2) ---
        for (int dy = 0; dy < 3; dy++) {
            for (int dx = 1; dx <= 2; dx++) {
                for (int dz = 1; dz <= 2; dz++) {
                    BlockPos col = origin.offset(dx, 1 + dy, dz);
                    setBlock(level, col, mix.get());

                    // Side buds/clusters on first 2 layers only, but only if attach face is sturdy
                    if (dy <= 1) {
                        for (Direction d : Direction.Plane.HORIZONTAL) {
                            BlockPos attachTo = col;                 // block the bud attaches TO
                            BlockPos target   = col.relative(d);     // the bud's position in air
                            if (level.isEmptyBlock(target) && canSupportFacing(level, attachTo, d)) {
                                setBudOrCluster(level, rand, target, d);
                            }
                        }
                    }
                }
            }
        }

        // --- Top 2×2 cap at y = baseY + 3: 1 gold + 3 clusters (up) ---
        int topY = baseY + 3;
        int goldIndex = rand.nextInt(4);
        int idx = 0;
        for (int dx = 1; dx <= 2; dx++) {
            for (int dz = 1; dz <= 2; dz++) {
                BlockPos top = origin.offset(dx, topY, dz);
                if (idx == goldIndex) {
                    setBlock(level, top, GOLD);
                } else {
                    if (canSupportFacing(level, top.below(), Direction.UP)) {
                        setCluster(level, top, Direction.UP);
                    } else {
                        setBlock(level, top, AMETHYST); // safe fallback if no support
                    }
                }
                idx++;
            }
        }

        return true;
    }

    // Use WORLD_SURFACE_WG for predictable “on ground” placement.
    private static BlockPos surfaceAt(LevelAccessor level, BlockPos pos) {
        int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
        return new BlockPos(pos.getX(), y, pos.getZ());
    }

    // Ensure 4×4 area is air/replaceable, dry (no fluid), flat(ish), and fully supported underneath.
    private static boolean isValidBase(LevelAccessor level, BlockPos origin) {
        // Flatten/slope check: all surface heights within 1 block of each other
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, origin.getX() + dx, origin.getZ() + dz);
                if (y < min) min = y;
                if (y > max) max = y;
            }
        }
        if (max - min > 1) return false; // too sloped → would “float” at edges

        // Re-anchor to the lowest of the sampled heights so we don’t “hang”
        if (origin.getY() != min) origin = new BlockPos(origin.getX(), min, origin.getZ());

        // Check each base cell at Y = min
        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                BlockPos here = origin.offset(dx, 0, dz);

                // Must be replaceable air (no water)
                FluidState fluid = level.getFluidState(here);
                if (!fluid.isEmpty()) return false;
                if (!level.getBlockState(here).canBeReplaced()) return false;

                // Block below must be solid on top (support)
                if (!canSupportFacing(level, here.below(), Direction.UP)) return false;
            }
        }
        return true;
    }

    // True if the block at pos can support something attached on the given face.
    private static boolean canSupportFacing(LevelAccessor level, BlockPos pos, Direction face) {
        return level.getBlockState(pos).isFaceSturdy(level, pos, face);
    }

    private static void setBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state, 2 | 16);
    }

    private static void setCluster(LevelAccessor level, BlockPos pos, Direction facing) {
        BlockState cluster = Blocks.AMETHYST_CLUSTER.defaultBlockState()
                .setValue(net.minecraft.world.level.block.AmethystClusterBlock.FACING, facing);
        setBlock(level, pos, cluster);
    }

    private static void setBudOrCluster(LevelAccessor level, RandomSource rand, BlockPos pos, Direction facing) {
        BlockState state;
        float r = rand.nextFloat();
        if (r < 0.25f) {
            state = Blocks.SMALL_AMETHYST_BUD.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.AmethystClusterBlock.FACING, facing);
        } else if (r < 0.50f) {
            state = Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.AmethystClusterBlock.FACING, facing);
        } else if (r < 0.75f) {
            state = Blocks.LARGE_AMETHYST_BUD.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.AmethystClusterBlock.FACING, facing);
        } else {
            state = Blocks.AMETHYST_CLUSTER.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.AmethystClusterBlock.FACING, facing);
        }
        setBlock(level, pos, state);
    }
}
