package net.ejibarra.pathtoimmortals.worldgen.features;// ModFeatures.java


import net.ejibarra.pathtoimmortals.PathToImmortals;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, PathToImmortals.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AMETHYST_GOLD_PILLAR =
            FEATURES.register("amethyst_gold_pillar",
                    () -> new AmethystGoldPillarFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CUSTOM_MUSHROOM =
            FEATURES.register("custom_mushroom",
                    () -> new CustomMushroomFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> TWIN_CAP_MUSHROOM =
            FEATURES.register("twin_cap_mushroom", ()-> new TwinCapMushroomFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> TURQUOISE_PRISM =
            FEATURES.register("turquoise_prism", ()-> new TurquoisePrismFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FEYS_PRISM =
            FEATURES.register("feys_prism", ()-> new FeysPrismFeature(NoneFeatureConfiguration.CODEC));
}
