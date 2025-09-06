package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.worldgen.ModBiomeModifiers;
import net.ejibarra.pathtoimmortals.worldgen.ModConfiguredFeatures;
import net.ejibarra.pathtoimmortals.worldgen.ModPlacedFeatures;
import net.ejibarra.pathtoimmortals.worldgen.biome.ModBiome;
import net.ejibarra.pathtoimmortals.worldgen.dimensions.ModDimensions;
import net.ejibarra.pathtoimmortals.worldgen.noise.ModNoises;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.NoiseData;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.BIOME, ModBiome::boostrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.NOISE, ModNoises::bootstrap)//new//new
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(PathToImmortals.MOD_ID));
    }
}
