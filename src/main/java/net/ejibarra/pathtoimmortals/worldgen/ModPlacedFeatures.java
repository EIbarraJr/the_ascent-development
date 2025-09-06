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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FIERO_PLACED_KEY = registerKey("fiero_placed_key");
    public static final ResourceKey<PlacedFeature> JASMINE_PLACED_KEY = registerKey("jasmine_placed_key");
    public static final ResourceKey<PlacedFeature> KINDER_PLACED_KEY = registerKey("kinder_placed_key");
    public static final ResourceKey<PlacedFeature> MANGO_PLACED_KEY = registerKey("mango_placed_key");
    public static final ResourceKey<PlacedFeature> SPIRIT_PLACED_KEY = registerKey("spirit_placed_key");
    public static final ResourceKey<PlacedFeature> JADED_PLACED_KEY = registerKey("jaded_placed_key");

    /*public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_SAPPHIRE_ORE_PLACED_KEY = registerKey("nether_sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> END_SAPPHIRE_ORE_PLACED_KEY = registerKey("end_sapphire_ore_placed");*/

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> featureHolderGetter = context.lookup(Registries.CONFIGURED_FEATURE);


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
                        PlacementUtils.countExtra(1, 0.1f, 1),
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




        /*register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_SAPPHIRE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, END_SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_SAPPHIRE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));*/

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
