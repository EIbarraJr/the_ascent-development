package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.block.custom.CoinBlocks;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PathToImmortals.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Blocks
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
        simpleBlockWithItem(ModBlocks.INFUSTION_TABLE_T1.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/infusion_table1")));


        //GLOWING BLOCKS ON/OFF
        glowingJade();
        glowingSpiritBlock();
        glowingPurpleBlock();
        glowingSaintBlock();
        glowingDivineBlockBlank();

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