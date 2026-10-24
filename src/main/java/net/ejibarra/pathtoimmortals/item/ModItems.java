package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.item.client.DivineEarthArmorRenderer;
import net.ejibarra.pathtoimmortals.item.client.HumanGradeArmorRenderer;
import net.ejibarra.pathtoimmortals.item.client.MediumHumanGradeArmorRenderer;
import net.ejibarra.pathtoimmortals.item.client.UpperHumanGradeArmorRenderer;
import net.ejibarra.pathtoimmortals.item.custom.*;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
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

    public static final RegistryObject<Item> CRUDE_JADED_IRON = ITEMS.register("crude_jaded_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REFINED_JADE_IRON = ITEMS.register("refined_jade_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REINFORCED_JADE_IRON = ITEMS.register("reinforced_jade_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_IRON_TIER4 = ITEMS.register("spirit_iron_tier4",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRIT_IRON_TIER5 = ITEMS.register("spirit_iron_tier5",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIVINE_IRON = ITEMS.register("divine_iron",
            () -> new Item(new Item.Properties()));

    //Scrolls
    // ORIGIN SWORD scrolls
    public static final RegistryObject<Item> ORIGIN_SWORD_SCROLL_T1 =
            ITEMS.register("origin_sword_scroll_t1",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.ORIGIN_SWORD, 1));
    public static final RegistryObject<Item> ORIGIN_SWORD_SCROLL_T2 =
            ITEMS.register("origin_sword_scroll_t2",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.ORIGIN_SWORD, 2));
    public static final RegistryObject<Item> ORIGIN_SWORD_SCROLL_T3 =
            ITEMS.register("origin_sword_scroll_t3",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.ORIGIN_SWORD, 3));
    public static final RegistryObject<Item> ORIGIN_SWORD_SCROLL_T4 =
            ITEMS.register("origin_sword_scroll_t4",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.ORIGIN_SWORD, 4));

    // PRESSURE WAVE scrolls
    public static final RegistryObject<Item> PRESSURE_WAVE_SCROLL_T1 =
            ITEMS.register("pressure_wave_scroll_t1",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.PRESSURE_WAVE, 1));
    public static final RegistryObject<Item> PRESSURE_WAVE_SCROLL_T2 =
            ITEMS.register("pressure_wave_scroll_t2",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.PRESSURE_WAVE, 2));
    public static final RegistryObject<Item> PRESSURE_WAVE_SCROLL_T3 =
            ITEMS.register("pressure_wave_scroll_t3",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.PRESSURE_WAVE, 3));
    public static final RegistryObject<Item> PRESSURE_WAVE_SCROLL_T4 =
            ITEMS.register("pressure_wave_scroll_t4",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.PRESSURE_WAVE, 4));

    // OVERCHARGED cooldown-only tiers
    public static final RegistryObject<Item> OVERCHARGED_SCROLL_T1 =
            ITEMS.register("overcharged_scroll_t1",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.OVERCHARGED, 1));
    public static final RegistryObject<Item> OVERCHARGED_SCROLL_T2 =
            ITEMS.register("overcharged_scroll_t2",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.OVERCHARGED, 2));
    public static final RegistryObject<Item> OVERCHARGED_SCROLL_T3 =
            ITEMS.register("overcharged_scroll_t3",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.OVERCHARGED, 3));
    public static final RegistryObject<Item> OVERCHARGED_SCROLL_T4 =
            ITEMS.register("overcharged_scroll_t4",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.OVERCHARGED, 4));

    // HEAL cooldown-only tiers
    public static final RegistryObject<Item> HEAL_SCROLL_T1 =
            ITEMS.register("heal_scroll_t1",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.HEAL, 1));
    public static final RegistryObject<Item> HEAL_SCROLL_T2 =
            ITEMS.register("heal_scroll_t2",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.HEAL, 2));
    public static final RegistryObject<Item> HEAL_SCROLL_T3 =
            ITEMS.register("heal_scroll_t3",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.HEAL, 3));
    public static final RegistryObject<Item> HEAL_SCROLL_T4 =
            ITEMS.register("heal_scroll_t4",
                    () -> new AbilityScrollItem(scrollProps(), AbilityType.HEAL, 4));

    public static final RegistryObject<Item> REINFORCED_SCROLL_T1 = ITEMS.register("reinforced_scroll_t1",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.REINFORCED, 1));
    public static final RegistryObject<Item> REINFORCED_SCROLL_T2 = ITEMS.register("reinforced_scroll_t2",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.REINFORCED, 2));
    public static final RegistryObject<Item> REINFORCED_SCROLL_T3 = ITEMS.register("reinforced_scroll_t3",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.REINFORCED, 3));
    public static final RegistryObject<Item> REINFORCED_SCROLL_T4 = ITEMS.register("reinforced_scroll_t4",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.REINFORCED, 4));

    public static final RegistryObject<Item> CLOUD_STEP_SCROLL_T1 = ITEMS.register("cloud_step_scroll_t1",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.CLOUD_STEP, 1));
    public static final RegistryObject<Item> CLOUD_STEP_SCROLL_T2 = ITEMS.register("cloud_step_scroll_t2",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.CLOUD_STEP, 2));
    public static final RegistryObject<Item> CLOUD_STEP_SCROLL_T3 = ITEMS.register("cloud_step_scroll_t3",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.CLOUD_STEP, 3));
    public static final RegistryObject<Item> CLOUD_STEP_SCROLL_T4 = ITEMS.register("cloud_step_scroll_t4",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.CLOUD_STEP, 4));

    public static final RegistryObject<Item> LEVITATION_SCROLL = ITEMS.register("levitation_scroll",
            () -> new AbilityScrollItem(scrollProps(), AbilityType.LEVITATION, 1));



    //Moss
    public static final RegistryObject<Item> SPIRIT_MOSS_ITEM =
            ITEMS.register("spirit_moss", () -> new BlockItem(ModBlocks.SPIRIT_MOSS.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SPIRIT_MOSS_CARPET_ITEM =
            ITEMS.register("spirit_moss_carpet", () -> new BlockItem(ModBlocks.SPIRIT_MOSS_CARPET.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> LEMON_MOSS_ITEM =
            ITEMS.register("lemon_moss", () -> new BlockItem(ModBlocks.LEMON_MOSS.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LEMON_MOSS_CARPET_ITEM =
            ITEMS.register("lemon_moss_carpet", () -> new BlockItem(ModBlocks.LEMON_MOSS_CARPET.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> ROSY_MOSS_ITEM =
            ITEMS.register("rosy_moss", () -> new BlockItem(ModBlocks.ROSY_MOSS.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ROSY_MOSS_CARPET_ITEM =
            ITEMS.register("rosy_moss_carpet", () -> new BlockItem(ModBlocks.ROSY_MOSS_CARPET.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> OCTOS_GROWTH_ITEM =
            ITEMS.register("octos_growth", () -> new BlockItem(ModBlocks.OCTOS_GROWTH.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> OCTOS_GROWTH_CARPET_ITEM =
            ITEMS.register("octos_growth_carpet", () -> new BlockItem(ModBlocks.OCTOS_GROWTH_CARPET.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> FEYS_SORROW_ITEM =
            ITEMS.register("feys_sorrow", () -> new BlockItem(ModBlocks.FEYS_SORROW.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> FEYS_SORROW_CARPET_ITEM =
            ITEMS.register("feys_sorrow_carpet", () -> new BlockItem(ModBlocks.FEYS_SORROW_CARPET.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> EVES_BLUES_ITEM =
            ITEMS.register("eves_blues", () -> new BlockItem(ModBlocks.EVES_BLUES.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EVES_BLUES_CARPET_ITEM =
            ITEMS.register("eves_blues_carpet", () -> new BlockItem(ModBlocks.EVES_BLUES_CARPET.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> LIGHT_RUNIC_ITEM =
            ITEMS.register("light_divine_brick_runic", () -> new BlockItem(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> DARK_RUNIC_ITEM =
            ITEMS.register("dark_divine_brick_runic", () -> new BlockItem(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get(), new Item.Properties().stacksTo(64)));

    //TRESS
    public static final RegistryObject<Item> FIERO_LEAVES_ITEM = ITEMS.register("fiero_leaves", ()-> new BlockItem(ModBlocks.FIERO_LEAVES.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> JADED_LEAVES_ITEM = ITEMS.register("jaded_leaves", ()-> new BlockItem(ModBlocks.JADED_LEAVES.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> JASMINE_LEAVES_ITEM = ITEMS.register("jasmine_leaves", ()-> new BlockItem(ModBlocks.JASMINE_LEAVES.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> KINDER_LEAVES_ITEM = ITEMS.register("kinder_leaves", ()-> new BlockItem(ModBlocks.KINDER_LEAVES.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MANGO_LEAVES_ITEM = ITEMS.register("mango_leaves", ()-> new BlockItem(ModBlocks.MANGO_LEAVES.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SPIRIT_LEAVES_ITEM = ITEMS.register("spirit_blossom_leaves", ()-> new BlockItem(ModBlocks.SPIRIT_BLOSSOM_LEAVES.get(), new Item.Properties().stacksTo(64)));

    //Crystals
    // Turquoise items
    public static final RegistryObject<Item> TURQUOISE_BLOCK_ITEM =
            ITEMS.register("turquoise_block", () -> new BlockItem(ModBlocks.TURQUOISE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SMALL_TURQUOISE_BUD_ITEM =
            ITEMS.register("small_turquoise_bud", () -> new BlockItem(ModBlocks.SMALL_TURQUOISE_BUD.get(), new Item.Properties()));
    public static final RegistryObject<Item> MEDIUM_TURQUOISE_BUD_ITEM =
            ITEMS.register("medium_turquoise_bud", () -> new BlockItem(ModBlocks.MEDIUM_TURQUOISE_BUD.get(), new Item.Properties()));
    public static final RegistryObject<Item> LARGE_TURQUOISE_BUD_ITEM =
            ITEMS.register("large_turquoise_bud", () -> new BlockItem(ModBlocks.LARGE_TURQUOISE_BUD.get(), new Item.Properties()));
    public static final RegistryObject<Item> TURQUOISE_CLUSTER_ITEM =
            ITEMS.register("turquoise_cluster", () -> new BlockItem(ModBlocks.TURQUOISE_CLUSTER.get(), new Item.Properties()));
    // Fey items
    public static final RegistryObject<Item> FEY_BLOCK_ITEM =
            ITEMS.register("feys_block", () -> new BlockItem(ModBlocks.FEY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SMALL_FEY_BUD_ITEM =
            ITEMS.register("small_feys_bud", () -> new BlockItem(ModBlocks.SMALL_FEY_BUD.get(), new Item.Properties()));
    public static final RegistryObject<Item> MEDIUM_FEY_BUD_ITEM =
            ITEMS.register("medium_feys_bud", () -> new BlockItem(ModBlocks.MEDIUM_FEY_BUD.get(), new Item.Properties()));
    public static final RegistryObject<Item> LARGE_FEY_BUD_ITEM =
            ITEMS.register("large_feys_bud", () -> new BlockItem(ModBlocks.LARGE_FEY_BUD.get(), new Item.Properties()));
    public static final RegistryObject<Item> FEY_CLUSTER_ITEM =
            ITEMS.register("feys_cluster", () -> new BlockItem(ModBlocks.FEY_CLUSTER.get(), new Item.Properties()));

    //Spawn Eggs
    public static final RegistryObject<Item> HERETIC_SPAWN_EGG = ITEMS.register("heretic_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HERETIC, 0xF5E6C8, 0x8B4513, new Item.Properties()));
    public static final RegistryObject<Item> SPIKEDRAT_SPAWN_EGG = ITEMS.register("spikedrat_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SPIKEDRAT, 0xBFBFBF, 0x7A4E28, new Item.Properties()));
    public static final RegistryObject<Item> HOODED_RAT_SPAWN_EGG = ITEMS.register("hooded_rat_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HOODEDRAT, 0xBFBFBF, 0x2E8B57, new Item.Properties()));
    public static final RegistryObject<Item> RAT_LORD_SPAWN_EGG = ITEMS.register("rat_lord_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RAT_LORD, 0xBFBFBF, 0x00AAAA, new Item.Properties()));
    public static final RegistryObject<Item> OGRE_SPAWN_EGG = ITEMS.register("ogre_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.OGRE, 0x98FB98, 0x006400, new Item.Properties()));
    public static final RegistryObject<Item> ORC_WARRIOR_SPAWN_EGG = ITEMS.register("orc_warrior_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ORC_WARRIOR, 0x98FB98, 0x006400, new Item.Properties()));//Change egg color future
    public static final RegistryObject<Item> SCORPION_SPAWN_EGG = ITEMS.register("scorpion_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SCORPION, 0x8B4513, 0xFFD300, new Item.Properties()));

    public static final RegistryObject<Item> FUNGI_SWORDSMAN_SPAWN_EGG = ITEMS.register("fungi_swordsman_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FUNGI_SWORDSMAN, 0xEDEBD7, 0xC724FF, new Item.Properties()));
    public static final RegistryObject<Item> SPORE_SPREADER_SPAWN_EGG = ITEMS.register("spore_spreader_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SPORE_SPREADER, 0xEDEBD7, 0x3B0A45, new Item.Properties()));
    public static final RegistryObject<Item> BEEFSTEAK_MUSHROOM_SPAWN_EGG = ITEMS.register("beefsteak_mushroom_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BEEFSTEAK_MUSHROOM, 0xEDEBD7, 0x7C0A02, new Item.Properties()));

    public static final RegistryObject<Item> BEAST_BLOOD = ITEMS.register("beast_blood",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHARD_OF_DIVINITY = ITEMS.register("shard_of_divinity",
            () -> new Item(new Item.Properties()));

    //CROPS
    public static final RegistryObject<Item> HUMAN_REALM_SEEDS = ITEMS.register("human_realm_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HUMAN_REALM_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> ORIGIN_BERRIES = ITEMS.register("origin_berries",
            () -> new Item(new Item.Properties()));

    //Souls
    public static final RegistryObject<Item> HUMAN_GRADE_SOUL_LOWER = ITEMS.register("human_grade_soul_lower",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HUMAN_GRADE_SOUL_MEDIUM = ITEMS.register("human_grade_soul_medium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HUMAN_GRADE_SOUL_UPPER = ITEMS.register("human_grade_soul_upper",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INFUSED_JADE = ITEMS.register("infused_jade",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEAST_OIL = ITEMS.register("beast_oil",
            () -> new FuelItem(new Item.Properties(), 20000));


    public static final RegistryObject<Item> DAO_CANNON = ITEMS.register("dao_cannon",
            () -> new DaoCannon(new Item.Properties().durability(1000)));

    //HUMAN GRADE GEAR
    //Tier 1 LOWER
    public static final RegistryObject<Item> HUMAN_GRADE_HELMET = ITEMS.register("human_grade_helmet",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.HELMET, new Item.Properties(),
                    3.0, 1.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, HumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> HUMAN_GRADE_CHESTPLATE = ITEMS.register("human_grade_chestplate",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    3.0, 2.0, 0.0, 0, 0.0, 0.0, 0.0,  0.0, 0.0,HumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> HUMAN_GRADE_LEGGINGS = ITEMS.register("human_grade_leggings",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    3.0, 1.0, 0.0, 0, 0.0, 0.0, 0.0,  0.0, 0.0,HumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> HUMAN_GRADE_BOOTS = ITEMS.register("human_grade_boots",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.BOOTS, new Item.Properties(),
                    3.0, 1.0, 0.06, 0, 0.0, 0.0, 0.0,  0.0, 0.0,HumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> LOWER_HUMAN_GRADE_SHIELD = ITEMS.register("lower_human_grade_shield",
            () -> new StatShieldItems(new Item.Properties().durability(3300)
                    , 0.0, 0.1, 0.0, 0.05, 0.05, 0.0, 0.0));
    public static final RegistryObject<Item> LOWER_HUMAN_GRADE_SABER = ITEMS.register("lower_human_saber",
            () -> new StatSwordItems(ModToolTiers.Jaded_Iron, 30, -2.4f, new Item.Properties(), 5, 0.07, 0.0, 0.00, 0.02, 0.1, 0.5));
       //Tier 2 MEDIUM
       public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_HELMET = ITEMS.register("medium_human_grade_helmet",
               () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.HELMET, new Item.Properties(),
                       4.0, 2.0, 0.0, 1, 0.0, 0.0, 0.0, 0.0, 0.0, MediumHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_CHESTPLATE = ITEMS.register("medium_human_grade_chestplate",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    4.0, 3.0, 0.0, 2, 0.0, 0.0, 0.0,  0.0, 0.0, MediumHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_LEGGINGS = ITEMS.register("medium_human_grade_leggings",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    4.0, 3.0, 0.0, 1, 0.0, 0.0, 0.0,  0.0, 0.0, MediumHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_BOOTS = ITEMS.register("medium_human_grade_boots",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.BOOTS, new Item.Properties(),
                    4.0, 2.0, 0.07, 1, 0.0, 0.0, 0.0,  0.0, 0.0, MediumHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_SHIELD = ITEMS.register("medium_human_grade_shield",
            () -> new StatShieldItems(new Item.Properties().durability(3300)
                    , 0.0, 0.12, 2.0, 0.1, 0.1, 0.0, 0.0));
    public static final RegistryObject<Item> MEDIUM_HUMAN_GRADE_SABER = ITEMS.register("medium_human_saber",
            () -> new StatSwordItems(ModToolTiers.Jaded_Iron, 40, -2.0f, new Item.Properties(), 10, 0.1, 0.0, 0.00, 0.02, 0.1, 0.5));
           //Tier 3 UPPER
    public static final RegistryObject<Item> UPPER_HUMAN_GRADE_HELMET = ITEMS.register("upper_human_grade_helmet",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.HELMET, new Item.Properties(),
                    5.0, 4.0, 0.0, 2, 0.0, 0.0, 0.0,  0.0, 0.0,UpperHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> UPPER_HUMAN_GRADE_CHESTPLATE = ITEMS.register("upper_human_grade_chestplate",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    5.0, 4.0, 0.0, 3, 0.0, 0.0, 0.0,  0.0, 0.0, UpperHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> UPPER_HUMAN_GRADE_LEGGINGS = ITEMS.register("upper_human_grade_leggings",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    5.0, 4.0, 0.0, 3, 0.0, 0.0, 0.0,  0.0, 0.0, UpperHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> UPPER_HUMAN_GRADE_BOOTS = ITEMS.register("upper_human_grade_boots",
            () -> new StatArmorItems(ModArmorMaterials.SPIRIT_IRON_TIER1, ArmorItem.Type.BOOTS, new Item.Properties(),
                    5.0, 3.0, 0.08, 2, 0.0, 0.0, 0.0,  0.0, 0.0, UpperHumanGradeArmorRenderer::new));
    public static final RegistryObject<Item> UPPER_HUMAN_GRADE_SHIELD = ITEMS.register("upper_human_grade_shield",
            () -> new StatShieldItems(new Item.Properties().durability(3300)
                    , 4.0, 0.15, 4.0, 0.12, 0.12, 4.0, 0.0));
    public static final RegistryObject<Item> UPPER_HUMAN_GRADE_SABER = ITEMS.register("upper_human_saber",
            () -> new StatSwordItems(ModToolTiers.Jaded_Iron, 50, -2.0f, new Item.Properties(), 15, 0.12, 0.0, 0.0, 0.03, 0.1, 0.5));
    //DIVINE
    public static final RegistryObject<Item> DIVINE_HUMAN_GRADE_HELMET = ITEMS.register("divine_human_grade_helmet",
            () -> new StatArmorItems(ModArmorMaterials.DIVINE_EARTH_METAL, ArmorItem.Type.HELMET, new Item.Properties(),
                    6.0, 5.0, 0.0, 2, 0.05, 0.0, 0.0,  0.1, 0.0, DivineEarthArmorRenderer::new));
    public static final RegistryObject<Item> DIVINE_HUMAN_GRADE_CHESTPLATE = ITEMS.register("divine_human_grade_chestplate",
            () -> new StatArmorItems(ModArmorMaterials.DIVINE_EARTH_METAL, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    6.0, 5.0, 0.0, 3, 0.05, 0.0, 0.1,  0.0, 0.0, DivineEarthArmorRenderer::new));
    public static final RegistryObject<Item> DIVINE_HUMAN_GRADE_LEGGINGS = ITEMS.register("divine_human_grade_leggings",
            () -> new StatArmorItems(ModArmorMaterials.DIVINE_EARTH_METAL, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    6.0, 5.0, 0.0, 3, 0.05, 0.15, 0.0,  0.0, 0.0, DivineEarthArmorRenderer::new));
    public static final RegistryObject<Item> DIVINE_HUMAN_GRADE_BOOTS = ITEMS.register("divine_human_grade_boots",
            () -> new StatArmorItems(ModArmorMaterials.DIVINE_EARTH_METAL, ArmorItem.Type.BOOTS, new Item.Properties(),
                    6.0, 5.0, 0.11, 2, 0.05, 0.0, 0.0,  0.0, 0.1, DivineEarthArmorRenderer::new));
    public static final RegistryObject<Item> DIVINE_HUMAN_GRADE_SHIELD = ITEMS.register("divine_human_grade_shield",
            () -> new StatShieldItems(new Item.Properties().durability(4300)
                    , 6.0, 0.2, 7.0, 0.15, 0.15, 10.0, 0.1));
    public static final RegistryObject<Item> DIVINE_HUMAN_GRADE_SABER = ITEMS.register("divine_human_saber",
            () -> new StatSwordItems(ModToolTiers.Divine_Earth_Metal, 69, -2.0f, new Item.Properties(), 11, 0.15, 0.0, 0.00, 0.05, 0.1, 0.5));

    public static final RegistryObject<Item> HUMAN_GRADE_PAXEL = ITEMS.register("human_grade_paxel",
            () -> new PaxelItem(ModToolTiers.Jaded_Iron, 5, -2.8f, new Item.Properties().durability(4096)));

    private static Item.Properties scrollProps(){
        return new Item.Properties().stacksTo(16);
    }


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}