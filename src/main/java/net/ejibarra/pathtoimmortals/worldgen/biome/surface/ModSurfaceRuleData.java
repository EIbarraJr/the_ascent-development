package net.ejibarra.pathtoimmortals.worldgen.biome.surface;

import com.google.common.collect.ImmutableList;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.worldgen.biome.ModBiome;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.PathfindToRaidGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;


public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.CAVE_AIR);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);

    // DIRT BLOCKS
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource MOSS_BLOCK = makeStateRule(Blocks.MOSS_BLOCK);
    private static final SurfaceRules.RuleSource BLUE_MOSS_BLOCK =
            makeStateRule(ModBlocks.SPIRIT_MOSS.get());
    private static final SurfaceRules.RuleSource PURPLE_MOSS_BLOCK =
            makeStateRule(ModBlocks.EVES_BLUES.get());
    private static final SurfaceRules.RuleSource FEYS_MOSS_BLOCK =
            makeStateRule(ModBlocks.FEYS_SORROW.get());
    private static final SurfaceRules.RuleSource ORANGE_MOSS_BLOCK =
            makeStateRule(ModBlocks.OCTOS_GROWTH.get());
    private static final SurfaceRules.RuleSource YELLOW_MOSS_BLOCK =
            makeStateRule(ModBlocks.LEMON_MOSS.get());
    private static final SurfaceRules.RuleSource RED_MOSS_BLOCK =
            makeStateRule(ModBlocks.ROSY_MOSS.get());

    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource MUDDY_ROOTS =
            makeStateRule(Blocks.MUDDY_MANGROVE_ROOTS);
    private static final SurfaceRules.RuleSource ROOTED_DIRT =
            makeStateRule(Blocks.ROOTED_DIRT);

    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);

    // Added for mixed river/lake beds
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);

    private static final SurfaceRules.RuleSource CLAY = makeStateRule(Blocks.CLAY);

    private static final SurfaceRules.RuleSource DESOLATE_SAND =
            makeStateRule(ModBlocks.DESOLATE_SAND.get());
    private static final SurfaceRules.RuleSource DESOLATE_SOUL_SAND =
            makeStateRule(ModBlocks.TRAPPED_SOUL_SAND.get());
    private static final SurfaceRules.RuleSource DESOLATE_SANDSTONE =
            makeStateRule(ModBlocks.DESOLATE_SANDSTONE.get());

    // STONE BLOCKS
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    // OTHER
    private static final SurfaceRules.RuleSource COBBLESTONE =
            makeStateRule(Blocks.COBBLESTONE);
    private static final SurfaceRules.RuleSource MOSSY_STONE =
            makeStateRule(Blocks.MOSSY_COBBLESTONE);
    private static final SurfaceRules.RuleSource SANDSTONE =
            makeStateRule(Blocks.SANDSTONE);

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource overworld() {
        return overworldLike(true);
    }

    public static SurfaceRules.RuleSource overworldLike(boolean isSurface) {

        /*
         * This condition is true when the generated surface is beneath water.
         *
         * waterBlockCheck(-1, 0) is normally true for dry surfaces.
         * Inverting it lets us detect river and lake floors.
         */
        SurfaceRules.ConditionSource underwater =
                SurfaceRules.not(
                        SurfaceRules.waterBlockCheck(-1, 0)
                );

        /*
         * Shared river/lake-bed material rule.
         *
         * SurfaceRules.sequence does not randomly choose a rule.
         * It checks from top to bottom and uses the first matching rule.
         *
         * The noise ranges create large natural-looking patches.
         */
        SurfaceRules.RuleSource mixedWaterBed = SurfaceRules.sequence(

                // Low-noise patches become clay
                SurfaceRules.ifTrue(
                        surfaceNoiseBelow(-2.00D),
                        CLAY
                ),

                // Medium-low patches become sand
                SurfaceRules.ifTrue(
                        surfaceNoiseBelow(0.00D),
                        SAND
                ),

                // Medium-high patches become gravel
                SurfaceRules.ifTrue(
                        surfaceNoiseBelow(2.00D),
                        GRAVEL
                ),

                // Remaining patches become dirt
                DIRT
        );

        // === Spore Lands surface ===
        SurfaceRules.RuleSource sporeLandsSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiome.SPORELAND),
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(

                                // TOP BLOCK
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(

                                                /*
                                                 * Check underwater first.
                                                 * This prevents grass or moss from
                                                 * covering the river/lake floor.
                                                 */
                                                SurfaceRules.ifTrue(
                                                        underwater,
                                                        mixedWaterBed
                                                ),

                                                // Normal dry land
                                                SurfaceRules.ifTrue(
                                                        surfaceNoiseAbove(3.00D),
                                                        PURPLE_MOSS_BLOCK
                                                ),

                                                GRASS_BLOCK
                                        )
                                ),

                                // BLOCKS DIRECTLY UNDER THE TOP
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(

                                                /*
                                                 * Continue the water-bed material
                                                 * beneath the visible top block.
                                                 */
                                                SurfaceRules.ifTrue(
                                                        underwater,
                                                        mixedWaterBed
                                                ),

                                                // Normal dry-land filler
                                                DIRT
                                        )
                                )
                        )
                )
        );

        // === DESOLATE_LANDS surface ===
        SurfaceRules.RuleSource desolateSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiome.DESOLATE_DESERT),
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(

                                // TOP BLOCK
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(

                                                // Noisy trapped soul sand patches
                                                SurfaceRules.ifTrue(
                                                        surfaceNoiseAbove(2.00D),
                                                        DESOLATE_SOUL_SAND
                                                ),

                                                DESOLATE_SAND
                                        )
                                ),

                                // UNDER THE TOP
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        DESOLATE_SANDSTONE
                                )
                        )
                )
        );

        // === THE_HALLOWED surface ===
        SurfaceRules.RuleSource theHallowedSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiome.THE_HALLOWED),
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(

                                // TOP BLOCK
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(

                                                // Mixed river and lake bottom
                                                SurfaceRules.ifTrue(
                                                        underwater,
                                                        mixedWaterBed
                                                ),

                                                // Normal dry land
                                                SurfaceRules.ifTrue(
                                                        surfaceNoiseAbove(3.00D),
                                                        BLUE_MOSS_BLOCK
                                                ),

                                                GRASS_BLOCK
                                        )
                                ),

                                // UNDER THE TOP
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(

                                                // Continue underwater patches downward
                                                SurfaceRules.ifTrue(
                                                        underwater,
                                                        mixedWaterBed
                                                ),

                                                // Normal dry-land filler
                                                DIRT
                                        )
                                )
                        )
                )
        );

        // === Ancient Garden surface ===
        SurfaceRules.RuleSource ancientGardenSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiome.ANCIENT_GARDEN),
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(

                                // TOP BLOCK
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(

                                                /*
                                                 * Check underwater first.
                                                 * This prevents grass or moss from
                                                 * covering the river/lake floor.
                                                 */
                                                SurfaceRules.ifTrue(
                                                        underwater,
                                                        mixedWaterBed
                                                ),

                                                // Normal dry land
                                                SurfaceRules.ifTrue(
                                                        surfaceNoiseAbove(3.00D),
                                                        FEYS_MOSS_BLOCK
                                                ),

                                                GRASS_BLOCK
                                        )
                                ),

                                // BLOCKS DIRECTLY UNDER THE TOP
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(

                                                /*
                                                 * Continue the water-bed material
                                                 * beneath the visible top block.
                                                 */
                                                SurfaceRules.ifTrue(
                                                        underwater,
                                                        mixedWaterBed
                                                ),

                                                // Normal dry-land filler
                                                DIRT
                                        )
                                )
                        )
                )
        );

        // Combine custom biome rules
        SurfaceRules.RuleSource onSurface = SurfaceRules.sequence(
                sporeLandsSurface,
                desolateSurface,
                theHallowedSurface,
                ancientGardenSurface

        );

        // Fall back to vanilla Overworld surface rules.
        return SurfaceRules.sequence(
                onSurface,
                SurfaceRuleData.overworld()
        );
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double value) {
        return SurfaceRules.noiseCondition(
                Noises.SURFACE,
                value / 8.25D,
                Double.MAX_VALUE
        );
    }

    /*
     * Added helper for values below a selected surface-noise threshold.
     *
     * This uses the same 8.25 scaling as surfaceNoiseAbove(), so the values
     * are easier to adjust consistently.
     */
    private static SurfaceRules.ConditionSource surfaceNoiseBelow(double value) {
        return SurfaceRules.noiseCondition(
                Noises.SURFACE,
                Double.NEGATIVE_INFINITY,
                value / 8.25D
        );
    }
}
