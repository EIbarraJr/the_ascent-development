package net.ejibarra.pathtoimmortals.worldgen.biome.surface;

import com.google.common.collect.ImmutableList;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.worldgen.biome.ModBiome;
import net.ejibarra.pathtoimmortals.worldgen.noise.ModNoises;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static net.minecraft.data.worldgen.SurfaceRuleData.overworldLike;

public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.CAVE_AIR);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);

    //DIRT_BLOCKS
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource MOSS_BLOCK = makeStateRule(Blocks.MOSS_BLOCK);
    private static final SurfaceRules.RuleSource BLUE_MOSS_BLOCK = makeStateRule(ModBlocks.SPIRIT_MOSS.get());
    private static final SurfaceRules.RuleSource PURPLE_MOSS_BLOCK = makeStateRule(ModBlocks.EVES_BLUES.get());
    private static final SurfaceRules.RuleSource FEYS_MOSS_BLOCK = makeStateRule(ModBlocks.FEYS_SORROW.get());
    private static final SurfaceRules.RuleSource ORANGE_MOSS_BLOCK = makeStateRule(ModBlocks.OCTOS_GROWTH.get());
    private static final SurfaceRules.RuleSource YELLOW_MOSS_BLOCK = makeStateRule(ModBlocks.LEMON_MOSS.get());
    private static final SurfaceRules.RuleSource RED_MOSS_BLOCK = makeStateRule(ModBlocks.ROSY_MOSS.get());
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource MUDDY_ROOTS = makeStateRule(Blocks.MUDDY_MANGROVE_ROOTS);
    private static final SurfaceRules.RuleSource ROOTED_DIRT = makeStateRule(Blocks.ROOTED_DIRT);

    //STONE_BLOCKS
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource CALCITE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    //Other
    private static final SurfaceRules.RuleSource COBBLESTONE = makeStateRule(Blocks.COBBLESTONE);
    private static final SurfaceRules.RuleSource MOSSY_STONE = makeStateRule(Blocks.MOSSY_COBBLESTONE);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource DESOLATE_SANDSTONE = makeStateRule(ModBlocks.DESOLATE_SANDSTONE.get());
    private static final SurfaceRules.RuleSource DESOLATE_SAND = makeStateRule(ModBlocks.DESOLATE_SAND.get());
    private static final SurfaceRules.RuleSource TRAPPED_SOUL_SAND = makeStateRule(ModBlocks.TRAPPED_SOUL_SAND.get());

    //stateRule Method
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource overworld() {
        return overworldLike(true, false, true);
    }

    public static SurfaceRules.RuleSource overworldLike(boolean isSurface, boolean bedrockRoof, boolean bedrockFloor) {
        SurfaceRules.ConditionSource deepslateLevel = SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8));

        SurfaceRules.ConditionSource yStart63 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
        SurfaceRules.ConditionSource yStart74 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);

        SurfaceRules.ConditionSource y60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
        SurfaceRules.ConditionSource y62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource y63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.ConditionSource y97 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
        SurfaceRules.ConditionSource y256 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);

        SurfaceRules.ConditionSource surfaceNoise1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
        SurfaceRules.ConditionSource surfaceNoise2 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818D, 0.1818D);
        SurfaceRules.ConditionSource surfaceNoise3 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);

        SurfaceRules.ConditionSource waterBlockCheck = SurfaceRules.waterBlockCheck(0, 0);
        SurfaceRules.ConditionSource waterBlockCheck1 = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.ConditionSource oceanGravelCheck = SurfaceRules.waterStartCheck(-6, -1);

        SurfaceRules.ConditionSource holeCondition = SurfaceRules.hole();
        SurfaceRules.ConditionSource steepCondition = SurfaceRules.steep();

        SurfaceRules.RuleSource onSurface = SurfaceRules.sequence(

                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiome.TEST_BIOME),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), PURPLE_MOSS_BLOCK),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95D), GRASS_BLOCK)))//put , and repeat for others
        );

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        if (bedrockRoof) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
        }

        if (bedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        }

        // ✅ Apply your surface edits only above the preliminary surface
        builder.add(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), onSurface));

        // Keep your deepslate swap
        builder.add(SurfaceRules.ifTrue(deepslateLevel, DEEPSLATE));

        // (Optional) add a simple vanilla-like grass/dirt fallback so areas not caught by noise still look sane
        builder.add(SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), GRASS_BLOCK),
                DIRT
        ));

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));

        /*builder.add(SurfaceRules.ifTrue(deepslateLevel, DEEPSLATE));

        return SurfaceRules.sequence(builder.build().toArray((rules) -> {
            return new SurfaceRules.RuleSource[rules];
        }));*/
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double p_194809_) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, p_194809_ / 8.25D, Double.MAX_VALUE);
    }

}
