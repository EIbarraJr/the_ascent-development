package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIVINE_BLOCK_BLANK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.DIVINE_CRYSTAL.get())
                .unlockedBy("has_divine_crystal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.DIVINE_CRYSTAL.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DIVINE_CRYSTAL.get(), 9)
                .requires(ModBlocks.DIVINE_BLOCK_BLANK.get())
                .unlockedBy("has_divine_block_blank", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.DIVINE_BLOCK_BLANK.get()).build()))
                .save(pWriter);





    }


}
