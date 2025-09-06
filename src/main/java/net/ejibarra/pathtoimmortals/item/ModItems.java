package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.item.custom.DaoCannon;
import net.ejibarra.pathtoimmortals.item.custom.PaxelItem;
import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import net.ejibarra.pathtoimmortals.item.custom.StatSwordItems;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

    public static final RegistryObject<Item> JADE = ITEMS.register("jade",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JADE_COIN = ITEMS.register("jade_coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PURPLE_CRYSTAL = ITEMS.register("purple_crystal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_COIN = ITEMS.register("purple_coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPIRIT_CRYSTAL = ITEMS.register("spirit_crystal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_COIN = ITEMS.register("spirit_coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAINT_CRYSTAL = ITEMS.register("saint_crystal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAINT_COIN = ITEMS.register("saint_coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPIRIT_IRON_TIER1 = ITEMS.register("spirit_iron_tier1",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_IRON_TIER2 = ITEMS.register("spirit_iron_tier2",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_IRON_TIER3 = ITEMS.register("spirit_iron_tier3",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_IRON_TIER4 = ITEMS.register("spirit_iron_tier4",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_IRON_TIER5 = ITEMS.register("spirit_iron_tier5",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HERETIC_SPAWN_EGG = ITEMS.register("heretic_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HERETIC, 0xF5E6C8, 0x8B4513, new Item.Properties()));
    public static final RegistryObject<Item> SPIKEDRAT_SPAWN_EGG = ITEMS.register("spikedrat_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SPIKEDRAT, 0xD3D3D3, 0x5C4033, new Item.Properties()));
    public static final RegistryObject<Item> DARKLIN_SPAWN_EGG = ITEMS.register("darklin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DARKLIN, 0xD3D3D5, 0x5C4043, new Item.Properties()));
    public static final RegistryObject<Item> HOODED_RAT_SPAWN_EGG = ITEMS.register("hooded_rat_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HOODEDRAT, 0xD3D3D5, 0x00AA00, new Item.Properties()));

    public static final RegistryObject<Item> BEAST_BLOOD = ITEMS.register("beast_blood",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOUL = ITEMS.register("souls",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEAST_OIL = ITEMS.register("beast_oil",
            () -> new FuelItem(new Item.Properties(), 20000));


    public static final RegistryObject<Item> DAO_CANNON = ITEMS.register("dao_cannon",
            () -> new DaoCannon(new Item.Properties().durability(1000)));

    //HUMAN GRADE GEAR
    //Tier 1 LOWER
    public static final RegistryObject<Item> HUMAN_GRADE_HELMET = ITEMS.register("human_grade_helmet",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.HELMET, new Item.Properties(),
                    3.0, 1.0, 0.0, 0, 0.0));
    public static final RegistryObject<Item> HUMAN_GRADE_CHESTPLATE = ITEMS.register("human_grade_chestplate",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    3.0, 2.0, 0.0, 0, 0.0));
    public static final RegistryObject<Item> HUMAN_GRADE_LEGGINGS = ITEMS.register("human_grade_leggings",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    3.0, 1.0, 0.0, 0, 0.0));
    public static final RegistryObject<Item> HUMAN_GRADE_BOOTS = ITEMS.register("human_grade_boots",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.BOOTS, new Item.Properties(),
                    3.0, 1.0, 0.06, 0, 0.0));
    public static final RegistryObject<Item> LOWER_HUMAN_GRADE_SABER = ITEMS.register("lower_human_saber",
            () -> new StatSwordItems(ModToolTiers.Jaded_Iron, 32, -2.0f, new Item.Properties(), 5, 0.07, 0.1, 0.00, 0.02));
       //Tier 2 MEDIUM
       public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_HELMET = ITEMS.register("medium_human_grade_helmet",
               () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.HELMET, new Item.Properties(),
                       3.0, 1.0, 0.0, 0, 0.0));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_CHESTPLATE = ITEMS.register("medium_human_grade_chestplate",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    3.0, 2.0, 0.0, 0, 0.0));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_LEGGINGS = ITEMS.register("medium_human_grade_leggings",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    3.0, 1.0, 0.0, 0, 0.0));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_BOOTS = ITEMS.register("medium_human_grade_boots",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.BOOTS, new Item.Properties(),
                    3.0, 1.0, 0.06, 0, 0.0));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_SABER = ITEMS.register("medium_human_saber",
            () -> new StatSwordItems(ModToolTiers.Jaded_Iron, 32, -2.0f, new Item.Properties(), 5, 0.07, 0.1, 0.00, 0.02));
           //Tier 3 UPPER
    public static final RegistryObject<Item> HUMAN_GRADE_PAXEL = ITEMS.register("human_grade_paxel",
            () -> new PaxelItem(ModToolTiers.Jaded_Iron, 5, 0.02f, new Item.Properties().durability(4096)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}