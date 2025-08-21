package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PathToImmortals.MOD_ID);

    public static final RegistryObject<CreativeModeTab> COURSE_TAB = CREATIVE_MODE_TABS.register("course_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIVINE_CRYSTAL.get()))
                    .title(Component.translatable("creativetab.course_tab"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.DIVINE_CRYSTAL.get());
                        output.accept(ModItems.DIVINE_COIN.get());

                        output.accept(ModItems.DAO_CANNON.get());

                        output.accept(ModBlocks.DIVINE_BLOCK_BLANK.get());
                        output.accept(ModBlocks.GATHERING_FORMATION_T1.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}