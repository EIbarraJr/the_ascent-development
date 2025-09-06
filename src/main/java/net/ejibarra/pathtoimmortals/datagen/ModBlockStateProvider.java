package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.block.custom.CoinBlocks;
import net.ejibarra.pathtoimmortals.block.custom.HumanRealmCropBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PathToImmortals.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Blocks
        blockWithItem(ModBlocks.HUMAN_REALM_PORTAL);
        blockWithItem(ModBlocks.DEEPSLATE_PURPLE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_DIVINE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_JADE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAINT_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SPIRIT_ORE);
        blockWithItem(ModBlocks.JADE_ORE);
        blockWithItem(ModBlocks.DIVINE_ORE);
        blockWithItem(ModBlocks.PURPLE_ORE);
        blockWithItem(ModBlocks.SAINT_ORE);
        blockWithItem(ModBlocks.SPIRIT_ORE);
        blockWithItem(ModBlocks.LIGHT_DIVINE_BRICK);
        blockWithItem(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED_RUNIC);
        blockWithItem(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED);
        blockWithItem(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC);
        blockWithItem(ModBlocks.DARK_DIVINE_BRICK);
        blockWithItem(ModBlocks.DARK_DIVINE_BRICK_RUNIC);
        blockWithItem(ModBlocks.DARK_DIVINE_BRICK_CRACKED);
        blockWithItem(ModBlocks.DARK_DIVINE_BRICK_CRACKED_RUNIC);
        blockWithItem(ModBlocks.CHISELED_DIVINE_BLOCK_NORMAL);
        blockWithItem(ModBlocks.CHISELED_DIVINE_BLOCK_SIDEWAYS);
        blockWithItem(ModBlocks.SPIRIT_BRICK);
        blockWithItem(ModBlocks.PURPLE_BRICK);
        blockWithItem(ModBlocks.SAINT_BRICK);
        blockWithItem(ModBlocks.TRAPPED_SOUL_SAND);
        blockWithItem(ModBlocks.DESOLATE_SANDSTONE);
        blockWithItem(ModBlocks.DESOLATE_SAND);
        simpleBlock(ModBlocks.SPIRIT_MOSS.get(), cubeAll(ModBlocks.SPIRIT_MOSS.get()));
        simpleBlock(ModBlocks.LEMON_MOSS.get(), cubeAll(ModBlocks.LEMON_MOSS.get()));
        simpleBlock(ModBlocks.ROSY_MOSS.get(), cubeAll(ModBlocks.ROSY_MOSS.get()));
        simpleBlock(ModBlocks.OCTOS_GROWTH.get(), cubeAll(ModBlocks.OCTOS_GROWTH.get()));
        simpleBlock(ModBlocks.FEYS_SORROW.get(), cubeAll(ModBlocks.FEYS_SORROW.get()));
        simpleBlock(ModBlocks.EVES_BLUES.get(), cubeAll(ModBlocks.EVES_BLUES.get()));



        blockWithItem(ModBlocks.INFUSED_JADE_BLOCK);

        //Carpet
        carpetBlock(ModBlocks.SPIRIT_MOSS_CARPET.get(), "spirit_moss_carpet");
        carpetBlock(ModBlocks.LEMON_MOSS_CARPET.get(), "lemon_moss_carpet");
        carpetBlock(ModBlocks.ROSY_MOSS_CARPET.get(), "rosy_moss_carpet");
        carpetBlock(ModBlocks.OCTOS_GROWTH_CARPET.get(), "octos_growth_carpet");
        carpetBlock(ModBlocks.FEYS_SORROW_CARPET.get(), "feys_sorrow_carpet");
        carpetBlock(ModBlocks.EVES_BLUES_CARPET.get(), "eves_blues_carpet");

        //Stairs Block
        blockItem(ModBlocks.LIGHT_DIVINE_BRICK_STAIRS);
        blockItem(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_STAIRS);
        blockItem(ModBlocks.DARK_DIVINE_BRICK_STAIRS);
        blockItem(ModBlocks.DARK_DIVINE_BRICK_RUNIC_STAIRS);
        blockItem(ModBlocks.SPIRIT_BRICK_STAIRS);
        blockItem(ModBlocks.PURPLE_BRICK_STAIRS);
        blockItem(ModBlocks.SAINT_BRICK_STAIRS);

        //Slabs
        blockItem(ModBlocks.LIGHT_DIVINE_BRICK_SLAB);
        blockItem(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB);
        blockItem(ModBlocks.DARK_DIVINE_BRICK_SLAB);
        blockItem(ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB);
        blockItem(ModBlocks.SPIRIT_BRICK_SLAB);
        blockItem(ModBlocks.PURPLE_BRICK_SLAB);
        blockItem(ModBlocks.SAINT_BRICK_SLAB);



        //CUSTOM BLOCKSTATES

        //STAIRS + TEXTURE GRABBER
        stairsBlock((StairBlock) ModBlocks.LIGHT_DIVINE_BRICK_STAIRS.get(), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK.get()));
        stairsBlock((StairBlock) ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_STAIRS.get(), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get()));
        stairsBlock((StairBlock) ModBlocks.DARK_DIVINE_BRICK_STAIRS.get(), blockTexture(ModBlocks.DARK_DIVINE_BRICK.get()));
        stairsBlock((StairBlock) ModBlocks.DARK_DIVINE_BRICK_RUNIC_STAIRS.get(), blockTexture(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get()));
        stairsBlock((StairBlock) ModBlocks.SPIRIT_BRICK_STAIRS.get(), blockTexture(ModBlocks.SPIRIT_BRICK.get()));
        stairsBlock((StairBlock) ModBlocks.PURPLE_BRICK_STAIRS.get(), blockTexture(ModBlocks.PURPLE_BRICK.get()));
        stairsBlock((StairBlock) ModBlocks.SAINT_BRICK_STAIRS.get(), blockTexture(ModBlocks.SAINT_BRICK.get()));

        //SLABS
        slabBlock(((SlabBlock) ModBlocks.LIGHT_DIVINE_BRICK_SLAB.get()), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK.get()), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK.get()));
        slabBlock(((SlabBlock) ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB.get()), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get()), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get()));
        slabBlock(((SlabBlock) ModBlocks.DARK_DIVINE_BRICK_SLAB.get()), blockTexture(ModBlocks.DARK_DIVINE_BRICK.get()), blockTexture(ModBlocks.DARK_DIVINE_BRICK.get()));
        slabBlock(((SlabBlock) ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB.get()), blockTexture(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get()), blockTexture(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get()));
        slabBlock(((SlabBlock) ModBlocks.SPIRIT_BRICK_SLAB.get()), blockTexture(ModBlocks.SPIRIT_BRICK.get()), blockTexture(ModBlocks.SPIRIT_BRICK.get()));
        slabBlock(((SlabBlock) ModBlocks.PURPLE_BRICK_SLAB.get()), blockTexture(ModBlocks.PURPLE_BRICK.get()), blockTexture(ModBlocks.PURPLE_BRICK.get()));
        slabBlock(((SlabBlock) ModBlocks.SAINT_BRICK_SLAB.get()), blockTexture(ModBlocks.SAINT_BRICK.get()), blockTexture(ModBlocks.SAINT_BRICK.get()));

        //WALLS
        wallBlock((WallBlock) ModBlocks.LIGHT_DIVINE_BRICK_WALL.get(), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK.get()));
        wallBlock((WallBlock) ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL.get(), blockTexture(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get()));
        wallBlock((WallBlock) ModBlocks.DARK_DIVINE_BRICK_WALL.get(), blockTexture(ModBlocks.DARK_DIVINE_BRICK.get()));
        wallBlock((WallBlock) ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL.get(), blockTexture(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get()));
        wallBlock((WallBlock) ModBlocks.SPIRIT_BRICK_WALL.get(), blockTexture(ModBlocks.SPIRIT_BRICK.get()));
        wallBlock((WallBlock) ModBlocks.PURPLE_BRICK_WALL.get(), blockTexture(ModBlocks.PURPLE_BRICK.get()));
        wallBlock((WallBlock) ModBlocks.SAINT_BRICK_WALL.get(), blockTexture(ModBlocks.SAINT_BRICK.get()));



        //Block Entities
        simpleBlockWithItem(ModBlocks.INFUSION_TABLE_1.get(),
                models().getExistingFile(modLoc("block/infusion_table1")));
        horizontalBlock(ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/human_grade_infuser")));
        simpleBlockWithItem(ModBlocks.HUMAN_GRADE_DIVINE_ALTAR_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/human_grade_divine_altar")));


        //GLOWING BLOCKS ON/OFF
        glowingJade();
        glowingSpiritBlock();
        glowingPurpleBlock();
        glowingSaintBlock();
        glowingDivineBlockBlank();

        simpleBlock(ModBlocks.TURQUOISE_BLOCK.get(), cubeAll(ModBlocks.TURQUOISE_BLOCK.get()));
        simpleBlock(ModBlocks.FEY_BLOCK.get(), cubeAll(ModBlocks.FEY_BLOCK.get()));


        // ----- Turquoise buds/clusters -----
        budDirectional(ModBlocks.SMALL_TURQUOISE_BUD.get(),  "block/small_amethyst_bud",  "block/small_turquoise_bud");  // key = cross
        budDirectional(ModBlocks.MEDIUM_TURQUOISE_BUD.get(), "block/medium_amethyst_bud", "block/medium_turquoise_bud"); // key = cross
        budDirectional(ModBlocks.LARGE_TURQUOISE_BUD.get(),  "block/large_amethyst_bud",  "block/large_turquoise_bud");  // key = cross

        // ----- FEYS buds/clusters (note plural) -----
        budDirectional(ModBlocks.SMALL_FEY_BUD.get(),  "block/small_amethyst_bud",  "block/small_feys_bud");
        budDirectional(ModBlocks.MEDIUM_FEY_BUD.get(), "block/medium_amethyst_bud", "block/medium_feys_bud");
        budDirectional(ModBlocks.LARGE_FEY_BUD.get(),  "block/large_amethyst_bud",  "block/large_feys_bud");

        // clusters as CROSS models (your working format)
        crossCluster(ModBlocks.TURQUOISE_CLUSTER.get(), "block/turquoise_cluster");
        crossCluster(ModBlocks.FEY_CLUSTER.get(),      "block/feys_cluster");


        //CROPS
        makeCrop(((HumanRealmCropBlock )ModBlocks.HUMAN_REALM_CROP.get()), "human_realm_stage", "human_realm_stage");

        leavesBlock(ModBlocks.FIERO_LEAVES);
        leavesBlock(ModBlocks.JASMINE_LEAVES);
        leavesBlock(ModBlocks.KINDER_LEAVES);
        leavesBlock(ModBlocks.MANGO_LEAVES);
        leavesBlock(ModBlocks.SPIRIT_BLOSSOM_LEAVES);
        leavesBlock(ModBlocks.JADED_LEAVES);
        saplingBlock(ModBlocks.FIERO_SAPLING);
        saplingBlock(ModBlocks.JASMINE_SAPLING);
        saplingBlock(ModBlocks.KINDER_SAPLING);
        saplingBlock(ModBlocks.MANGO_SAPLING);
        saplingBlock(ModBlocks.SPIRIT_SAPLING);
        saplingBlock(ModBlocks.JADED_SAPLING);

    }
    private void budDirectional(Block block, String vanillaParent, String texturePath) {
        String id = ForgeRegistries.BLOCKS.getKey(block).getPath();
        ModelFile model = models()
                .withExistingParent(id, mcLoc(vanillaParent))
                .texture("cross",    modLoc(texturePath))   // buds use "cross"
                .texture("particle", modLoc(texturePath));
        directionalBlock(block, model);
    }

    /** Emits a block model with parent minecraft:block/cross and the given texture under "cross".
     *  Also emits a blockstate. AmethystClusterBlock has FACING/WATERLOGGED; rotations won’t visually matter for a cross,
     *  but generating them is harmless and keeps JSON complete. */
    private void crossCluster(Block block, String texturePath) {
        String id = ForgeRegistries.BLOCKS.getKey(block).getPath();

        // Model: simple cross type
        ModelFile model = models()
                .withExistingParent(id, mcLoc("block/cross"))
                .texture("cross", modLoc(texturePath))
                .texture("particle", modLoc(texturePath));

        // Blockstate: rotate if it has FACING, otherwise simpleBlock()
        if (block.defaultBlockState().hasProperty(BlockStateProperties.FACING)) {
            getVariantBuilder(block).forAllStates(state -> {
                Direction facing = state.getValue(BlockStateProperties.FACING);
                int xRot = switch (facing) {
                    case DOWN -> 180;
                    case UP   -> 0;
                    case NORTH -> 90;
                    case SOUTH -> 90;
                    case WEST  -> 90;
                    case EAST  -> 90;
                };
                int yRot = switch (facing) {
                    case NORTH -> 0;
                    case SOUTH -> 180;
                    case WEST  -> 270;
                    case EAST  -> 90;
                    default    -> 0;
                };
                return ConfiguredModel.builder()
                        .modelFile(model)
                        .rotationX(xRot)
                        .rotationY(yRot)
                        .build(); // returns ConfiguredModel[]
            });
        } else {
            simpleBlock(block, model);
        }
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((HumanRealmCropBlock) block).getAgeProperty()),
                new ResourceLocation(PathToImmortals.MOD_ID, "block/" + textureName + state.getValue(((HumanRealmCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void carpetBlock(Block carpet, String textureName) {
        // 1) Make the model file
        ModelFile carpetModel = models()
                .withExistingParent(blockName(carpet), mcLoc("block/carpet"))
                .texture("wool", modLoc("block/" + textureName));

        // 2) Generate the blockstate (single-variant)
        simpleBlock(carpet, carpetModel);
    }

    // helper function to safely get a block's registry name
    private String blockName(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

    private void glowingJade() {
        getVariantBuilder(ModBlocks.JADE_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(CoinBlocks.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("jade_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" + "jade_block")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("jade_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"jade_block")))};
            }
        });
        simpleBlockItem(ModBlocks.JADE_BLOCK.get(), models().cubeAll("jade_block",
                new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"jade_block")));
    }

    private void glowingSpiritBlock() {
        getVariantBuilder(ModBlocks.SPIRIT_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(CoinBlocks.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("spirit_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" + "spirit_block")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("spirit_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"spirit_block")))};
            }
        });
        simpleBlockItem(ModBlocks.SPIRIT_BLOCK.get(), models().cubeAll("spirit_block",
                new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"spirit_block")));
    }

    private void glowingPurpleBlock() {
        getVariantBuilder(ModBlocks.PURPLE_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(CoinBlocks.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("purple_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" + "purple_block")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("purple_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"purple_block")))};
            }
        });
        simpleBlockItem(ModBlocks.PURPLE_BLOCK.get(), models().cubeAll("purple_block",
                new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"purple_block")));
    }

    private void glowingSaintBlock() {
        getVariantBuilder(ModBlocks.SAINT_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(CoinBlocks.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("saint_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" + "saint_block")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("saint_block",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"saint_block")))};
            }
        });
        simpleBlockItem(ModBlocks.SAINT_BLOCK.get(), models().cubeAll("saint_block",
                new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"saint_block")));
    }

    private void glowingDivineBlockBlank() {
        getVariantBuilder(ModBlocks.DIVINE_BLOCK_BLANK.get()).forAllStates(state -> {
            if(state.getValue(CoinBlocks.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("divine_block_blank",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" + "divine_block_blank")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("divine_block_blank",
                        new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"divine_block_blank")))};
            }
        });
        simpleBlockItem(ModBlocks.DIVINE_BLOCK_BLANK.get(), models().cubeAll("divine_block_blank",
                new ResourceLocation(PathToImmortals.MOD_ID, "block/" +"divine_block_blank")));
    }


    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("pathtoimmortals:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("pathtoimmortals:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}