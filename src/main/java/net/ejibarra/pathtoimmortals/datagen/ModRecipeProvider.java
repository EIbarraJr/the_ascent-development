package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.datagen.custom.HumanGradeInfuserRecipeBuilder;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public static final List<ItemLike> HUMAN_SMELTABLES = List.of(ModBlocks.JADE_ORE.get(), ModBlocks.DEEPSLATE_JADE_ORE.get());

    public static final List<ItemLike> ASCENDANT_SMELTABLES = List.of(ModBlocks.SPIRIT_ORE.get(), ModBlocks.DEEPSLATE_SPIRIT_ORE.get());

    public static final List<ItemLike> TRANSCENDENT_SMELTABLES = List.of(ModBlocks.PURPLE_ORE.get(), ModBlocks.DEEPSLATE_PURPLE_ORE.get());

    public static final List<ItemLike> DEMIGOD_SMELTABLES = List.of(ModBlocks.SAINT_ORE.get(), ModBlocks.DEEPSLATE_SAINT_ORE.get());

    public static final List<ItemLike> GODLY_SMELTABLES = List.of(ModBlocks.DIVINE_ORE.get(), ModBlocks.DEEPSLATE_DIVINE_ORE.get());


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //Smelt & Blasting
        oreSmelting(pWriter, HUMAN_SMELTABLES, RecipeCategory.MISC, ModItems.JADE.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, HUMAN_SMELTABLES, RecipeCategory.MISC, ModItems.JADE.get(), 0.25f, 100, "sapphire");

        oreSmelting(pWriter, ASCENDANT_SMELTABLES, RecipeCategory.MISC, ModItems.SPIRIT_CRYSTAL.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, ASCENDANT_SMELTABLES, RecipeCategory.MISC, ModItems.SPIRIT_CRYSTAL.get(), 0.25f, 100, "sapphire");

        oreSmelting(pWriter, TRANSCENDENT_SMELTABLES, RecipeCategory.MISC, ModItems.PURPLE_CRYSTAL.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, TRANSCENDENT_SMELTABLES, RecipeCategory.MISC, ModItems.PURPLE_CRYSTAL.get(), 0.25f, 100, "sapphire");

        oreSmelting(pWriter, DEMIGOD_SMELTABLES, RecipeCategory.MISC, ModItems.SAINT_CRYSTAL.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, DEMIGOD_SMELTABLES, RecipeCategory.MISC, ModItems.SAINT_CRYSTAL.get(), 0.25f, 100, "sapphire");

        oreSmelting(pWriter, GODLY_SMELTABLES, RecipeCategory.MISC, ModItems.DIVINE_CRYSTAL.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, GODLY_SMELTABLES, RecipeCategory.MISC, ModItems.DIVINE_CRYSTAL.get(), 0.25f, 100, "sapphire");

        //Normal Recipes

        // Infused Jade Block + Crude Jaded Iron -> Refined Jade Iron
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REFINED_JADE_IRON.get(), 1)
                .requires(ModBlocks.INFUSED_JADE_BLOCK.get())
                .requires(ModItems.CRUDE_JADED_IRON.get())
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .save(pWriter, new ResourceLocation(PathToImmortals.MOD_ID, "refined_jade_iron"));

        // Refined Jade Iron + 2 Infused Jade Blocks -> Reinforced Jade Iron
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REINFORCED_JADE_IRON.get())
                .requires(ModItems.REFINED_JADE_IRON.get())
                .requires(ModBlocks.INFUSED_JADE_BLOCK.get())
                .requires(ModBlocks.INFUSED_JADE_BLOCK.get())
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .save(pWriter, new ResourceLocation(PathToImmortals.MOD_ID, "reinforced_jade_iron"));

        //Human Tier Paxel
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HUMAN_GRADE_PAXEL.get())
                .define('S', ModItems.CRUDE_JADED_IRON.get())
                .define('I', Items.STICK)
                .pattern("SSS")
                .pattern(" I ")
                .pattern(" I ")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pWriter);

        // Coins <-> Shards
        nineToOne(pWriter,
                RecipeCategory.MISC, ModItems.DIVINE_CRYSTAL.get(), ModItems.DIVINE_COIN.get(), "divine/coins_to_shard");

        oneToNine(pWriter,
                RecipeCategory.MISC, ModItems.DIVINE_COIN.get(), ModItems.DIVINE_CRYSTAL.get(), "divine/shard_to_coins");

        nineToOne(pWriter,
                RecipeCategory.MISC, ModItems.SAINT_CRYSTAL.get(), ModItems.SAINT_COIN.get(), "saint/coins_to_shard");

        oneToNine(pWriter,
                RecipeCategory.MISC, ModItems.SAINT_COIN.get(), ModItems.SAINT_CRYSTAL.get(), "saint/shard_to_coins");

        nineToOne(pWriter,
                RecipeCategory.MISC, ModItems.PURPLE_CRYSTAL.get(), ModItems.PURPLE_COIN.get(), "purple/coins_to_shard");

        oneToNine(pWriter,
                RecipeCategory.MISC, ModItems.PURPLE_COIN.get(), ModItems.PURPLE_CRYSTAL.get(), "purple/shard_to_coins");

        nineToOne(pWriter,
                RecipeCategory.MISC, ModItems.SPIRIT_CRYSTAL.get(), ModItems.SPIRIT_COIN.get(), "spirit/coins_to_shard");

        oneToNine(pWriter,
                RecipeCategory.MISC, ModItems.SPIRIT_COIN.get(), ModItems.SPIRIT_CRYSTAL.get(), "spirit/shard_to_coins");

        nineToOne(pWriter,
                RecipeCategory.MISC, ModItems.JADE.get(), ModItems.JADE_COIN.get(), "jade/coins_to_shard");

        oneToNine(pWriter,
                RecipeCategory.MISC, ModItems.JADE_COIN.get(), ModItems.JADE.get(), "jade/shard_to_coins");

        // Shards <-> Block
        nineToOne(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JADE_BLOCK.get().asItem(), ModItems.JADE.get(),"jade/shards_to_block");

        oneToNine(pWriter, RecipeCategory.MISC, ModItems.JADE.get(), ModBlocks.JADE_BLOCK.get().asItem(), "jade/block_to_shards");

        nineToOne(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.INFUSED_JADE_BLOCK.get().asItem(), ModItems.INFUSED_JADE.get(),"jade_infused/shards_to_block");

        oneToNine(pWriter, RecipeCategory.MISC, ModItems.INFUSED_JADE.get(), ModBlocks.INFUSED_JADE_BLOCK.get().asItem(), "jade_infused/block_to_shards");

        nineToOne(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPIRIT_BLOCK.get().asItem(), ModItems.SPIRIT_CRYSTAL.get(),"spirit/shards_to_block");

        oneToNine(pWriter, RecipeCategory.MISC, ModItems.SPIRIT_CRYSTAL.get(), ModBlocks.SPIRIT_BLOCK.get().asItem(), "spirit/block_to_shards");

        nineToOne(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_BLOCK.get().asItem(), ModItems.PURPLE_CRYSTAL.get(),"purple/shards_to_block");

        oneToNine(pWriter, RecipeCategory.MISC, ModItems.PURPLE_CRYSTAL.get(), ModBlocks.PURPLE_BLOCK.get().asItem(), "purple/block_to_shards");

        nineToOne(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAINT_BLOCK.get().asItem(), ModItems.SAINT_CRYSTAL.get(),"saint/shards_to_block");

        oneToNine(pWriter, RecipeCategory.MISC, ModItems.SAINT_CRYSTAL.get(), ModBlocks.SAINT_BLOCK.get().asItem(), "saint/block_to_shards");

        nineToOne(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIVINE_BLOCK_BLANK.get().asItem(), ModItems.DIVINE_CRYSTAL.get(),"divine/shards_to_block");

        oneToNine(pWriter, RecipeCategory.MISC, ModItems.DIVINE_CRYSTAL.get(), ModBlocks.DIVINE_BLOCK_BLANK.get().asItem(), "divine/block_to_shards");


        //Infuser Recipes
        new HumanGradeInfuserRecipeBuilder(Items.IRON_INGOT, ModItems.CRUDE_JADED_IRON.get(), 1)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(pWriter);
        new HumanGradeInfuserRecipeBuilder(ModItems.JADE.get(), ModItems.INFUSED_JADE.get(), 1)
                .unlockedBy("has_jade", has(ModItems.JADE.get())).save(pWriter);
        new HumanGradeInfuserRecipeBuilder(ModBlocks.JADE_BLOCK.get(), ModBlocks.INFUSED_JADE_BLOCK.get(), 1)
                .unlockedBy("has_jade_block", has(ModBlocks.JADE_BLOCK.get())).save(pWriter);
        new HumanGradeInfuserRecipeBuilder(ModItems.BEAST_BLOOD.get(), ModItems.BEAST_OIL.get(), 1)
                .unlockedBy("has_beast_blood", has(ModItems.BEAST_BLOOD.get())).save(pWriter);

        // ===== Crude Jade Armor (uses material item) =====
        // Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HUMAN_GRADE_HELMET.get()).define('S', ModItems.CRUDE_JADED_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .save(pWriter);

        // Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HUMAN_GRADE_CHESTPLATE.get())
                .define('S', ModItems.CRUDE_JADED_IRON.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .save(pWriter);

        // Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HUMAN_GRADE_LEGGINGS.get())
                .define('S', ModItems.CRUDE_JADED_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .save(pWriter);

        // Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.HUMAN_GRADE_BOOTS.get())
                .define('S', ModItems.CRUDE_JADED_IRON.get())
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .save(pWriter);

        // ===== Crude Jade Sword =====
        // Vertical 1x3 pattern:
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LOWER_HUMAN_GRADE_SABER.get())
                .define('S', ModItems.CRUDE_JADED_IRON.get())
                .define('I', Items.STICK)
                .pattern("S")
                .pattern("S")
                .pattern("I")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pWriter);

        // ===== Crude Jade Shield =====
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.LOWER_HUMAN_GRADE_SHIELD.get())
                .define('S', ModItems.CRUDE_JADED_IRON.get())
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_crude_jaded_iron", has(ModItems.CRUDE_JADED_IRON.get()))
                .save(pWriter);

        // ===== Refined Jade Armor (uses material item) =====
        // Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MEDIUM_HUMAN_GRADE_HELMET.get())
                .define('S', ModItems.REFINED_JADE_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .save(pWriter);

        // Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MEDIUM_HUMAN_GRADE_CHESTPLATE.get())
                .define('S', ModItems.REFINED_JADE_IRON.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .save(pWriter);

        // Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MEDIUM_HUMAN_GRADE_LEGGINGS.get())
                .define('S', ModItems.REFINED_JADE_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .save(pWriter);

        // Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MEDIUM_HUMAN_GRADE_BOOTS.get())
                .define('S', ModItems.REFINED_JADE_IRON.get())
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .save(pWriter);

        // ===== Refined Jade Sword =====
        // Vertical 1x3 pattern:
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MEDIUM_HUMAN_GRADE_SABER.get())
                .define('S', ModItems.REFINED_JADE_IRON.get())
                .define('I', Items.STICK)
                .pattern("S")
                .pattern("S")
                .pattern("I")
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pWriter);

        // ===== Refined Jade Shield =====
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MEDIUM_HUMAN_GRADE_SHIELD.get())
                .define('S', ModItems.REFINED_JADE_IRON.get())
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_refined_jade_iron", has(ModItems.REFINED_JADE_IRON.get()))
                .save(pWriter);

        // ===== Reinforced Jade Armor (uses material item) =====
        // Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UPPER_HUMAN_GRADE_HELMET.get())
                .define('S', ModItems.REINFORCED_JADE_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .unlockedBy("has_reinforced_jade_iron", has(ModItems.REINFORCED_JADE_IRON.get()))
                .save(pWriter);

        // Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UPPER_HUMAN_GRADE_CHESTPLATE.get())
                .define('S', ModItems.REINFORCED_JADE_IRON.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .unlockedBy("has_reinforced_jade_iron", has(ModItems.REINFORCED_JADE_IRON.get()))
                .save(pWriter);

        // Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UPPER_HUMAN_GRADE_LEGGINGS.get())
                .define('S', ModItems.REINFORCED_JADE_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_reinforced_jade_iron", has(ModItems.REINFORCED_JADE_IRON.get()))
                .save(pWriter);

        // Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UPPER_HUMAN_GRADE_BOOTS.get())
                .define('S', ModItems.REINFORCED_JADE_IRON.get())
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_reinforced_jade_iron", has(ModItems.REINFORCED_JADE_IRON.get()))
                .save(pWriter);

        // ===== Reinforced Jade Sword =====
        // Vertical 1x3 pattern:
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UPPER_HUMAN_GRADE_SABER.get())
                .define('S', ModItems.REINFORCED_JADE_IRON.get())
                .define('I', Items.STICK)
                .pattern("S")
                .pattern("S")
                .pattern("I")
                .unlockedBy("has_reinforced_jade_iron", has(ModItems.REINFORCED_JADE_IRON.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pWriter);

        // ===== Reinforced Jade Shield =====
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UPPER_HUMAN_GRADE_SHIELD.get())
                .define('S', ModItems.REINFORCED_JADE_IRON.get())
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_reinforced_jade_iron", has(ModItems.REINFORCED_JADE_IRON.get()))
                .save(pWriter);

        // ===== Divine Iron Armor (uses material item) =====
        // Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIVINE_HUMAN_GRADE_HELMET.get())
                .define('S', ModItems.DIVINE_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .unlockedBy("has_divine_iron", has(ModItems.DIVINE_IRON.get()))
                .save(pWriter);

        // Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIVINE_HUMAN_GRADE_CHESTPLATE.get())
                .define('S', ModItems.DIVINE_IRON.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .unlockedBy("has_divine_iron", has(ModItems.DIVINE_IRON.get()))
                .save(pWriter);

        // Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIVINE_HUMAN_GRADE_LEGGINGS.get())
                .define('S', ModItems.DIVINE_IRON.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_divine_iron", has(ModItems.DIVINE_IRON.get()))
                .save(pWriter);

        // Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIVINE_HUMAN_GRADE_BOOTS.get())
                .define('S', ModItems.DIVINE_IRON.get())
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_divine_iron", has(ModItems.DIVINE_IRON.get()))
                .save(pWriter);

        // ===== Reinforced Jade Sword =====
        // Vertical 1x3 pattern:
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIVINE_HUMAN_GRADE_SABER.get())
                .define('S', ModItems.DIVINE_IRON.get())
                .define('I', Items.STICK)
                .pattern("S")
                .pattern("S")
                .pattern("I")
                .unlockedBy("has_divine_iron", has(ModItems.DIVINE_IRON.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(pWriter);

        // ===== Reinforced Jade Shield =====
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIVINE_HUMAN_GRADE_SHIELD.get())
                .define('S', ModItems.DIVINE_IRON.get())
                .pattern("SS")
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_divine_iron", has(ModItems.DIVINE_IRON.get()))
                .save(pWriter);



        // --- (Optional) If you use a TAG instead of the concrete ingot item ---
        // Ingredient sunSteel = Ingredient.of( /* TagKey<Item> */ );
        // Then swap .define('S', ModItems.SUN_STEEL_INGOT.get()) with .define('S', sunSteel);





    }

    /** 3×3 compression: 9 of 'ingredient' -> 1 'result' */
    private void nineToOne(Consumer<FinishedRecipe> pWriter,
                           RecipeCategory resultCategory, ItemLike result,
                           ItemLike ingredient,
                           String idPath) {
        ShapedRecipeBuilder.shaped(resultCategory, result)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter, new ResourceLocation(PathToImmortals.MOD_ID, idPath));
    }

    /** Decompression: 1 'ingredient' -> 9 'result' */
    private void oneToNine(Consumer<FinishedRecipe> pWriter,
                           RecipeCategory resultCategory, ItemLike result,
                           ItemLike ingredient,
                           String idPath) {
        ShapelessRecipeBuilder.shapeless(resultCategory, result, 9)
                .requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter, new ResourceLocation(PathToImmortals.MOD_ID, idPath));
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer).
                    group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, PathToImmortals.MOD_ID + ":" + getItemName (pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }


}
