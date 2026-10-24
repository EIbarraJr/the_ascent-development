package net.ejibarra.pathtoimmortals.worldgen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;

import net.ejibarra.pathtoimmortals.worldgen.features.ModFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.BlockBlobFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;


public class ModConfiguredFeatures {
    //KEYS
    public  static final ResourceKey<ConfiguredFeature<?, ?>> FIERO_KEY = registerKey("fiero");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> JASMINE_KEY = registerKey("jasmine");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> KINDER_KEY = registerKey("kinder");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> MANGO_KEY = registerKey("mango");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> SPIRIT_KEY = registerKey("spirit");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> JADED_KEY = registerKey("jaded");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> TURQUOISE_ROCKS_KEY = registerKey("turquoise_rocks_key");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> FEYS_ROCKS_KEY = registerKey("feys_rocks_key");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROCKS = registerKey("patch_rocks");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROSE_BLUE = registerKey("patch_rose_blue");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROSE_RED = registerKey("patch_rose_red");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROSE_PURPLE = registerKey("patch_rose_purple");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROSE_NAT = registerKey("patch_rose_nat");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROSE_MAGIC = registerKey("patch_rose_magic");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ROSE_MIDNIGHT = registerKey("patch_rose_midnight");

    //Feature Made
    public  static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_PILLAR_KEY = registerKey("amethyst_pillar");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_CUSTOM_MUSHROOM_KEY = registerKey("purple_custom_mushroom_key");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_TWIN_CAP_MUSHROOM_KEY = registerKey("purple_twin_cap_mushroom_key");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> TURQUOISE_PRISM_KEY = registerKey("turquoise_prism_key");
    public  static final ResourceKey<ConfiguredFeature<?, ?>> FEYS_PRISM_KEY = registerKey("feys_prism_key");



    public static final ResourceKey<ConfiguredFeature<?, ?>> HUMAN_REALM_JADE_ORE_KEY = registerKey("jade_ore");
    /*public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SAPPHIRE_ORE_KEY = registerKey("nether_sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_SAPPHIRE_ORE_KEY = registerKey("end_stone_sapphire_ore");*/

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        //custom tree start
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

        register(context, PATCH_ROCKS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
         new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.EVES_COBBLESTONE.get().defaultBlockState(),
         1).add(Blocks.AMETHYST_BLOCK.defaultBlockState(), 1).add(ModBlocks.PURPLE_BIOSHROOM.get().defaultBlockState(), 2).add(Blocks.STONE.defaultBlockState(), 1).add(Blocks.COBBLESTONE.defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright

        //ROSES ANCIENT GARDEN
        register(context, PATCH_ROSE_NAT, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.PURPLE_ROSE_BUSH.get().defaultBlockState(),
                        1).add(ModBlocks.ORANGE_ROSE_BUSH.get().defaultBlockState(), 1).add(ModBlocks.YELLOW_ROSE_BUSH.get().defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright
        register(context, PATCH_ROSE_MAGIC, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.FAE_ROSE_BUSH.get().defaultBlockState(),
                        1).add(ModBlocks.SPIRIT_ROSE_BUSH.get().defaultBlockState(), 1).add(ModBlocks.SPIRIT_FAE_ROSE_BUSH.get().defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright
        register(context, PATCH_ROSE_MIDNIGHT, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.BLUE_ROSE_BUSH.get().defaultBlockState(),
                        1).add(ModBlocks.FAE_ROSE_BUSH.get().defaultBlockState(), 1).add(ModBlocks.MIDNIGHTS_REACH.get().defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright
        register(context, PATCH_ROSE_RED, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.MAHOGANY_ROSE_BUSH.get().defaultBlockState(),
                        1).add(ModBlocks.WHITE_ROSE_BUSH.get().defaultBlockState(), 1).add(ModBlocks.CARMINE_ROSE_BUSH.get().defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright
        register(context, PATCH_ROSE_BLUE, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.BLUE_ROSE_BUSH.get().defaultBlockState(),
                        1).add(ModBlocks.SPIRIT_ROSE_BUSH.get().defaultBlockState(), 1).add(ModBlocks.CERULEAN_ROSE_BUSH.get().defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright
        register(context, PATCH_ROSE_PURPLE, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.LAVENDER_ROSE_BUSH.get().defaultBlockState(),
                        1).add(ModBlocks.FAE_ROSE_BUSH.get().defaultBlockState(), 1).add(ModBlocks.COTTONS_ROSE_BUSH.get().defaultBlockState(), 1).add(Blocks.AIR.defaultBlockState(), 100))),
                List.of(Blocks.GRASS_BLOCK), 75));//orig tries for grass is 125, 100was alright

        context.register(AMETHYST_PILLAR_KEY, new ConfiguredFeature<>(ModFeatures.AMETHYST_GOLD_PILLAR.get(), NoneFeatureConfiguration.INSTANCE));
        context.register(PURPLE_CUSTOM_MUSHROOM_KEY, new ConfiguredFeature<>(ModFeatures.CUSTOM_MUSHROOM.get(), NoneFeatureConfiguration.INSTANCE));
        context.register(PURPLE_TWIN_CAP_MUSHROOM_KEY, new ConfiguredFeature<>(ModFeatures.TWIN_CAP_MUSHROOM.get(), NoneFeatureConfiguration.INSTANCE));
        context.register(TURQUOISE_PRISM_KEY, new ConfiguredFeature<>(ModFeatures.TURQUOISE_PRISM.get(), NoneFeatureConfiguration.INSTANCE));
        context.register(FEYS_PRISM_KEY, new ConfiguredFeature<>(ModFeatures.FEYS_PRISM.get(), NoneFeatureConfiguration.INSTANCE));

        var turq = ModBlocks.TURQUOISE_BLOCK.get().defaultBlockState();
        var feys = ModBlocks.FEY_BLOCK.get().defaultBlockState();

        context.register(TURQUOISE_ROCKS_KEY, new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(turq)));
        context.register(FEYS_ROCKS_KEY, new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(feys)));

        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> humanRealmJadeOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        ModBlocks.JADE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_JADE_ORE.get().defaultBlockState()));

        register(context, HUMAN_REALM_JADE_ORE_KEY, Feature.ORE, new OreConfiguration(humanRealmJadeOres, 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(PathToImmortals.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
