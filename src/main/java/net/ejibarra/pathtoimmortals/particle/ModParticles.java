package net.ejibarra.pathtoimmortals.particle;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PathToImmortals.MOD_ID);

    public static final RegistryObject<SimpleParticleType> FIERO_LEAVES_PARTICLE =
            PARTICLE_TYPES.register("fiero_particles", ()-> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> JADED_LEAVES_PARTICLE =
            PARTICLE_TYPES.register("jaded_particles", ()-> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> JASMINE_LEAVES_PARTICLE =
            PARTICLE_TYPES.register("jasmine_particles", ()-> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> KINDER_LEAVES_PARTICLE =
            PARTICLE_TYPES.register("kinder_particles", ()-> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MANGO_LEAVES_PARTICLE =
            PARTICLE_TYPES.register("mango_particles", ()-> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SPIRIT_LEAVES_PARTICLE =
            PARTICLE_TYPES.register("spirit_particles", ()-> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
