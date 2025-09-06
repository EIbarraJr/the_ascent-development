package net.ejibarra.pathtoimmortals.worldgen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.util.ModTags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;


public class ModConfiguredFeatures {
    public  static final ResourceKey<ConfiguredFeature<?, ?>> FIERO_KEY = registerKey("fiero");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> JASMINE_KEY = registerKey("jasmine");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> KINDER_KEY = registerKey("kinder");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> MANGO_KEY = registerKey("mango");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_KEY = registerKey("spirit");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> JADED_KEY = registerKey("jaded");



    /*public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SAPPHIRE_ORE_KEY = registerKey("nether_sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_SAPPHIRE_ORE_KEY = registerKey("end_stone_sapphire_ore");*/

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        //custom tree start
        var trunk = BlockStateProvider.simple(Blocks.CHERRY_LOG);
        var leaves = BlockStateProvider.simple(ModBlocks.FIERO_LEAVES.get());
        var trunkPlacer = new CherryTrunkPlacer(
                7,  //baseHeight
                2, //first Random height
                0, //second Random height
                new  WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3),1).build()),
                UniformInt.of(2, 4), //branch count
                UniformInt.of(-4, -3), //branch horizontal length
                UniformInt.of(-1, 0) //branch start  offset from top
                //branch end offset from top
        );
        var foliagePlacer = new CherryFoliagePlacer(
                ConstantInt.of(4), //radius
                ConstantInt.of(0), //offset
                ConstantInt.of(5), //height
                0.25f, //wideBottomLayerHoleChance
                0.5f, //cornerHoleChance
                0.1666667f, //hangingLeavesChance
                0.3333334f //hangingLeavesExtensionChance
        );

        //end


        FeatureUtils.register(context, FIERO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_LOG), trunkPlacer, BlockStateProvider.simple(ModBlocks.FIERO_LEAVES.get()), foliagePlacer, new TwoLayersFeatureSize(1, 0, 2)).build());
        FeatureUtils.register(context, JADED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG), trunkPlacer, BlockStateProvider.simple(ModBlocks.JADED_LEAVES.get()), foliagePlacer, new TwoLayersFeatureSize(1, 0, 2)).build());
        FeatureUtils.register(context, JASMINE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.SPRUCE_LOG), trunkPlacer, BlockStateProvider.simple(ModBlocks.JASMINE_LEAVES.get()), foliagePlacer, new TwoLayersFeatureSize(1, 0, 2)).build());
        FeatureUtils.register(context, KINDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG), trunkPlacer, BlockStateProvider.simple(ModBlocks.KINDER_LEAVES.get()), foliagePlacer, new TwoLayersFeatureSize(1, 0, 2)).build());
        FeatureUtils.register(context, MANGO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG), trunkPlacer, BlockStateProvider.simple(ModBlocks.MANGO_LEAVES.get()), foliagePlacer, new TwoLayersFeatureSize(1, 0, 2)).build());
        FeatureUtils.register(context, SPIRIT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG), trunkPlacer, BlockStateProvider.simple(ModBlocks.SPIRIT_BLOSSOM_LEAVES.get()), foliagePlacer, new TwoLayersFeatureSize(1, 0, 2)).build());
        //FeatureUtils.register(context, SPIRIT_KEY, Feature.TREE, treeConfiguration);
        /*register(context, FIERO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new CherryTrunkPlacer(4, 5, 3, 7, 4, 4),
                BlockStateProvider.simple(ModBlocks.FIERO_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1,0,2)).build());
        register(context, JASMINE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(9, 4, 3),
                BlockStateProvider.simple(ModBlocks.JASMINE_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1,0,2)).build());
        register(context, KINDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(9, 4, 3),
                BlockStateProvider.simple(ModBlocks.KINDER_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1,0,2)).build());
        register(context, MANGO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(9, 4, 3),
                BlockStateProvider.simple(ModBlocks.MANGO_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1,0,2)).build());
        register(context, SPIRIT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(9, 4, 3),
                BlockStateProvider.simple(ModBlocks.SPIRIT_BLOSSOM_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1,0,2)).build());
        register(context, JADED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new DarkOakTrunkPlacer(9, 4, 3),
                BlockStateProvider.simple(ModBlocks.JADED_LEAVES.get()),
                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1,0,2)).build());*/

        /*RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceabeles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceabeles = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 9));
        register(context, NETHER_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceabeles,
                ModBlocks.NETHER_SAPPHIRE_ORE.get().defaultBlockState(), 9));
        register(context, END_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceabeles,
                ModBlocks.END_STONE_SAPPHIRE_ORE.get().defaultBlockState(), 9));*/
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(PathToImmortals.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
