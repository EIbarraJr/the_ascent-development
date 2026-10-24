package net.ejibarra.pathtoimmortals.worldgen.features;

import com.mojang.serialization.Codec;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TurquoisePrismFeature extends Feature<NoneFeatureConfiguration> {
    public TurquoisePrismFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();
        BlockPos origin = ctx.origin();

        // 1. Start at the world surface at this XZ
        BlockPos surfaceGuess = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, origin);

        // 2. Walk downward to find "real ground" (not leaves/logs/air/water/lava)
        BlockPos.MutableBlockPos cursor = surfaceGuess.mutable();
        BlockPos groundPos = null;

        for (int i = 0; i < 20; i++) { // search up to 20 blocks down
            BlockState state = level.getBlockState(cursor);

            if (!state.isAir()
                    && !state.is(BlockTags.LEAVES)
                    && !state.is(BlockTags.LOGS)
                    && !state.is(Blocks.WATER)
                    && !state.is(Blocks.LAVA)) {

                groundPos = cursor.immutable();
                break;
            }

            cursor.move(0, -1, 0);
            if (cursor.getY() <= level.getMinBuildHeight() + 5) {
                break;
            }
        }

        if (groundPos == null) {
            // Column is weird (only trees/air/etc.) – skip this attempt
            return false;
        }

        // Air block just above solid ground
        BlockPos surface = groundPos.above();

        // 3. Radius & center so it's half-buried
        int radius = 2 + random.nextInt(2); // 2-3
        BlockPos center = surface.below((radius * 2) / 3);

        BlockState turquoise = ModBlocks.TURQUOISE_BLOCK.get().defaultBlockState();
        BlockState calcite = Blocks.CALCITE.defaultBlockState();

        boolean placedAny = false;
        int r2 = radius * radius;

        // 4. Fill a solid sphere with turquoise + calcite
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dy * dy + dz * dz <= r2) {
                        BlockPos pos = center.offset(dx, dy, dz);

                        if (pos.getY() < level.getMinBuildHeight() + 5
                                || pos.getY() > level.getMaxBuildHeight() - 5) {
                            continue;
                        }

                        BlockState current = level.getBlockState(pos);

                        // Don't overwrite logs or leaves so we never carve through trees
                        if (current.is(BlockTags.LEAVES) || current.is(BlockTags.LOGS)) {
                            continue;
                        }

                        BlockState toPlace = turquoise;
                        level.setBlock(pos, toPlace, 2);
                        placedAny = true;
                    }
                }
            }
        }

        return placedAny;
    }
}
