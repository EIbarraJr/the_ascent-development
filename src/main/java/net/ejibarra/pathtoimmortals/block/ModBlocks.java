package net.ejibarra.pathtoimmortals.block;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.custom.CoinBlocks;
import net.ejibarra.pathtoimmortals.block.custom.InfusionTableBlock1;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PathToImmortals.MOD_ID);
    // Shared tier property: 1..5 (used by the solar generator blockstate)
    public static final IntegerProperty TIER = IntegerProperty.create("tier", 1, 5);

    //GLOWING BLOCKS ON/OFF
    public static final RegistryObject<Block> DIVINE_BLOCK_BLANK = registerBlock("divine_block_blank",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> JADE_BLOCK = registerBlock("jade_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> SPIRIT_BLOCK = registerBlock("spirit_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> PURPLE_BLOCK = registerBlock("purple_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> SAINT_BLOCK = registerBlock("saint_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    //Blocks
    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK = registerBlock("light_divine_brick",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_RUNIC = registerBlock("light_divine_brick_runic",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> CHISELED_DIVINE_BLOCK_NORMAL = registerBlock("chiseled_divine_block_normal",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> CHISELED_DIVINE_BLOCK_SIDEWAYS = registerBlock("chiseled_divine_block_sideways",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_CRACKED = registerBlock("light_divine_brick_cracked",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_CRACKED_RUNIC = registerBlock("light_divine_brick_cracked_runic",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK = registerBlock("dark_divine_brick",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_CRACKED = registerBlock("dark_divine_brick_cracked",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_CRACKED_RUNIC = registerBlock("dark_divine_brick_cracked_runic",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_RUNIC = registerBlock("dark_divine_brick_runic",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SPIRIT_BRICK = registerBlock("spirit_brick",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> PURPLE_BRICK = registerBlock("purple_brick",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SAINT_BRICK = registerBlock("saint_bricks",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 8.0F).sound(SoundType.STONE)));

    //Stairs
    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_STAIRS = registerBlock("light_divine_brick_stairs",
            () -> new StairBlock(()-> ModBlocks.LIGHT_DIVINE_BRICK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_RUNIC_STAIRS = registerBlock("light_divine_brick_runic_stairs",
            () -> new StairBlock(()-> ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_STAIRS = registerBlock("dark_divine_brick_stairs",
            () -> new StairBlock(()-> ModBlocks.DARK_DIVINE_BRICK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_RUNIC_STAIRS = registerBlock("dark_divine_brick_runic_stairs",
            () -> new StairBlock(()-> ModBlocks.DARK_DIVINE_BRICK_RUNIC.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SPIRIT_BRICK_STAIRS = registerBlock("spirit_brick_stairs",
            () -> new StairBlock(()-> ModBlocks.SPIRIT_BRICK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> PURPLE_BRICK_STAIRS = registerBlock("purple_brick_stairs",
            () -> new StairBlock(()-> ModBlocks.PURPLE_BRICK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SAINT_BRICK_STAIRS = registerBlock("saint_brick_stairs",
            () -> new StairBlock(()-> ModBlocks.SAINT_BRICK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.GRANITE_STAIRS).sound(SoundType.STONE)));

    //Slab
    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_SLAB = registerBlock("light_divine_brick_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_RUNIC_SLAB = registerBlock("light_divine_brick_runic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_SLAB = registerBlock("dark_divine_brick_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_RUNIC_SLAB = registerBlock("dark_divine_brick_runic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SPIRIT_BRICK_SLAB = registerBlock("spirit_brick_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    public static final RegistryObject<Block> PURPLE_BRICK_SLAB = registerBlock("purple_brick_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SAINT_BRICK_SLAB = registerBlock("saint_brick_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE_SLAB).sound(SoundType.STONE)));

    //Walls
    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_WALL = registerBlock("light_divine_brick_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LIGHT_DIVINE_BRICK_RUNIC_WALL = registerBlock("light_divine_brick_runic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_WALL = registerBlock("dark_divine_brick_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_DIVINE_BRICK_RUNIC_WALL = registerBlock("dark_divine_brick_runic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SPIRIT_BRICK_WALL = registerBlock("spirit_brick_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> PURPLE_BRICK_WALL = registerBlock("purple_brick_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));

    public static final RegistryObject<Block> SAINT_BRICK_WALL = registerBlock("saint_brick_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.STONE)));


    //ORES
    public static final RegistryObject<Block> DEEPSLATE_DIVINE_ORE = registerBlock("deepslate_divine_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> DEEPSLATE_JADE_ORE = registerBlock("deepslate_jade_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> DEEPSLATE_SAINT_ORE = registerBlock("deepslate_saint_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> DEEPSLATE_SPIRIT_ORE = registerBlock("deepslate_spirit_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> DEEPSLATE_PURPLE_ORE = registerBlock("deepslate_purple_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> PURPLE_ORE = registerBlock("purple_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> SAINT_ORE = registerBlock("saint_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> SPIRIT_ORE = registerBlock("spirit_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> JADE_ORE = registerBlock("jade_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    public static final RegistryObject<Block> DIVINE_ORE = registerBlock("divine_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    //Block Entities & Customs
    /*public static final RegistryObject<Block> GATHERING_FORMATION_T1 = registerBlock("gathering_formation1",
            ()-> new GatheringFormationBlock1(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));*/
    public static final RegistryObject<Block> INFUSTION_TABLE_T1 = registerBlock("infusion_table1",
            ()-> new InfusionTableBlock1(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));

    /*public static final RegistryObject<Block> GATHERING_FORMATION_T1 = BLOCKS.register("gathering_formation_t1",
            () -> new GatheringFormationBlock(1, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(2.5F)));
    public static final RegistryObject<Block> GATHERING_FORMATION_T2 = BLOCKS.register("gathering_formation_t2",
            () -> new GatheringFormationBlock(2, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(2.75F)));
    public static final RegistryObject<Block> GATHERING_FORMATION_T3 = BLOCKS.register("gathering_formation_t3",
            () -> new GatheringFormationBlock(3, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(3.0F)));
    public static final RegistryObject<Block> GATHERING_FORMATION_T4 = BLOCKS.register("gathering_formation_t4",
            () -> new GatheringFormationBlock(4, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(3.25F)));
    public static final RegistryObject<Block> GATHERING_FORMATION_T5 = BLOCKS.register("gathering_formation_t5",
            () -> new GatheringFormationBlock(5, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(3.5F)));*/






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;

    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }



    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
