package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PathToImmortals.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.DIVINE_CRYSTAL);
        simpleItem(ModItems.JADE);
        simpleItem(ModItems.SPIRIT_CRYSTAL);
        simpleItem(ModItems.SAINT_CRYSTAL);
        simpleItem(ModItems.PURPLE_CRYSTAL);
        simpleItem(ModItems.JADE_COIN);
        simpleItem(ModItems.SPIRIT_COIN);
        simpleItem(ModItems.PURPLE_COIN);
        simpleItem(ModItems.SAINT_COIN);
        simpleItem(ModItems.DIVINE_COIN);
        simpleItem(ModItems.SPIRIT_IRON_TIER1);
        simpleItem(ModItems.SPIRIT_IRON_TIER2);
        simpleItem(ModItems.SPIRIT_IRON_TIER3);
        simpleItem(ModItems.SPIRIT_IRON_TIER4);
        simpleItem(ModItems.SPIRIT_IRON_TIER5);
        simpleItem(ModItems.BEAST_BLOOD);
        simpleItem(ModItems.BEAST_OIL);
        simpleItem(ModItems.SOUL);

        //Armor
        //HUMAN GRADE GEAR
        //Tier 1 LOWER
        simpleItem(ModItems.HUMAN_GRADE_HELMET);
        simpleItem(ModItems.HUMAN_GRADE_CHESTPLATE);
        simpleItem(ModItems.HUMAN_GRADE_LEGGINGS);
        simpleItem(ModItems.HUMAN_GRADE_BOOTS);
        handheldItem(ModItems.LOWER_HUMAN_GRADE_SABER);
        handheldItem(ModItems.HUMAN_GRADE_PAXEL);
        //Tier 2 MEDIUM
        simpleItem(ModItems.MEDIUM_HUMAN_GRADE_HELMET);
        simpleItem(ModItems.MEDIUM_HUMAN_GRADE_CHESTPLATE);
        simpleItem(ModItems.MEDIUM_HUMAN_GRADE_LEGGINGS);
        simpleItem(ModItems.MEDIUM_HUMAN_GRADE_BOOTS);
        handheldItem(ModItems.MEDIUM_HUMAN_GRADE_SABER);

        wallItem(ModBlocks.LIGHT_DIVINE_BRICK_WALL, ModBlocks.LIGHT_DIVINE_BRICK);
        wallItem(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL, ModBlocks.LIGHT_DIVINE_BRICK_RUNIC);
        wallItem(ModBlocks.DARK_DIVINE_BRICK_WALL, ModBlocks.DARK_DIVINE_BRICK);
        wallItem(ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL, ModBlocks.DARK_DIVINE_BRICK_RUNIC);
        wallItem(ModBlocks.SPIRIT_BRICK_WALL, ModBlocks.SPIRIT_BRICK);
        wallItem(ModBlocks.PURPLE_BRICK_WALL, ModBlocks.PURPLE_BRICK);
        wallItem(ModBlocks.SAINT_BRICK_WALL, ModBlocks.SAINT_BRICK);

        withExistingParent(ModItems.HERETIC_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SPIKEDRAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.DARKLIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.HOODED_RAT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        //complexBlock(ModBlocks.GATHERING_FORMATION_T1.get());
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PathToImmortals.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), new ResourceLocation(PathToImmortals.MOD_ID,
                "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(PathToImmortals.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PathToImmortals.MOD_ID,"item/" + item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(PathToImmortals.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(PathToImmortals.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PathToImmortals.MOD_ID,"block/" + item.getId().getPath()));
    }
}
