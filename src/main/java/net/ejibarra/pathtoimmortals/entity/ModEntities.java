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
    public static final RegistryObject<EntityType<OriginSwordProjectileEntity>> ORIGIN_SWORD_PROJECTILE =
            ENTITY_TYPES.register("origin_sword_projectile",
                    () -> EntityType.Builder.<OriginSwordProjectileEntity>of(OriginSwordProjectileEntity::new, MobCategory.MISC)
                            .sized(0.40f, 0.40f)
                            .clientTrackingRange(64)
                            .updateInterval(10)
                            .build("origin_sword_projectile"));
    public static final RegistryObject<EntityType<PressureWaveProjectile>> PRESSURE_WAVE =
            ENTITY_TYPES.register("pressure_wave",
                    () -> EntityType.Builder.<PressureWaveProjectile>of(PressureWaveProjectile::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("pressure_wave"));
    /**/


    public static final RegistryObject<EntityType<HereticEntity>> HERETIC =
            ENTITY_TYPES.register("heretic", ()-> EntityType.Builder.of(HereticEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 2f).build("heretic"));
    public static final RegistryObject<EntityType<AssassainEntity>> ASSASSAIN =
            ENTITY_TYPES.register("assassain", ()-> EntityType.Builder.of(AssassainEntity::new, MobCategory.MONSTER)
                    .sized(0.8f,2.7f).build("assassain"));
    public static final RegistryObject<EntityType<SpikedRatEntity>> SPIKEDRAT =
            ENTITY_TYPES.register("spikedrat", ()-> EntityType.Builder.of(SpikedRatEntity::new, MobCategory.MONSTER)
                    .sized(0.5f,1.6f).build("spikedrat"));
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
    public static final RegistryObject<EntityType<FungiSwordsmanEntity>> FUNGI_SWORDSMAN =
            ENTITY_TYPES.register("fungi_swordsman", ()-> EntityType.Builder.of(FungiSwordsmanEntity::new, MobCategory.MONSTER)
                    .sized(0.4f,1.0f).build("fungi_swordsman"));
    public static final RegistryObject<EntityType<SporeSpreaderEntity>> SPORE_SPREADER =
            ENTITY_TYPES.register("spore_spreader", ()-> EntityType.Builder.of(SporeSpreaderEntity::new, MobCategory.MONSTER)
                    .sized(0.6f,2.0f).build("spore_spreader"));
    public static final RegistryObject<EntityType<BeefsteakMushroomEntity>> BEEFSTEAK_MUSHROOM =
            ENTITY_TYPES.register("beefsteak_mushroom", ()-> EntityType.Builder.of(BeefsteakMushroomEntity::new, MobCategory.MONSTER)
                    .sized(0.8f,2.7f).build("beefsteak_mushroom"));
    public static final RegistryObject<EntityType<OrcWarrior>> ORC_WARRIOR =
            ENTITY_TYPES.register("orc_warrior", ()-> EntityType.Builder.of(OrcWarrior::new, MobCategory.MONSTER)
                    .sized(0.8f,3.7f).build("orc_warrior"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
