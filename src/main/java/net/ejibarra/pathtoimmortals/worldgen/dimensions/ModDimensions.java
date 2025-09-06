package net.ejibarra.pathtoimmortals.worldgen.dimensions;

import com.mojang.datafixers.util.Pair;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.worldgen.biome.ModBiome;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> HUMAN_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(PathToImmortals.MOD_ID, "human_realm"));
    public static final ResourceKey<Level> HUMAN_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(PathToImmortals.MOD_ID, "human_realm"));
    public static final ResourceKey<DimensionType> HUMAN_REALM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(PathToImmortals.MOD_ID, "human_realm_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(HUMAN_REALM_TYPE, new DimensionType(
                 OptionalLong.empty(),// fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0.025f, // ambientLight
                new DimensionType.MonsterSettings(false, true, ConstantInt.of(7), 7)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        /*NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.TEST_BIOME_2)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));*/

        var ER_FLAT    = Climate.Parameter.span(0.60F, 1.00F);
        var WD_NEUTRAL = Climate.Parameter.span(-0.15F, 0.15F);

// Bands
        var C_DEEP_OCEAN = Climate.Parameter.span(-1.00F, -0.70F);
        var C_OCEAN      = Climate.Parameter.span(-0.70F, -0.45F);
        var C_COAST      = Climate.Parameter.span(-0.45F, -0.30F);
        var C_LAND       = Climate.Parameter.span(-0.05F,  1.00F); // small gap from coast

        var T_WARM  = Climate.Parameter.span(0.55F, 1.00F);
        var H_MOIST = Climate.Parameter.span(0.10F, 1.00F);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(// DESERT — hot, dry, inland, fairly flat
                                // --- OCEANS ---
                                Pair.of(
                                        Climate.parameters(
                                                T_WARM, H_MOIST, C_DEEP_OCEAN, ER_FLAT, WD_NEUTRAL,
                                                Climate.Parameter.point(-0.20F), 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN)
                                ),
                                Pair.of(
                                        Climate.parameters(
                                                T_WARM, H_MOIST, C_OCEAN, ER_FLAT, WD_NEUTRAL,
                                                Climate.Parameter.point(-0.15F), 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.LUKEWARM_OCEAN)
                                ),

// --- COAST BUFFER (prevents land from bleeding into ocean) ---
                                Pair.of(
                                        Climate.parameters(
                                                Climate.Parameter.span(0.40F, 0.95F), // warm
                                                Climate.Parameter.span(-0.30F, 0.80F),
                                                C_COAST, ER_FLAT, WD_NEUTRAL,
                                                Climate.Parameter.point(-0.05F), 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.BEACH)
                                ),
// Optionally add stony shore as a second coastal band with slightly lower erosion

// --- LAND EXAMPLES ---
                                Pair.of(
                                        Climate.parameters(
                                                Climate.Parameter.span(0.85F, 1.00F), // hot
                                                Climate.Parameter.span(-1.00F, -0.50F), // dry
                                                C_LAND, ER_FLAT, WD_NEUTRAL,
                                                Climate.Parameter.point(0.05F), 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.DESERT)
                                ),
                                Pair.of(
                                        Climate.parameters(
                                                Climate.Parameter.span(0.30F, 0.80F),
                                                Climate.Parameter.span(0.20F, 0.80F),
                                                C_LAND, ER_FLAT, WD_NEUTRAL,
                                                Climate.Parameter.point(0.00F), 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.PLAINS)
                                ),
                                Pair.of(
                                        Climate.parameters(
                                                Climate.Parameter.span(0.00F, 0.50F),
                                                Climate.Parameter.span(-0.20F, 0.20F),
                                                C_LAND, Climate.Parameter.span(0.30F, 0.70F), // a little more relief if you want
                                                WD_NEUTRAL,
                                                Climate.Parameter.point(0.03F), 0.0F),
                                        biomeRegistry.getOrThrow(ModBiome.TEST_BIOME)
                                )
                        ))
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.HUMAN_REALM_TYPE), noiseBasedChunkGenerator);


        context.register(HUMAN_KEY, stem);
    }
}
