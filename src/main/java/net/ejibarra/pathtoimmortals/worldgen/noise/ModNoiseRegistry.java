package net.ejibarra.pathtoimmortals.worldgen.noise;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoiseRegistry {
    public static void bootstrap(BootstapContext<NormalNoise.NoiseParameters> context) {
        ModNoises.bootstrap(context);
    }

    public static ResourceKey<NormalNoise.NoiseParameters> createKey(String name) {
        return ResourceKey.create(Registries.NOISE, new ResourceLocation(PathToImmortals.MOD_ID, name));
    }
}
