package net.ejibarra.pathtoimmortals.block;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.custom.*;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.ejibarra.pathtoimmortals.particle.ModParticles;
import net.ejibarra.pathtoimmortals.util.ModTags;
import net.ejibarra.pathtoimmortals.worldgen.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PathToImmortals.MOD_ID);
    // Shared tier property: 1..5 (used by the solar generator blockstate)
    public static final IntegerProperty TIER = IntegerProperty.create("tier", 1, 5);

    //GLOWING BLOCKS ON/OFF
    public static final RegistryObject<Block> DIVINE_BLOCK_BLANK = registerBlock("divine_block_blank",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> JADE_BLOCK = registerBlock("jade_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> SPIRIT_BLOCK = registerBlock("spirit_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> PURPLE_BLOCK = registerBlock("purple_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    public static final RegistryObject<Block> SAINT_BLOCK = registerBlock("saint_block",
            ()-> new CoinBlocks(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.CHAIN)
                    .lightLevel(state -> state.getValue(CoinBlocks.CLICKED) ? 15: 0)));

    //Blocks
    public static final RegistryObject<Block> DESOLATE_SAND = registerBlock("desolate_sand",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<Block> TRAPPED_SOUL_SAND = registerBlock("trapped_soul_sand",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.5F).sound(SoundType.SOUL_SAND)));
    public static final RegistryObject<Block> DESOLATE_SANDSTONE = registerBlock("desolate_sandstone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));

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

    //INFUSED BLOCKS
    public static final RegistryObject<Block> INFUSED_JADE_BLOCK = registerBlock("infused_jade_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(2.0F, 10.0F).sound(SoundType.STONE).lightLevel(state-> 5 )));

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
    public static final RegistryObject<Block> INFUSION_TABLE_1 = BLOCKS.register("infusion_table1",
            ()-> new InfusionTableBlock1(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> HUMAN_GRADE_INFUSER_BLOCK = registerBlock("human_grade_infuser",
            ()-> new HumanGradeInfuserBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE).noOcclusion()));

    public static final RegistryObject<Block> HUMAN_GRADE_DIVINE_ALTAR_BLOCK = registerBlock("human_grade_divine_altar",
            ()-> new HumanGradeDivineAltar(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE).noOcclusion()));

    //CROPS
    public static final RegistryObject<Block> HUMAN_REALM_CROP = BLOCKS.register("human_realm_crop",
            ()-> new HumanRealmCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission().randomTicks().instabreak(), ModBlocks.INFUSED_JADE_BLOCK.get()));

    //Trees
    public static final RegistryObject<Block> FIERO_LEAVES = BLOCKS.register("fiero_leaves",
            ()-> new ColoredPetalLeavesBlock(ModParticles.FIERO_LEAVES_PARTICLE, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}}
    );
    public static final RegistryObject<Block> JASMINE_LEAVES = BLOCKS.register("jasmine_leaves",
            ()-> new ColoredPetalLeavesBlock(ModParticles.JASMINE_LEAVES_PARTICLE, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}}
    );
    public static final RegistryObject<Block> KINDER_LEAVES = BLOCKS.register("kinder_leaves",
            ()-> new ColoredPetalLeavesBlock(ModParticles.KINDER_LEAVES_PARTICLE, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}}
    );
    public static final RegistryObject<Block> MANGO_LEAVES = BLOCKS.register("mango_leaves",
            ()-> new ColoredPetalLeavesBlock(ModParticles.MANGO_LEAVES_PARTICLE, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}}
    );
    public static final RegistryObject<Block> SPIRIT_BLOSSOM_LEAVES = BLOCKS.register("spirit_blossom_leaves",
            ()-> new ColoredPetalLeavesBlock(ModParticles.SPIRIT_LEAVES_PARTICLE, BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}}
    );
    public static final RegistryObject<Block> JADED_LEAVES = BLOCKS.register("jaded_leaves",
            ()-> new ColoredPetalLeavesBlock(ModParticles.JADED_LEAVES_PARTICLE,BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}}
    );

    public static final RegistryObject<Block> FIERO_SAPLING = registerBlock("fiero_sapling", ()-> new SaplingBlock(new FieroTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> JASMINE_SAPLING = registerBlock("jasmine_sapling", ()-> new SaplingBlock(new JasmineTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> KINDER_SAPLING = registerBlock("kinder_sapling", ()-> new SaplingBlock(new KinderTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> MANGO_SAPLING = registerBlock("mango_sapling", ()-> new SaplingBlock(new MangoTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> SPIRIT_SAPLING = registerBlock("spirit_sapling", ()-> new SaplingBlock(new SpiritTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> JADED_SAPLING = registerBlock("jaded_sapling", ()-> new SaplingBlock(new JadedTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));


    //Moss
    // Carpet blocks (optional)
    public static final RegistryObject<Block> SPIRIT_MOSS_CARPET =
            BLOCKS.register("spirit_moss_carpet", () ->
                    new MossCarpetBlock(Block.Properties.of().noCollission().noOcclusion().strength(0.0F).sound(SoundType.MOSS_CARPET).lightLevel(s -> 4)));
    public static final RegistryObject<Block> LEMON_MOSS_CARPET =
            BLOCKS.register("lemon_moss_carpet", () ->
                    new MossCarpetBlock(Block.Properties.of().noCollission().noOcclusion().strength(0.0F).sound(SoundType.MOSS_CARPET).lightLevel(s -> 4)));
    public static final RegistryObject<Block> ROSY_MOSS_CARPET =
            BLOCKS.register("rosy_moss_carpet", () ->
                    new MossCarpetBlock(Block.Properties.of().noCollission().noOcclusion().strength(0.0F).sound(SoundType.MOSS_CARPET).lightLevel(s -> 4)));
    public static final RegistryObject<Block> OCTOS_GROWTH_CARPET =
            BLOCKS.register("octos_growth_carpet", () ->
                    new MossCarpetBlock(Block.Properties.of().noCollission().noOcclusion().strength(0.0F).sound(SoundType.MOSS_CARPET).lightLevel(s -> 4)));
    public static final RegistryObject<Block> FEYS_SORROW_CARPET =
            BLOCKS.register("feys_sorrow_carpet", () ->
                    new MossCarpetBlock(Block.Properties.of().noCollission().noOcclusion().strength(0.0F).sound(SoundType.MOSS_CARPET).lightLevel(s -> 4)));
    public static final RegistryObject<Block> EVES_BLUES_CARPET =
            BLOCKS.register("eves_blues_carpet", () ->
                    new MossCarpetBlock(Block.Properties.of().noCollission().noOcclusion().strength(0.0F).sound(SoundType.MOSS_CARPET).lightLevel(s -> 4)));

    // Main spreadable blocks, each with its own Config
    public static final RegistryObject<Block> SPIRIT_MOSS =
            BLOCKS.register("spirit_moss", () ->
                    new SpreadableMossBlock(
                            Block.Properties.of().strength(0.1F).noOcclusion().randomTicks(),
                            new SpreadableMossBlock.Config(
                                    () -> ModBlocks.SPIRIT_MOSS.get(),
                                    () -> ModBlocks.SPIRIT_MOSS_CARPET.get(),
                                    ModTags.Blocks.SPIRIT_MOSS_SPREADABLE,
                                    5, 2, 64,        // radiusXZ, radiusY, attempts change 64 to a higher value for growth of floraye
                                    0.35F,           // carpet chance
                                    null, // cyan
                                    0.3f,
                                    List.of(
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.FLOWERING_AZALEA, 10f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.BIG_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.SMALL_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.ORANGE_TULIP, 15f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.POPPY, 15f)

                                    )        // flora list (add your tiny plants here)
                            )
                    )
            );
    public static final RegistryObject<Block> LEMON_MOSS =
            BLOCKS.register("lemon_moss", () ->
                    new SpreadableMossBlock(
                            Block.Properties.of().strength(0.1F).noOcclusion().randomTicks(),
                            new SpreadableMossBlock.Config(
                                    () -> ModBlocks.LEMON_MOSS.get(),
                                    () -> ModBlocks.LEMON_MOSS_CARPET.get(),
                                    ModTags.Blocks.LEMON_MOSS_SPREADABLE,
                                    5, 2, 64,        // radiusXZ, radiusY, attempts change 64 to a higher value for growth of floraye
                                    0.35F,           // carpet chance
                                    null,
                                    0.3f,
                                    List.of(
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.FLOWERING_AZALEA, 10f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.BIG_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.SMALL_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.ORANGE_TULIP, 15f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.POPPY, 15f)
                                    )        // flora list (add your tiny plants here)
                            )
                    )
            );
    public static final RegistryObject<Block> ROSY_MOSS =
            BLOCKS.register("rosy_moss", () ->
                    new SpreadableMossBlock(
                            Block.Properties.of().strength(0.1F).noOcclusion().randomTicks(),
                            new SpreadableMossBlock.Config(
                                    () -> ModBlocks.ROSY_MOSS.get(),
                                    () -> ModBlocks.ROSY_MOSS_CARPET.get(),
                                    ModTags.Blocks.ROSY_MOSS_SPREADABLE,
                                    5, 2, 64,        // radiusXZ, radiusY, attempts change 64 to a higher value for growth of floraye
                                    0.35F,           // carpet chance
                                    null, // cyan
                                    0.3f,
                                    List.of(
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.FLOWERING_AZALEA, 10f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.BIG_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.SMALL_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.ORANGE_TULIP, 15f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.POPPY, 15f)
                                    )        // flora list (add your tiny plants here)
                            )
                    )
            );
    public static final RegistryObject<Block> OCTOS_GROWTH =
            BLOCKS.register("octos_growth", () ->
                    new SpreadableMossBlock(
                            Block.Properties.of().strength(0.1F).noOcclusion().randomTicks(),
                            new SpreadableMossBlock.Config(
                                    () -> ModBlocks.OCTOS_GROWTH.get(),
                                    () -> ModBlocks.OCTOS_GROWTH_CARPET.get(),
                                    ModTags.Blocks.OCTOS_GROWTH_SPREADABLE,
                                    5, 2, 64,        // radiusXZ, radiusY, attempts change 64 to a higher value for growth of floraye
                                    0.35F,           // carpet chance
                                    null, // cyan
                                    0.3f,
                                    List.of(
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.FLOWERING_AZALEA, 10f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.BIG_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.SMALL_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.ORANGE_TULIP, 15f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.POPPY, 15f)
                                    )        // flora list (add your tiny plants here)
                            )
                    )
            );
    public static final RegistryObject<Block> FEYS_SORROW =
            BLOCKS.register("feys_sorrow", () ->
                    new SpreadableMossBlock(
                            Block.Properties.of().strength(0.1F).noOcclusion().randomTicks(),
                            new SpreadableMossBlock.Config(
                                    () -> ModBlocks.FEYS_SORROW.get(),
                                    () -> ModBlocks.FEYS_SORROW_CARPET.get(),
                                    ModTags.Blocks.FEYS_SORROW_SPREADABLE,
                                    5, 2, 64,        // radiusXZ, radiusY, attempts change 64 to a higher value for growth of floraye
                                    0.35F,           // carpet chance
                                    null, // cyan
                                    0.3f,
                                    List.of(
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.FLOWERING_AZALEA, 10f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.BIG_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.SMALL_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.ORANGE_TULIP, 15f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.POPPY, 15f)
                                    )        // flora list (add your tiny plants here)
                            )
                    )
            );
    public static final RegistryObject<Block> EVES_BLUES =
            BLOCKS.register("eves_blues", () ->
                    new SpreadableMossBlock(
                            Block.Properties.of().strength(0.1F).noOcclusion().randomTicks(),
                            new SpreadableMossBlock.Config(
                                    () -> ModBlocks.EVES_BLUES.get(),
                                    () -> ModBlocks.EVES_BLUES_CARPET.get(),
                                    ModTags.Blocks.EVES_BLUES_SPREADABLE,
                                    5, 2, 64,        // radiusXZ, radiusY, attempts change 64 to a higher value for growth of floraye
                                    0.35F,           // carpet chance
                                    null, // cyan
                                    0.3f,
                                    List.of(
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.FLOWERING_AZALEA, 10f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.BIG_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.SMALL_DRIPLEAF, 20f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.ORANGE_TULIP, 15f),
                                            new SpreadableMossBlock.FloraEntry(()-> Blocks.POPPY, 15f)
                                    )        // flora list (add your tiny plants here)
                            )
                    )
            );

    //Crystals
    // --- Turquoise set ---
    public static final RegistryObject<Block> TURQUOISE_BLOCK =
            BLOCKS.register("turquoise_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> SMALL_TURQUOISE_BUD =
            BLOCKS.register("small_turquoise_bud",
                    () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD)));
    public static final RegistryObject<Block> MEDIUM_TURQUOISE_BUD =
            BLOCKS.register("medium_turquoise_bud",
                    () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD)));
    public static final RegistryObject<Block> LARGE_TURQUOISE_BUD =
            BLOCKS.register("large_turquoise_bud",
                    () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD)));
    public static final RegistryObject<Block> TURQUOISE_CLUSTER =
            BLOCKS.register("turquoise_cluster",
                    () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)));
    // --- Fey set ---
    public static final RegistryObject<Block> FEY_BLOCK =
            BLOCKS.register("feys_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> SMALL_FEY_BUD =
            BLOCKS.register("small_feys_bud",
                    () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD)));
    public static final RegistryObject<Block> MEDIUM_FEY_BUD =
            BLOCKS.register("medium_feys_bud",
                    () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD)));
    public static final RegistryObject<Block> LARGE_FEY_BUD =
            BLOCKS.register("large_feys_bud",
                    () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD)));
    public static final RegistryObject<Block> FEY_CLUSTER =
            BLOCKS.register("feys_cluster",
                    () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)));



    public static final RegistryObject<Block> HUMAN_REALM_PORTAL = registerBlock("human_portal",
            ()-> new HumanRealmPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL).noLootTable().noOcclusion().noCollission()));


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
