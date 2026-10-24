package net.ejibarra.pathtoimmortals.worldgen.biome;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.worldgen.AscentsBiomeDefaultFeatures;
import net.ejibarra.pathtoimmortals.worldgen.ModConfiguredFeatures;
import net.ejibarra.pathtoimmortals.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class

ModBiome {
    public static final ResourceKey<Biome> SPORELAND = register("spore_lands");
    public static final ResourceKey<Biome> DESOLATE_DESERT = register("desolate_desert");
    public static final ResourceKey<Biome> THE_HALLOWED = register("hallowed");
    public static final ResourceKey<Biome> ANCIENT_GARDEN = register("ancient_garden");

    protected static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;

    public static void boostrap(BootstapContext<Biome> context){
        context.register(SPORELAND, sporeBiome(context));
        context.register(DESOLATE_DESERT, desolateDesertBiome(context));
        context.register(THE_HALLOWED, hallowedBiome(context));
        context.register(ANCIENT_GARDEN, ancientGarden(context));

    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome sporeBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.HERETIC.get(), 500, 1, 1));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addWarmFlowers(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);


        AscentsBiomeDefaultFeatures.addRocks(biomeBuilder);
        AscentsBiomeDefaultFeatures.addMegaPoisonMushroom(biomeBuilder);
        AscentsBiomeDefaultFeatures.addTwinCapMushroom(biomeBuilder);
        AscentsBiomeDefaultFeatures.addJadeOre(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.JASMINE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.AMETHYST_PILLAR_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xB026FF)//Neon Purple
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(0x9B30FF)//Vibrant Purple
                        .grassColorOverride(0x7851A9)//orig 0x7f03fc
                        .foliageColorOverride(0xd203fc)
                        .fogColor(OVERWORLD_FOG_COLOR)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)

                        .build()).build();
    }

    public static Biome desolateDesertBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.HERETIC.get(), 500, 1, 1));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        BiomeDefaultFeatures.addBadlandGrass(biomeBuilder);
        BiomeDefaultFeatures.addBadlandExtraVegetation(biomeBuilder);


        AscentsBiomeDefaultFeatures.addJadeOre(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.00f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3A3A3A)//Dark Grey
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(0xD0D0D0)//Smooth, slightly brighter grey
                        .grassColorOverride(0xC0C0C0)//silverish
                        .foliageColorOverride(0xB7B7B7)//metallic neutral
                        .fogColor(OVERWORLD_FOG_COLOR)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)

                        .build()).build();
    }

    public static Biome hallowedBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.HERETIC.get(), 500, 1, 1));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addWarmFlowers(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);

        AscentsBiomeDefaultFeatures.addTurquoiseRocks(biomeBuilder);
        //AscentsBiomeDefaultFeatures.addFeysPrism(biomeBuilder);
        AscentsBiomeDefaultFeatures.addTurqPrism(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SPIRIT_PLACED_KEY);


        AscentsBiomeDefaultFeatures.addJadeOre(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.35f)
                .temperature(0.65f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x008080)//Teal
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(0x2AF7FF)//Light neon blue
                        .grassColorOverride(0x00F5FF)//Vibrant aqua
                        .foliageColorOverride(0x00FFFF)//Cyan
                        .fogColor(OVERWORLD_FOG_COLOR)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)

                        .build()).build();
    }

    public static Biome ancientGarden(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.HERETIC.get(), 500, 1, 1));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addWarmFlowers(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);

        AscentsBiomeDefaultFeatures.addRosesRed(biomeBuilder);
        AscentsBiomeDefaultFeatures.addRosesBlue(biomeBuilder);
        AscentsBiomeDefaultFeatures.addRosesPurple(biomeBuilder);
        AscentsBiomeDefaultFeatures.addRosesMagic(biomeBuilder);
        AscentsBiomeDefaultFeatures.addRosesMidnight(biomeBuilder);
        AscentsBiomeDefaultFeatures.addRosesNat(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SPIRIT_PLACED_KEY);


        AscentsBiomeDefaultFeatures.addJadeOre(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.15f)
                .temperature(0.40f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x008080)//Teal
                        .waterFogColor(NORMAL_WATER_FOG_COLOR)
                        .skyColor(0xFF1493)//Deep Pink
                        .grassColorOverride(0xC71585)//Magenta Pink
                        .foliageColorOverride(0xB03060)//Dark Orchid Pink
                        .fogColor(OVERWORLD_FOG_COLOR)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)

                        .build()).build();
    }

    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(PathToImmortals.MOD_ID, name));
    }
}
