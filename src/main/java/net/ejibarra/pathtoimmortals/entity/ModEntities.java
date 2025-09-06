package net.ejibarra.pathtoimmortals.entity;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities  {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PathToImmortals.MOD_ID);

    public static final RegistryObject<EntityType<DaoCoreProjectile>> DAO_CORE_PROJECTILE =
            ENTITY_TYPES.register("dao_core_projectile",
                    () -> EntityType.Builder.<DaoCoreProjectile>of(DaoCoreProjectile::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("dao_core_projectile"));
    public static final RegistryObject<EntityType<SpikeProjectileEntity>> SPIKE_PROJECTILE =
            ENTITY_TYPES.register("spike_projectile",
                    () -> EntityType.Builder.<SpikeProjectileEntity>of(SpikeProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build("spike_projectile"));
    public static final RegistryObject<EntityType<CheeseProjectileEntity>> CHEESE_PROJECTILE =
            ENTITY_TYPES.register("cheese_projectile",
                    () -> EntityType.Builder.<CheeseProjectileEntity>of(CheeseProjectileEntity::new, MobCategory.MISC)
                            .sized(1.0f, 1.0f)
                            .clientTrackingRange(5)
                            .updateInterval(20)
                            .build("cheese_projectile"));

    public static final RegistryObject<EntityType<HereticEntity>> HERETIC =
            ENTITY_TYPES.register("heretic", ()-> EntityType.Builder.of(HereticEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 2f).build("heretic"));
    public static final RegistryObject<EntityType<SpikedRatEntity>> SPIKEDRAT =
            ENTITY_TYPES.register("spikedrat", ()-> EntityType.Builder.of(SpikedRatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f,1.6f).build("spikedrat"));
    public static final RegistryObject<EntityType<DarklinEntity>> DARKLIN =
            ENTITY_TYPES.register("darklin", ()-> EntityType.Builder.of(DarklinEntity::new, MobCategory.MONSTER)
                    .sized(0.5f,2f).build("darklin"));
    public static final RegistryObject<EntityType<HoodedSpikedRatEntity>> HOODEDRAT =
            ENTITY_TYPES.register("hooded_spiked_rat", ()-> EntityType.Builder.of(HoodedSpikedRatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f,1.6f).build("hooded_spiked_rat"));
    public static final RegistryObject<EntityType<RatLord>> RAT_LORD =
            ENTITY_TYPES.register("rat_lord", ()-> EntityType.Builder.of(RatLord::new, MobCategory.MONSTER)
                    .sized(0.5f,1.6f).build("rat_lord"));
    public static final RegistryObject<EntityType<OgreEntity>> OGRE =
            ENTITY_TYPES.register("ogre", ()-> EntityType.Builder.of(OgreEntity::new, MobCategory.MONSTER)
                    .sized(1.2f,3.5f).build("ogre"));
    public static final RegistryObject<EntityType<ScorpionEntity>> SCORPION =
            ENTITY_TYPES.register("scorpion", ()-> EntityType.Builder.of(ScorpionEntity::new, MobCategory.MONSTER)
                    .sized(1.2f,3.5f).build("scorpion"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
