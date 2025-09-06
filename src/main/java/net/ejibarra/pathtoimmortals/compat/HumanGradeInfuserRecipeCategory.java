package net.ejibarra.pathtoimmortals.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.recipe.HumanGradeInfuserRecipe;
import net.ejibarra.pathtoimmortals.recipe.InfusionTableRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class HumanGradeInfuserRecipeCategory implements IRecipeCategory<HumanGradeInfuserRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PathToImmortals.MOD_ID, "human_grade_infusion");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PathToImmortals.MOD_ID,
            "textures/gui/human_grade_infuser_gui.png");

    public static final RecipeType<HumanGradeInfuserRecipe> HUMAN_GRADE_INFUSER_RECIPE_TYPE =
            new RecipeType<>(UID, HumanGradeInfuserRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public HumanGradeInfuserRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0,0,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get()));
    }

    @Override
    public RecipeType<HumanGradeInfuserRecipe> getRecipeType() {
        return HUMAN_GRADE_INFUSER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Human Grade Infuser");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, HumanGradeInfuserRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 80,11).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80,59).addItemStack(recipe.getResultItem(null));

    }
}
