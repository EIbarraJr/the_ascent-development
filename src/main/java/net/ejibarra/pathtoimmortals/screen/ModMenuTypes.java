package net.ejibarra.pathtoimmortals.screen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, PathToImmortals.MOD_ID);

    public static final RegistryObject<MenuType<InfusionTable1Menu>> INFUSIONTABLE_MENU_1 =
            registerMenuType(InfusionTable1Menu::new, "infusion_table_menu1");

    public static final RegistryObject<MenuType<HumanGradeInfuserMenu>> HUMAN_GRADE_INFUSER_MENU =
            registerMenuType(HumanGradeInfuserMenu::new, "human_grade_infuser_menu");

    public static final RegistryObject<MenuType<HumanGradeDivineAltarMenu>> HUMAN_GRADE_DIVINE_ALTAR_MENU =
            registerMenuType(HumanGradeDivineAltarMenu::new, "human_grade_divine_altar_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name){
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
