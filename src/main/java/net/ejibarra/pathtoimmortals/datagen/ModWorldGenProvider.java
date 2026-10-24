package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.worldgen.ModBiomeModifiers;
import net.ejibarra.pathtoimmortals.worldgen.ModConfiguredFeatures;
import net.ejibarra.pathtoimmortals.worldgen.ModPlacedFeatures;
import net.ejibarra.pathtoimmortals.worldgen.biome.ModBiome;
import net.ejibarra.pathtoimmortals.worldgen.dimensions.ModDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    // Order matters: BIOME / DIMENSION_TYPE / NOISE / NOISE_SETTINGS BEFORE LEVEL_STEM
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE,    ModPlacedFeatures::bootstrap)
            .add(Registries.BIOME,             ModBiome::boostrap)
            .add(Registries.DIMENSION_TYPE,    ModDimensions::bootstrapType)// must be before LevelStem
            .add(Registries.LEVEL_STEM,        ModDimensions::bootstrapStem) // reads your noise settings
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);



    // Keep this ctor only if some code still calls it; otherwise, you can remove it.
    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(PathToImmortals.MOD_ID));
    }
}
