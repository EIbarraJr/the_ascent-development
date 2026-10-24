package net.ejibarra.pathtoimmortals.block.custom.entity;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public  static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PathToImmortals.MOD_ID);


    public static final RegistryObject<BlockEntityType<HumanGradeInfuserBlockEntity>> HUMAN_GRADE_INFUSER_BE =
            BLOCK_ENTITIES.register("human_grade_infuser", ()->
                    BlockEntityType.Builder.of(HumanGradeInfuserBlockEntity::new,
                            ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<HumanGradeDivineAltarBlockEntity>> HUMAN_GRADE_DIVINE_ALTAR_BE =
            BLOCK_ENTITIES.register("human_grade_divine_altar", ()->
                    BlockEntityType.Builder.of(HumanGradeDivineAltarBlockEntity::new,
                            ModBlocks.HUMAN_GRADE_DIVINE_ALTAR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<LightDivineRunicBlockEntity>> LIGHT_DIVINE_BRICK_RUNIC_BE =
            BLOCK_ENTITIES.register("light_divine_brick_runic", ()->
                    BlockEntityType.Builder.of(LightDivineRunicBlockEntity::new,
                            ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get()).build(null));

    public static final RegistryObject<BlockEntityType<DarkDivineBrickRunicBlockEntity>> DARK_DIVINE_BRICK_RUNIC_BE =
            BLOCK_ENTITIES.register("dark_divine_brick_runic", ()->
                    BlockEntityType.Builder.of(DarkDivineBrickRunicBlockEntity::new,
                            ModBlocks.DARK_DIVINE_BRICK_RUNIC.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
