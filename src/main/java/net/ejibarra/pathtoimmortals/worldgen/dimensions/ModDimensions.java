package net.ejibarra.pathtoimmortals.worldgen.dimensions;

import com.mojang.datafixers.util.Pair;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.worldgen.biome.ModBiome;
import net.minecraft.core.Holder;
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
        HolderGetter<DimensionType> dimTypes =
                context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings =
                context.lookup(Registries.NOISE_SETTINGS);

        /*
         * Climate.parameters argument order:
         *
         * 1. Temperature
         * 2. Humidity
         * 3. Continentalness
         * 4. Erosion
         * 5. Depth
         * 6. Weirdness
         * 7. Offset
         */

        // =========================================================
        // GENERAL-PURPOSE RANGES
        // =========================================================

        Climate.Parameter FULL_RANGE =
                Climate.Parameter.span(-1.0F, 1.0F);

        /*
         * Keep the depth range broad for ordinary surface biomes.
         *
         * Depth participates in distinguishing surface terrain from
         * valleys, caves, and other vertical terrain regions.
         *
         * Your previous code used WD_NEUTRAL in the depth position.
         */
        Climate.Parameter SURFACE_DEPTH =
                Climate.Parameter.span(-0.20F, 0.20F);

        // =========================================================
        // CONTINENTALNESS
        // =========================================================

        // Far from land and normally deeper underwater.
        Climate.Parameter DEEP_OCEAN =
                Climate.Parameter.span(-1.05F, -0.75F);

        // Ordinary ocean.
        Climate.Parameter OCEAN =
                Climate.Parameter.span(-0.75F, -0.45F);

        // Transition between ocean and land.
        Climate.Parameter COAST =
                Climate.Parameter.span(-0.45F, -0.16F);

        // Land close to the coastline.
        Climate.Parameter NEAR_INLAND =
                Climate.Parameter.span(-0.16F, 0.25F);

        // Normal inland terrain.
        Climate.Parameter MID_INLAND =
                Climate.Parameter.span(0.25F, 0.60F);

        // Deep continental interiors.
        Climate.Parameter FAR_INLAND =
                Climate.Parameter.span(0.60F, 1.00F);

        // Covers all inland regions.
        Climate.Parameter ALL_INLAND =
                Climate.Parameter.span(-0.16F, 1.00F);

        // =========================================================
        // TEMPERATURE
        // =========================================================

        Climate.Parameter TEMP_FROZEN =
                Climate.Parameter.span(-1.00F, -0.45F);

        Climate.Parameter TEMP_COOL =
                Climate.Parameter.span(-0.45F, -0.10F);

        Climate.Parameter TEMP_TEMPERATE =
                Climate.Parameter.span(-0.25F, 0.35F);

        Climate.Parameter TEMP_WARM =
                Climate.Parameter.span(0.15F, 0.70F);

        Climate.Parameter TEMP_HOT =
                Climate.Parameter.span(0.55F, 1.00F);

        Climate.Parameter TEMP_EXTREME_HOT =
                Climate.Parameter.span(0.70F, 1.00F);

        // =========================================================
        // HUMIDITY
        // =========================================================

        Climate.Parameter HUMIDITY_EXTREME_DRY =
                Climate.Parameter.span(-1.00F, -0.55F);

        Climate.Parameter HUMIDITY_DRY =
                Climate.Parameter.span(-1.00F, -0.40F);

        Climate.Parameter HUMIDITY_PLAINS =
                Climate.Parameter.span(-0.40F, 0.25F);

        Climate.Parameter HUMIDITY_MODERATE =
                Climate.Parameter.span(0.05F, 0.55F);

        Climate.Parameter HUMIDITY_FOREST =
                Climate.Parameter.span(0.20F, 0.80F);

        Climate.Parameter HUMIDITY_GARDEN =
                Climate.Parameter.span(0.25F, 0.80F);

        Climate.Parameter HUMIDITY_WET =
                Climate.Parameter.span(0.55F, 1.00F);

        // =========================================================
        // EROSION
        // =========================================================

        /*
         * Lower erosion generally favors more rugged terrain.
         * Higher erosion generally favors smoother terrain.
         */

        Climate.Parameter EROSION_RUGGED =
                Climate.Parameter.span(-0.75F, 0.00F);

        Climate.Parameter EROSION_ROLLING =
                Climate.Parameter.span(-0.25F, 0.65F);

        Climate.Parameter EROSION_GENERAL =
                Climate.Parameter.span(0.00F, 1.00F);

        Climate.Parameter EROSION_FLAT =
                Climate.Parameter.span(0.45F, 1.00F);

        Climate.Parameter EROSION_GARDEN =
                Climate.Parameter.span(0.35F, 0.95F);

        // =========================================================
        // WEIRDNESS
        // =========================================================

        Climate.Parameter WEIRDNESS_FULL =
                Climate.Parameter.span(-1.00F, 1.00F);

        Climate.Parameter WEIRDNESS_NEGATIVE =
                Climate.Parameter.span(-1.00F, -0.15F);

        Climate.Parameter WEIRDNESS_SPORELANDS =
                Climate.Parameter.span(-0.75F, -0.15F);

        Climate.Parameter WEIRDNESS_NEUTRAL =
                Climate.Parameter.span(-0.20F, 0.20F);

        Climate.Parameter WEIRDNESS_DESERT =
                Climate.Parameter.span(-1.00F, 0.25F);

        Climate.Parameter WEIRDNESS_DESOLATE =
                Climate.Parameter.span(0.25F, 1.00F);

        /*
         * A narrow band makes the Ancient Garden less common while
         * still allowing it to appear in appropriately temperate,
         * humid, smooth inland areas.
         */
        Climate.Parameter WEIRDNESS_ANCIENT_GARDEN =
                Climate.Parameter.span(0.35F, 0.90F);

        // =========================================================
        // BIOME PARAMETER LIST
        // =========================================================

        List<Pair<Climate.ParameterPoint, Holder<Biome>>> biomeParameters =
                List.of(

                        // =================================================
                        // DEEP OCEANS
                        // =================================================

                        /*
                         * Cold deep ocean.
                         *
                         * Adding temperature-specific oceans makes the coast
                         * and ocean layout feel more connected to nearby land.
                         */
                        Pair.of(
                                Climate.parameters(
                                        TEMP_FROZEN,
                                        FULL_RANGE,
                                        DEEP_OCEAN,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.DEEP_COLD_OCEAN)
                        ),

                        // Temperate deep ocean.
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.45F, 0.55F),
                                        FULL_RANGE,
                                        DEEP_OCEAN,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.DEEP_OCEAN)
                        ),

                        // Warm deep ocean.
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(0.55F, 1.00F),
                                        FULL_RANGE,
                                        DEEP_OCEAN,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(
                                        Biomes.DEEP_LUKEWARM_OCEAN
                                )
                        ),

                        // =================================================
                        // NORMAL OCEANS
                        // =================================================

                        // Frozen and cold ocean areas.
                        Pair.of(
                                Climate.parameters(
                                        TEMP_FROZEN,
                                        FULL_RANGE,
                                        OCEAN,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.COLD_OCEAN)
                        ),

                        // Normal temperate ocean.
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.45F, 0.55F),
                                        FULL_RANGE,
                                        OCEAN,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.OCEAN)
                        ),

                        // Warm-region ocean.
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(0.55F, 1.00F),
                                        FULL_RANGE,
                                        OCEAN,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.LUKEWARM_OCEAN)
                        ),

                        // =================================================
                        // BEACHES
                        // =================================================

                        // Snowy beaches next to cold land.
                        Pair.of(
                                Climate.parameters(
                                        TEMP_FROZEN,
                                        FULL_RANGE,
                                        COAST,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.SNOWY_BEACH)
                        ),

                        // Normal beach covering most coastal climates.
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.45F, 1.00F),
                                        FULL_RANGE,
                                        COAST,
                                        FULL_RANGE,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.BEACH)
                        ),

                        /*
                         * Stony shore in a rugged coastal slice.
                         *
                         * Because this uses the same coast band but lower erosion,
                         * it can interrupt ordinary beaches around rough terrain.
                         */
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.45F, 0.55F),
                                        FULL_RANGE,
                                        COAST,
                                        EROSION_RUGGED,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.STONY_SHORE)
                        ),

                        // =================================================
                        // SNOWY TAIGA
                        // =================================================

                        /*
                         * Cold and moderately wet.
                         *
                         * Multiple continentalness entries help it appear near
                         * coasts as well as farther inland.
                         */
                        Pair.of(
                                Climate.parameters(
                                        TEMP_FROZEN,
                                        Climate.Parameter.span(0.00F, 0.75F),
                                        NEAR_INLAND,
                                        EROSION_ROLLING,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.SNOWY_TAIGA)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_FROZEN,
                                        Climate.Parameter.span(0.00F, 0.75F),
                                        Climate.Parameter.span(0.25F, 1.00F),
                                        Climate.Parameter.span(-0.50F, 0.80F),
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.SNOWY_TAIGA)
                        ),

                        // =================================================
                        // NORMAL PLAINS
                        // =================================================

                        /*
                         * Plains are intentionally common.
                         *
                         * They use a broad inland range, temperate climate,
                         * and relatively smooth terrain.
                         */
                        Pair.of(
                                Climate.parameters(
                                        TEMP_TEMPERATE,
                                        HUMIDITY_PLAINS,
                                        NEAR_INLAND,
                                        EROSION_GENERAL,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.PLAINS)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_TEMPERATE,
                                        HUMIDITY_PLAINS,
                                        MID_INLAND,
                                        EROSION_GENERAL,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.PLAINS)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_TEMPERATE,
                                        HUMIDITY_PLAINS,
                                        FAR_INLAND,
                                        EROSION_FLAT,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.PLAINS)
                        ),

                        // =================================================
                        // SPORELANDS
                        // =================================================

                        /*
                         * Sporelands overlap normal plains but are:
                         *
                         * - Slightly warmer
                         * - Slightly wetter
                         * - Restricted to a negative weirdness band
                         *
                         * This should let both Plains and Sporelands exist in
                         * similar terrain without Sporelands replacing Plains.
                         *
                         * Rename ModBiome.SPORELANDS if your actual ResourceKey
                         * has a different name.
                         */
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(0.00F, 0.50F),
                                        HUMIDITY_MODERATE,
                                        NEAR_INLAND,
                                        EROSION_FLAT,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_SPORELANDS,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(ModBiome.SPORELAND)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(0.00F, 0.50F),
                                        HUMIDITY_MODERATE,
                                        Climate.Parameter.span(0.25F, 0.80F),
                                        Climate.Parameter.span(0.10F, 1.00F),
                                        SURFACE_DEPTH,
                                        WEIRDNESS_SPORELANDS,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(ModBiome.SPORELAND)
                        ),

                        // =================================================
                        // THE HALLOWED
                        // =================================================

                        /*
                         * Temperate, moist, forest-like terrain.
                         *
                         * The broad weirdness range makes this a regular biome,
                         * while its humidity separates it from Plains.
                         */
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.10F, 0.45F),
                                        HUMIDITY_FOREST,
                                        NEAR_INLAND,
                                        EROSION_ROLLING,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(ModBiome.THE_HALLOWED)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.10F, 0.45F),
                                        HUMIDITY_FOREST,
                                        MID_INLAND,
                                        EROSION_ROLLING,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(ModBiome.THE_HALLOWED)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.10F, 0.45F),
                                        HUMIDITY_FOREST,
                                        FAR_INLAND,
                                        Climate.Parameter.span(-0.25F, 0.50F),
                                        SURFACE_DEPTH,
                                        WEIRDNESS_FULL,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(ModBiome.THE_HALLOWED)
                        ),

                        // =================================================
                        // SWAMP
                        // =================================================

                        /*
                         * Swamps are warm, wet, smooth, and closer to coasts
                         * than the deep continental interior.
                         */
                        Pair.of(
                                Climate.parameters(
                                        TEMP_WARM,
                                        HUMIDITY_WET,
                                        NEAR_INLAND,
                                        EROSION_FLAT,
                                        SURFACE_DEPTH,
                                        Climate.Parameter.span(-0.40F, 0.40F),
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.SWAMP)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_WARM,
                                        HUMIDITY_WET,
                                        Climate.Parameter.span(0.25F, 0.45F),
                                        EROSION_FLAT,
                                        SURFACE_DEPTH,
                                        Climate.Parameter.span(-0.40F, 0.40F),
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.SWAMP)
                        ),

                        // =================================================
                        // ANCIENT GARDEN
                        // =================================================

                        /*
                         * Ancient Garden is similar to moist Plains/Hallowed,
                         * but its narrow weirdness range makes it uncommon.
                         *
                         * It receives two continentalness entries so it is rare
                         * without being restricted to one tiny inland band.
                         *
                         * Rename ModBiome.ANCIENT_GARDEN if necessary.
*/
                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.15F, 0.40F),
                                        HUMIDITY_GARDEN,
                                        Climate.Parameter.span(0.05F, 0.45F),
                                        EROSION_GARDEN,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_ANCIENT_GARDEN,
                                        0.05F
                                ),
                                biomeRegistry.getOrThrow(
                                        ModBiome.ANCIENT_GARDEN
                                )
                        ),

                        Pair.of(
                                Climate.parameters(
                                        Climate.Parameter.span(-0.15F, 0.40F),
                                        HUMIDITY_GARDEN,
                                        Climate.Parameter.span(0.45F, 0.90F),
                                        EROSION_GARDEN,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_ANCIENT_GARDEN,
                                        0.05F
                                ),
                                biomeRegistry.getOrThrow(
                                        ModBiome.ANCIENT_GARDEN
                                )
                        ),

                        // =================================================
                        // NORMAL DESERT
                        // =================================================

                        /*
                         * The normal Desert owns most hot, dry, relatively
                         * smooth or negative-weirdness terrain.
                         */
                        Pair.of(
                                Climate.parameters(
                                        TEMP_HOT,
                                        HUMIDITY_DRY,
                                        NEAR_INLAND,
                                        EROSION_GENERAL,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_DESERT,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.DESERT)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_HOT,
                                        HUMIDITY_DRY,
                                        MID_INLAND,
                                        EROSION_GENERAL,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_DESERT,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.DESERT)
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_HOT,
                                        HUMIDITY_DRY,
                                        FAR_INLAND,
                                        EROSION_GENERAL,
                                        SURFACE_DEPTH,
                                        WEIRDNESS_DESERT,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(Biomes.DESERT)
                        ),

                        // =================================================
                        // DESOLATE DESERT
                        // =================================================

                        /*
                         * The Desolate Desert is an extreme version of Desert.
                         *
                         * It prefers:
                         * - Higher temperature
                         * - Lower humidity
                         * - Lower erosion / rougher terrain
                         * - Positive weirdness
                         */
                        Pair.of(
                                Climate.parameters(
                                        TEMP_EXTREME_HOT,
                                        HUMIDITY_EXTREME_DRY,
                                        NEAR_INLAND,
                                        Climate.Parameter.span(-0.75F, 0.35F),
                                        SURFACE_DEPTH,
                                        WEIRDNESS_DESOLATE,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(
                                        ModBiome.DESOLATE_DESERT
                                )
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_EXTREME_HOT,
                                        HUMIDITY_EXTREME_DRY,
                                        MID_INLAND,
                                        Climate.Parameter.span(-0.75F, 0.35F),
                                        SURFACE_DEPTH,
                                        WEIRDNESS_DESOLATE,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(
                                        ModBiome.DESOLATE_DESERT
                                )
                        ),

                        Pair.of(
                                Climate.parameters(
                                        TEMP_EXTREME_HOT,
                                        HUMIDITY_EXTREME_DRY,
                                        FAR_INLAND,
                                        Climate.Parameter.span(-0.75F, 0.35F),
                                        SURFACE_DEPTH,
                                        WEIRDNESS_DESOLATE,
                                        0.0F
                                ),
                                biomeRegistry.getOrThrow(
                                        ModBiome.DESOLATE_DESERT
                                )
                        )
                );

        NoiseBasedChunkGenerator noiseBasedChunkGenerator =
                new NoiseBasedChunkGenerator(
                        MultiNoiseBiomeSource.createFromList(
                                new Climate.ParameterList<>(biomeParameters)
                        ),
                        noiseGenSettings.getOrThrow(
                                NoiseGeneratorSettings.OVERWORLD
                        )
                );

        LevelStem stem = new LevelStem(
                dimTypes.getOrThrow(HUMAN_REALM_TYPE),
                noiseBasedChunkGenerator
        );

        context.register(HUMAN_KEY, stem);
    }
}
