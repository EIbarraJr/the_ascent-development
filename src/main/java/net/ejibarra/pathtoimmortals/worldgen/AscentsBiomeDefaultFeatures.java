package net.ejibarra.pathtoimmortals.worldgen;

import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class AscentsBiomeDefaultFeatures {
    public static void addRocks(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROCKS);
    }

    public static void addFeysRocks(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FEYS_ROCKS_PLACED_KEY);
    }

    public static void addTurquoiseRocks(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TURQUOISE_ROCKS_PLACED_KEY);
    }

    public static void addMegaPoisonMushroom(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CUSTOM_PURPLE_MUSHROOM_PLACED_KEY);
    }

    public static void addTwinCapMushroom(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PURPLE_TWIN_CAP_MUSHROOM_PLACED_KEY);
    }

    public static void addJadeOre(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.HUMAN_REALM_JADE_ORE_PLACED_KEY);
    }

    public static void addTurqPrism(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.TURQUOISE_PRISM_PLACED_KEY);
    }

    public static void addRosesRed(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROSES_RED);
    }

    public static void addRosesBlue(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROSES_BLUE);
    }

    public static void addRosesPurple(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROSES_PURPLE);
    }

    public static void addRosesMidnight(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROSES_MIDNIGHT);
    }

    public static void addRosesMagic(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROSES_MAGIC);
    }

    public static void addRosesNat(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.NOISE_ROSES_NAT);
    }

    public static void addFeysPrism(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.FEYS_PRISM_PLACED_KEY);
    }
}
