package net.ejibarra.pathtoimmortals.worldgen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.awt.*;
import java.util.List;

import static net.ejibarra.pathtoimmortals.worldgen.ModConfiguredFeatures.*;

public class ModPlacedFeatures {
    //KEYS
    public static final ResourceKey<PlacedFeature> FIERO_PLACED_KEY = registerKey("fiero_placed_key");
    public static final ResourceKey<PlacedFeature> JASMINE_PLACED_KEY = registerKey("jasmine_placed_key");
    public static final ResourceKey<PlacedFeature> KINDER_PLACED_KEY = registerKey("kinder_placed_key");
    public static final ResourceKey<PlacedFeature> MANGO_PLACED_KEY = registerKey("mango_placed_key");
    public static final ResourceKey<PlacedFeature> SPIRIT_PLACED_KEY = registerKey("spirit_placed_key");
    public static final ResourceKey<PlacedFeature> JADED_PLACED_KEY = registerKey("jaded_placed_key");
    public static final ResourceKey<PlacedFeature> TURQUOISE_ROCKS_PLACED_KEY = registerKey("turquoise_rocks_placed_key");
    public static final ResourceKey<PlacedFeature> FEYS_ROCKS_PLACED_KEY = registerKey("feys_rocks_placed_key");
    public static final ResourceKey<PlacedFeature> NOISE_ROCKS = registerKey("noise_rocks");
    public static final ResourceKey<PlacedFeature> NOISE_ROSES_RED = registerKey("noise_roses_red");
    public static final ResourceKey<PlacedFeature> NOISE_ROSES_BLUE = registerKey("noise_roses_blue");
    public static final ResourceKey<PlacedFeature> NOISE_ROSES_PURPLE = registerKey("noise_roses_purple");
    public static final ResourceKey<PlacedFeature> NOISE_ROSES_MIDNIGHT = registerKey("noise_roses_midnight");
    public static final ResourceKey<PlacedFeature> NOISE_ROSES_MAGIC = registerKey("noise_roses_magic");
    public static final ResourceKey<PlacedFeature> NOISE_ROSES_NAT = registerKey("noise_roses_nat");

    //Custom Features
    public static final ResourceKey<PlacedFeature> AMETHYST_PILLAR_PLACED_KEY = registerKey("amethyst_pillar_placed_key");
    public static final ResourceKey<PlacedFeature> CUSTOM_PURPLE_MUSHROOM_PLACED_KEY = registerKey("purple_custom_mushroom_key");
    public static final ResourceKey<PlacedFeature> PURPLE_TWIN_CAP_MUSHROOM_PLACED_KEY = registerKey("purple_twin_cap_mushroom_key");
    public static final ResourceKey<PlacedFeature> TURQUOISE_PRISM_PLACED_KEY = registerKey("turquoise_prism_feature");
    public static final ResourceKey<PlacedFeature> FEYS_PRISM_PLACED_KEY = registerKey("feys_prism_feature");

    public static final ResourceKey<PlacedFeature> HUMAN_REALM_JADE_ORE_PLACED_KEY = registerKey("human_realm_jade_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> featureHolderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> cf = featureHolderGetter.getOrThrow(AMETHYST_PILLAR_KEY);



        /*WE ADDED A 2x2 clearance funtion to check if trees have room to spawn, also the PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), both are added after the Heightmap for better spawning
        * currently works and is nicely populated with all tree may need to change the numbers current# (1, 0.1f, 1) to get crazy forest remove 2x2 function and PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)*/
        register(context, FIERO_PLACED_KEY, featureHolderGetter.getOrThrow(ModConfiguredFeatures.FIERO_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                        twoByTwoBaseClearance(),
                        BiomeFilter.biome()
                )
        );
        register(context, JASMINE_PLACED_KEY, featureHolderGetter.getOrThrow(ModConfiguredFeatures.JASMINE_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.05f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                        twoByTwoBaseClearance(),
                        BiomeFilter.biome()
                )
        );
        register(context, KINDER_PLACED_KEY, featureHolderGetter.getOrThrow(ModConfiguredFeatures.KINDER_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                        twoByTwoBaseClearance(),
                        BiomeFilter.biome()
                )
        );
        register(context, MANGO_PLACED_KEY, featureHolderGetter.getOrThrow(ModConfiguredFeatures.MANGO_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                        twoByTwoBaseClearance(),
                        BiomeFilter.biome()
                )
        );
        register(context, SPIRIT_PLACED_KEY, featureHolderGetter.getOrThrow(ModConfiguredFeatures.SPIRIT_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                        twoByTwoBaseClearance(),
                        BiomeFilter.biome()
                )
        );
        register(context, JADED_PLACED_KEY, featureHolderGetter.getOrThrow(ModConfiguredFeatures.JADED_KEY),
                List.of(
                        PlacementUtils.countExtra(1, 0.1f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                        PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                        twoByTwoBaseClearance(),
                        BiomeFilter.biome()
                )
        );

        register(context, NOISE_ROCKS, featureHolderGetter.getOrThrow(ModConfiguredFeatures.PATCH_ROCKS), List.of(CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        //ROSES
        register(context, NOISE_ROSES_RED, featureHolderGetter.getOrThrow(PATCH_ROSE_RED), List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, NOISE_ROSES_BLUE, featureHolderGetter.getOrThrow(PATCH_ROSE_BLUE), List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, NOISE_ROSES_PURPLE, featureHolderGetter.getOrThrow(PATCH_ROSE_PURPLE), List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, NOISE_ROSES_MIDNIGHT, featureHolderGetter.getOrThrow(PATCH_ROSE_MIDNIGHT), List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, NOISE_ROSES_MAGIC, featureHolderGetter.getOrThrow(PATCH_ROSE_MAGIC), List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, NOISE_ROSES_NAT, featureHolderGetter.getOrThrow(PATCH_ROSE_NAT), List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        // Placement: rare, on heightmap, spread in square
        // Tweak frequency as you like (e.g., RarityFilter.onAverageOnceEvery(8))
        //1 per ~10 chunks
        context.register(AMETHYST_PILLAR_PLACED_KEY, new PlacedFeature(cf, List.of(RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(CUSTOM_PURPLE_MUSHROOM_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(PURPLE_CUSTOM_MUSHROOM_KEY),
                List.of(RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(),PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
        context.register(PURPLE_TWIN_CAP_MUSHROOM_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(PURPLE_TWIN_CAP_MUSHROOM_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));

        context.register(TURQUOISE_ROCKS_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(TURQUOISE_ROCKS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(8),InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, SurfaceWaterDepthFilter.forMaxDepth(0), BiomeFilter.biome())));
        context.register(FEYS_ROCKS_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(FEYS_ROCKS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(8),InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, SurfaceWaterDepthFilter.forMaxDepth(0), BiomeFilter.biome())));

        context.register(HUMAN_REALM_JADE_ORE_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(HUMAN_REALM_JADE_ORE_KEY),
                List.of(CountPlacement.of(7), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(100)), BiomeFilter.biome())));

        context.register(TURQUOISE_PRISM_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(TURQUOISE_PRISM_KEY), List.of(CountPlacement.of(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(1)), BiomeFilter.biome())));
        context.register(FEYS_PRISM_PLACED_KEY, new PlacedFeature(featureHolderGetter.getOrThrow(FEYS_PRISM_KEY), List.of(CountPlacement.of(1), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(1)), BiomeFilter.biome())));

    }
    private static PlacementModifier twoByTwoBaseClearance() {
        // Require AIR at the 2×2 base (y = 0) and one block above (y = 1),
        // and require DIRT-tag below each of the 4 trunk blocks.
        BlockPredicate[] preds = new BlockPredicate[] {
                // air at y=0
                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0), Blocks.AIR),
                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.AIR),
                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.AIR),
                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 1), Blocks.AIR),

                // air at y=1
                BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR),
                BlockPredicate.matchesBlocks(new BlockPos(1, 1, 0), Blocks.AIR),
                BlockPredicate.matchesBlocks(new BlockPos(0, 1, 1), Blocks.AIR),
                BlockPredicate.matchesBlocks(new BlockPos(1, 1, 1), Blocks.AIR),

                // dirt-like below (y = -1)
                BlockPredicate.matchesTag(new BlockPos(0, -1, 0), BlockTags.DIRT),
                BlockPredicate.matchesTag(new BlockPos(1, -1, 0), BlockTags.DIRT),
                BlockPredicate.matchesTag(new BlockPos(0, -1, 1), BlockTags.DIRT),
                BlockPredicate.matchesTag(new BlockPos(1, -1, 1), BlockTags.DIRT)
        };
        return BlockPredicateFilter.forPredicate(BlockPredicate.allOf(preds));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(PathToImmortals.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
