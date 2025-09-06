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
import net.minecraftforge.client.model.generators.ModelFile;
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
        simpleItem(ModItems.DIVINE_METAL_TIER1);
        simpleItem(ModItems.BEAST_BLOOD);
        simpleItem(ModItems.BEAST_OIL);
        simpleItem(ModItems.HUMAN_GRADE_SOUL_LOWER);
        simpleItem(ModItems.HUMAN_GRADE_SOUL_MEDIUM);
        simpleItem(ModItems.HUMAN_GRADE_SOUL_UPPER);
        simpleItem(ModItems.INFUSED_JADE);
        simpleItem(ModItems.ORIGIN_BERRIES);
        simpleItem(ModItems.HUMAN_REALM_SEEDS);

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
        //Tier 3 UPPER
        simpleItem(ModItems.UPPER_HUMAN_GRADE_HELMET);
        simpleItem(ModItems.UPPER_HUMAN_GRADE_CHESTPLATE);
        simpleItem(ModItems.UPPER_HUMAN_GRADE_LEGGINGS);
        simpleItem(ModItems.UPPER_HUMAN_GRADE_BOOTS);
        handheldItem(ModItems.UPPER_HUMAN_GRADE_SABER);
        //DIVINE GEAR
        simpleItem(ModItems.DIVINE_HUMAN_GRADE_HELMET);
        simpleItem(ModItems.DIVINE_HUMAN_GRADE_CHESTPLATE);
        simpleItem(ModItems.DIVINE_HUMAN_GRADE_LEGGINGS);
        simpleItem(ModItems.DIVINE_HUMAN_GRADE_BOOTS);
        handheldItem(ModItems.DIVINE_HUMAN_GRADE_SABER);

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
        withExistingParent(ModItems.RAT_LORD_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        blockParentItem("turquoise_block");
        blockParentItem("feys_block");
        blockParentItem("small_turquoise_bud");
        blockParentItem("medium_turquoise_bud");
        blockParentItem("large_turquoise_bud");
        blockParentItem("turquoise_cluster");
        blockParentItem("small_feys_bud");
        blockParentItem("medium_feys_bud");
        blockParentItem("large_feys_bud");
        blockParentItem("feys_cluster");

        //complexBlock(ModBlocks.GATHERING_FORMATION_T1.get());
        complexBlock(ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get());
        String itemId = ForgeRegistries.ITEMS.getKey(ModBlocks.INFUSION_TABLE_1.get().asItem()).getPath();
        withExistingParent(itemId, modLoc("block/infusion_table1"));

        //Moss
        blockItem(ModBlocks.SPIRIT_MOSS.get());
        blockItem(ModBlocks.SPIRIT_MOSS_CARPET.get());
        blockItem(ModBlocks.LEMON_MOSS.get());
        blockItem(ModBlocks.LEMON_MOSS_CARPET.get());
        blockItem(ModBlocks.ROSY_MOSS.get());
        blockItem(ModBlocks.ROSY_MOSS_CARPET.get());
        blockItem(ModBlocks.OCTOS_GROWTH.get());
        blockItem(ModBlocks.OCTOS_GROWTH_CARPET.get());
        blockItem(ModBlocks.FEYS_SORROW.get());
        blockItem(ModBlocks.FEYS_SORROW_CARPET.get());
        blockItem(ModBlocks.EVES_BLUES.get());
        blockItem(ModBlocks.EVES_BLUES_CARPET.get());

        //Trees
        saplingItem(ModBlocks.FIERO_SAPLING);
        saplingItem(ModBlocks.JASMINE_SAPLING);
        saplingItem(ModBlocks.KINDER_SAPLING);
        saplingItem(ModBlocks.MANGO_SAPLING);
        saplingItem(ModBlocks.SPIRIT_SAPLING);
        saplingItem(ModBlocks.JADED_SAPLING);
    }

    private void blockParentItem(String id) {
        getBuilder(id).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + id)));
    }

    private void blockItem(Block block) {
        var id = ForgeRegistries.BLOCKS.getKey(block);
        assert id != null;

        // Parent path: <modid>:block/<blockName>
        var parent = new ModelFile.UncheckedModelFile(
                new ResourceLocation(id.getNamespace(), "block/" + id.getPath())
        );

        // Item model name = item registry name (usually same as block)
        getBuilder(id.getPath()).parent(parent);
    }

    private void simpleBlockItem(Block b) {
        String id = ForgeRegistries.BLOCKS.getKey(b).getPath();
        withExistingParent(id, modLoc("block/" + id));
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
