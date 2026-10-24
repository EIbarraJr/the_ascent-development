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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SHARD_OF_DIVINITY.get()))
                    .title(Component.translatable("creativetab.course_tab"))
                    .displayItems((displayParameters, output) -> {

                        //ITEMS
                        output.accept(ModItems.JADE.get());
                        output.accept(ModItems.SPIRIT_CRYSTAL.get());
                        output.accept(ModItems.PURPLE_CRYSTAL.get());
                        output.accept(ModItems.SAINT_CRYSTAL.get());
                        output.accept(ModItems.DIVINE_CRYSTAL.get());
                        output.accept(ModItems.INFUSED_JADE.get());
                        output.accept(ModItems.JADE_COIN.get());
                        output.accept(ModItems.SPIRIT_COIN.get());
                        output.accept(ModItems.PURPLE_COIN.get());
                        output.accept(ModItems.SAINT_COIN.get());
                        output.accept(ModItems.DIVINE_COIN.get());
                        output.accept(ModItems.CRUDE_JADED_IRON.get());
                        output.accept(ModItems.REFINED_JADE_IRON.get());
                        output.accept(ModItems.REINFORCED_JADE_IRON.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER4.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER5.get());
                        output.accept(ModItems.DIVINE_IRON.get());
                        output.accept(ModItems.HUMAN_GRADE_SOUL_LOWER.get());
                        output.accept(ModItems.HUMAN_GRADE_SOUL_MEDIUM.get());
                        output.accept(ModItems.HUMAN_GRADE_SOUL_UPPER.get());
                        output.accept(ModItems.BEAST_BLOOD.get());
                        output.accept(ModItems.BEAST_OIL.get());
                        output.accept(ModItems.ORIGIN_BERRIES.get());
                        output.accept(ModItems.HUMAN_REALM_SEEDS.get());
                        output.accept(ModItems.SHARD_OF_DIVINITY.get());

                        //Scrolls
                        output.accept(ModItems.LEVITATION_SCROLL.get());
                        output.accept(ModItems.CLOUD_STEP_SCROLL_T1.get());
                        output.accept(ModItems.CLOUD_STEP_SCROLL_T2.get());
                        output.accept(ModItems.CLOUD_STEP_SCROLL_T3.get());
                        output.accept(ModItems.CLOUD_STEP_SCROLL_T4.get());
                        output.accept(ModItems.OVERCHARGED_SCROLL_T1.get());
                        output.accept(ModItems.OVERCHARGED_SCROLL_T2.get());
                        output.accept(ModItems.OVERCHARGED_SCROLL_T3.get());
                        output.accept(ModItems.OVERCHARGED_SCROLL_T4.get());
                        output.accept(ModItems.HEAL_SCROLL_T1.get());
                        output.accept(ModItems.HEAL_SCROLL_T2.get());
                        output.accept(ModItems.HEAL_SCROLL_T3.get());
                        output.accept(ModItems.HEAL_SCROLL_T4.get());
                        output.accept(ModItems.ORIGIN_SWORD_SCROLL_T1.get());
                        output.accept(ModItems.ORIGIN_SWORD_SCROLL_T2.get());
                        output.accept(ModItems.ORIGIN_SWORD_SCROLL_T3.get());
                        output.accept(ModItems.ORIGIN_SWORD_SCROLL_T4.get());
                        output.accept(ModItems.PRESSURE_WAVE_SCROLL_T1.get());
                        output.accept(ModItems.PRESSURE_WAVE_SCROLL_T2.get());
                        output.accept(ModItems.PRESSURE_WAVE_SCROLL_T3.get());
                        output.accept(ModItems.PRESSURE_WAVE_SCROLL_T4.get());
                        output.accept(ModItems.REINFORCED_SCROLL_T1.get());
                        output.accept(ModItems.REINFORCED_SCROLL_T2.get());
                        output.accept(ModItems.REINFORCED_SCROLL_T3.get());
                        output.accept(ModItems.REINFORCED_SCROLL_T4.get());

                        //SPAWN EGGS
                        output.accept(ModItems.HERETIC_SPAWN_EGG.get());
                        output.accept(ModItems.SPIKEDRAT_SPAWN_EGG.get());
                        output.accept(ModItems.HOODED_RAT_SPAWN_EGG.get());
                        output.accept(ModItems.RAT_LORD_SPAWN_EGG.get());
                        output.accept(ModItems.OGRE_SPAWN_EGG.get());
                        output.accept(ModItems.ORC_WARRIOR_SPAWN_EGG.get());
                        output.accept(ModItems.SCORPION_SPAWN_EGG.get());
                        output.accept(ModItems.FUNGI_SWORDSMAN_SPAWN_EGG.get());
                        output.accept(ModItems.SPORE_SPREADER_SPAWN_EGG.get());
                        output.accept(ModItems.BEEFSTEAK_MUSHROOM_SPAWN_EGG.get());

                        //CUSTOM ITEMS
                        output.accept(ModItems.DAO_CANNON.get());

                        //HUMAN GRADE GEAR
                         //Tier 1
                        output.accept(ModItems.HUMAN_GRADE_HELMET.get());
                        output.accept(ModItems.HUMAN_GRADE_CHESTPLATE.get());
                        output.accept(ModItems.HUMAN_GRADE_LEGGINGS.get());
                        output.accept(ModItems.HUMAN_GRADE_BOOTS.get());
                        output.accept(ModItems.LOWER_HUMAN_GRADE_SABER.get());
                        output.accept(ModItems.HUMAN_GRADE_PAXEL.get());
                        output.accept(ModItems.LOWER_HUMAN_GRADE_SHIELD.get());
                           //Tier 2
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_HELMET.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_CHESTPLATE.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_LEGGINGS.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_BOOTS.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_SABER.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_SHIELD.get());
                                //Tier 3
                        output.accept(ModItems.UPPER_HUMAN_GRADE_HELMET.get());
                        output.accept(ModItems.UPPER_HUMAN_GRADE_CHESTPLATE.get());
                        output.accept(ModItems.UPPER_HUMAN_GRADE_LEGGINGS.get());
                        output.accept(ModItems.UPPER_HUMAN_GRADE_BOOTS.get());
                        output.accept(ModItems.UPPER_HUMAN_GRADE_SABER.get());
                        output.accept(ModItems.UPPER_HUMAN_GRADE_SHIELD.get());
                                    //Divine
                        output.accept(ModItems.DIVINE_HUMAN_GRADE_HELMET.get());
                        output.accept(ModItems.DIVINE_HUMAN_GRADE_CHESTPLATE.get());
                        output.accept(ModItems.DIVINE_HUMAN_GRADE_LEGGINGS.get());
                        output.accept(ModItems.DIVINE_HUMAN_GRADE_BOOTS.get());
                        output.accept(ModItems.DIVINE_HUMAN_GRADE_SABER.get());
                        output.accept(ModItems.DIVINE_HUMAN_GRADE_SHIELD.get());

                        //ORES
                        output.accept(ModBlocks.JADE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_JADE_ORE.get());
                        output.accept(ModBlocks.SPIRIT_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SPIRIT_ORE.get());
                        output.accept(ModBlocks.PURPLE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_PURPLE_ORE.get());
                        output.accept(ModBlocks.SAINT_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SAINT_ORE.get());
                        output.accept(ModBlocks.DIVINE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_DIVINE_ORE.get());

                        //BLOCK ENTITIES & CUSTOM BLOCKS
                        output.accept(ModBlocks.PORTAL_BLOCK_HUMAN_REALM.get());
                        output.accept(ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get());
                        output.accept(ModBlocks.HUMAN_GRADE_DIVINE_ALTAR_BLOCK.get());

                        output.accept(ModBlocks.INFUSED_JADE_BLOCK.get());

                        //Blocks
                        output.accept(ModBlocks.HUMAN_REALM_PORTAL.get());
                        output.accept(ModBlocks.JADE_BLOCK.get());
                        output.accept(ModBlocks.SPIRIT_BLOCK.get());
                        output.accept(ModBlocks.PURPLE_BLOCK.get());
                        output.accept(ModBlocks.SAINT_BLOCK.get());
                        output.accept(ModBlocks.DIVINE_BLOCK_BLANK.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED.get());
                        //output.accept(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get());
                        output.accept(ModItems.LIGHT_RUNIC_ITEM.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED_RUNIC.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_CRACKED.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_CRACKED_RUNIC.get());
                        //output.accept(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get());
                        output.accept(ModItems.DARK_RUNIC_ITEM.get());
                        output.accept(ModBlocks.CHISELED_DIVINE_BLOCK_SIDEWAYS.get());
                        output.accept(ModBlocks.CHISELED_DIVINE_BLOCK_NORMAL.get());
                        output.accept(ModBlocks.SPIRIT_BRICK.get());
                        output.accept(ModBlocks.PURPLE_BRICK.get());
                        output.accept(ModBlocks.SAINT_BRICK.get());
                        output.accept(ModBlocks.SPIRIT_MOSS_CARPET.get());


                        output.accept(ModItems.SPIRIT_MOSS_ITEM.get());
                        output.accept(ModItems.LEMON_MOSS_ITEM.get());
                        output.accept(ModItems.ROSY_MOSS_ITEM.get());
                        output.accept(ModItems.OCTOS_GROWTH_ITEM.get());
                        output.accept(ModItems.FEYS_SORROW_ITEM.get());
                        output.accept(ModItems.EVES_BLUES_ITEM.get());
                        output.accept(ModItems.SPIRIT_MOSS_CARPET_ITEM.get());
                        output.accept(ModItems.LEMON_MOSS_CARPET_ITEM.get());
                        output.accept(ModItems.ROSY_MOSS_CARPET_ITEM.get());
                        output.accept(ModItems.OCTOS_GROWTH_CARPET_ITEM.get());
                        output.accept(ModItems.FEYS_SORROW_CARPET_ITEM.get());
                        output.accept(ModItems.EVES_BLUES_CARPET_ITEM.get());
                        output.accept(ModItems.FEY_BLOCK_ITEM.get());
                        output.accept(ModItems.FEY_CLUSTER_ITEM.get());
                        output.accept(ModItems.LARGE_FEY_BUD_ITEM.get());
                        output.accept(ModItems.MEDIUM_FEY_BUD_ITEM.get());
                        output.accept(ModItems.SMALL_FEY_BUD_ITEM.get());
                        output.accept(ModItems.TURQUOISE_BLOCK_ITEM.get());
                        output.accept(ModItems.TURQUOISE_CLUSTER_ITEM.get());
                        output.accept(ModItems.LARGE_TURQUOISE_BUD_ITEM.get());
                        output.accept(ModItems.MEDIUM_TURQUOISE_BUD_ITEM.get());
                        output.accept(ModItems.SMALL_TURQUOISE_BUD_ITEM.get());

                        output.accept(ModBlocks.TRAPPED_SOUL_SAND.get());
                        output.accept(ModBlocks.DESOLATE_SANDSTONE.get());
                        output.accept(ModBlocks.DESOLATE_SAND.get());
                        output.accept(ModBlocks.EVES_COBBLESTONE.get());
                        output.accept(ModBlocks.POISON_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.PURPLE_BIOSHROOM.get());

                        //STAIRS
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_STAIRS.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_STAIRS.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_STAIRS.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_RUNIC_STAIRS.get());
                        output.accept(ModBlocks.SPIRIT_BRICK_STAIRS.get());
                        output.accept(ModBlocks.PURPLE_BRICK_STAIRS.get());
                        output.accept(ModBlocks.SAINT_BRICK_STAIRS.get());

                        //SLABS
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_SLAB.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_SLAB.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB.get());
                        output.accept(ModBlocks.SPIRIT_BRICK_SLAB.get());
                        output.accept(ModBlocks.PURPLE_BRICK_SLAB.get());
                        output.accept(ModBlocks.SAINT_BRICK_SLAB.get());

                        //WALLS
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_WALL.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_WALL.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL.get());
                        output.accept(ModBlocks.SPIRIT_BRICK_WALL.get());
                        output.accept(ModBlocks.PURPLE_BRICK_WALL.get());
                        output.accept(ModBlocks.SAINT_BRICK_WALL.get());

                        //TREES
                        output.accept(ModItems.FIERO_LEAVES_ITEM.get());
                        output.accept(ModItems.JASMINE_LEAVES_ITEM.get());
                        output.accept(ModItems.JADED_LEAVES_ITEM.get());
                        output.accept(ModItems.KINDER_LEAVES_ITEM.get());
                        output.accept(ModItems.MANGO_LEAVES_ITEM.get());
                        output.accept(ModItems.SPIRIT_LEAVES_ITEM.get());
                        output.accept(ModBlocks.FIERO_LEAVES.get());
                        output.accept(ModBlocks.JASMINE_LEAVES.get());
                        output.accept(ModBlocks.KINDER_LEAVES.get());
                        output.accept(ModBlocks.MANGO_LEAVES.get());
                        output.accept(ModBlocks.SPIRIT_BLOSSOM_LEAVES.get());
                        output.accept(ModBlocks.JADED_LEAVES.get());
                        output.accept(ModBlocks.FIERO_SAPLING.get());
                        output.accept(ModBlocks.JASMINE_SAPLING.get());
                        output.accept(ModBlocks.KINDER_SAPLING.get());
                        output.accept(ModBlocks.MANGO_SAPLING.get());
                        output.accept(ModBlocks.SPIRIT_SAPLING.get());
                        output.accept(ModBlocks.JADED_SAPLING.get());

                        //PLANTS
                        output.accept(ModBlocks.BLUE_ROSE_BUSH.get());
                        output.accept(ModBlocks.WHITE_ROSE_BUSH.get());
                        output.accept(ModBlocks.PINK_ROSE_BUSH.get());
                        output.accept(ModBlocks.PURPLE_ROSE_BUSH.get());
                        output.accept(ModBlocks.LAVENDER_ROSE_BUSH.get());
                        output.accept(ModBlocks.ORANGE_ROSE_BUSH.get());
                        output.accept(ModBlocks.YELLOW_ROSE_BUSH.get());
                        output.accept(ModBlocks.FAE_ROSE_BUSH.get());
                        output.accept(ModBlocks.SPIRIT_ROSE_BUSH.get());
                        output.accept(ModBlocks.SPIRIT_FAE_ROSE_BUSH.get());
                        output.accept(ModBlocks.MAHOGANY_ROSE_BUSH.get());
                        output.accept(ModBlocks.COTTONS_ROSE_BUSH.get());
                        output.accept(ModBlocks.MIDNIGHTS_REACH.get());
                        output.accept(ModBlocks.CARMINE_ROSE_BUSH.get());
                        output.accept(ModBlocks.CERULEAN_ROSE_BUSH.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}