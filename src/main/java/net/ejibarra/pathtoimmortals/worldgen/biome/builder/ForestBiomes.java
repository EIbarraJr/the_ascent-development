package net.ejibarra.pathtoimmortals.worldgen.biome.builder;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ForestBiomes {
    protected static final int NORMAL_WATER_COLOR = 4159204;
    protected static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static MobSpawnSettings.Builder baseForestSpawning(boolean hasWolfSpawns) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseForestGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean addDefaultFlowers) {
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        //RuBiomeDefaultFeatures.globalOverworldGeneration(biomeBuilder);
        //RuBiomeDefaultFeatures.mediumGrass(biomeBuilder);
        if(addDefaultFlowers){
            BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        }
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //RuBiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        return biomeBuilder;
    }

    /*public static Biome autumnalMapleForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.5F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(9877306)
                .grassColorOverride(12896058)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder biomeBuilder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        RuBiomeDefaultFeatures.autumnalTrees(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        RuBiomeDefaultFeatures.addTrillium(biomeBuilder);
        RuBiomeDefaultFeatures.addMapleGroundcover(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.AUTUMNAL_SHRUB_MIX);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(true);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.25f)
                .downfall(0.35f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }*/
}
