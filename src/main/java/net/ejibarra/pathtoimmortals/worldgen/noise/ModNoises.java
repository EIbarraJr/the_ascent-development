package net.ejibarra.pathtoimmortals.worldgen.noise;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;

public class ModNoises {

    public static final ResourceKey<NormalNoise.NoiseParameters> WEIGHTED = ModNoiseRegistry.createKey("weighted");
    public static final ResourceKey<NormalNoise.NoiseParameters> SHIELD = ModNoiseRegistry.createKey("shield");

    public static void bootstrap(BootstapContext<NormalNoise.NoiseParameters> context) {
        register(context, WEIGHTED, 0, 1.0D);
        register(context, SHIELD, -5, 1.0D, 1.0D, 1.0D);
    }
    private static void register(BootstapContext<NormalNoise.NoiseParameters> context, ResourceKey<NormalNoise.NoiseParameters> key, int i, double v, double... doubles) {
        context.register(key, new NormalNoise.NoiseParameters(i, v, doubles));
    }

}
