package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.item.custom.DaoCannon;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PathToImmortals.MOD_ID);

    public static final RegistryObject<Item> DIVINE_CRYSTAL = ITEMS.register("divine_crystal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIVINE_COIN = ITEMS.register("divine_coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DAO_CANNON = ITEMS.register("dao_cannon",
            () -> new DaoCannon(new Item.Properties().durability(1000)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}