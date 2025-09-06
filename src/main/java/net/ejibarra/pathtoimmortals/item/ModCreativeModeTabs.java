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

                        //ITEMS
                        output.accept(ModItems.JADE.get());
                        output.accept(ModItems.SPIRIT_CRYSTAL.get());
                        output.accept(ModItems.PURPLE_CRYSTAL.get());
                        output.accept(ModItems.SAINT_CRYSTAL.get());
                        output.accept(ModItems.DIVINE_CRYSTAL.get());
                        output.accept(ModItems.JADE_COIN.get());
                        output.accept(ModItems.SPIRIT_COIN.get());
                        output.accept(ModItems.PURPLE_COIN.get());
                        output.accept(ModItems.SAINT_COIN.get());
                        output.accept(ModItems.DIVINE_COIN.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER1.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER2.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER3.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER4.get());
                        output.accept(ModItems.SPIRIT_IRON_TIER5.get());
                        output.accept(ModItems.BEAST_BLOOD.get());
                        output.accept(ModItems.BEAST_OIL.get());
                        output.accept(ModItems.SOUL.get());

                        //SPAWN EGGS
                        output.accept(ModItems.HERETIC_SPAWN_EGG.get());
                        output.accept(ModItems.SPIKEDRAT_SPAWN_EGG.get());
                        output.accept(ModItems.DARKLIN_SPAWN_EGG.get());
                        output.accept(ModItems.HOODED_RAT_SPAWN_EGG.get());

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
                           //Tier 2
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_HELMET.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_CHESTPLATE.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_LEGGINGS.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_BOOTS.get());
                        output.accept(ModItems.MEDIUM_HUMAN_GRADE_SABER.get());

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
                        //output.accept(ModBlocks.GATHERING_FORMATION_T1.get());
                        output.accept(ModBlocks.INFUSTION_TABLE_T1.get());
                        output.accept(ModBlocks.JADE_BLOCK.get());
                        output.accept(ModBlocks.SPIRIT_BLOCK.get());
                        output.accept(ModBlocks.PURPLE_BLOCK.get());
                        output.accept(ModBlocks.SAINT_BLOCK.get());
                        output.accept(ModBlocks.DIVINE_BLOCK_BLANK.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get());
                        output.accept(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED_RUNIC.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_CRACKED.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_CRACKED_RUNIC.get());
                        output.accept(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get());
                        output.accept(ModBlocks.CHISELED_DIVINE_BLOCK_SIDEWAYS.get());
                        output.accept(ModBlocks.CHISELED_DIVINE_BLOCK_NORMAL.get());
                        output.accept(ModBlocks.SPIRIT_BRICK.get());
                        output.accept(ModBlocks.PURPLE_BRICK.get());
                        output.accept(ModBlocks.SAINT_BRICK.get());

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

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}