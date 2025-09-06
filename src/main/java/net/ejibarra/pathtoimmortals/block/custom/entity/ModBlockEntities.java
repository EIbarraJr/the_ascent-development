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

    public static final RegistryObject<BlockEntityType<InfusionTable1Entity>> INFUSION_TABLE_BLOCK1 =
            BLOCK_ENTITIES.register("infusion_table_block1", () ->
                    BlockEntityType.Builder.of(InfusionTable1Entity::new,
                            ModBlocks.INFUSTION_TABLE_T1.get()).build(null));



    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
