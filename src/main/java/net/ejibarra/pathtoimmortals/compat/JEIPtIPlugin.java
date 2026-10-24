package net.ejibarra.pathtoimmortals.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.recipe.HumanGradeInfuserRecipe;
import net.ejibarra.pathtoimmortals.screen.HumanGradeInfuserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIPtIPlugin implements IModPlugin {


    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(PathToImmortals.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new HumanGradeInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<HumanGradeInfuserRecipe> humanGradeInfuserRecipes = recipeManager.getAllRecipesFor(HumanGradeInfuserRecipe.Type.INSTANCE);
        registration.addRecipes(HumanGradeInfuserRecipeCategory.HUMAN_GRADE_INFUSER_RECIPE_TYPE, humanGradeInfuserRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(HumanGradeInfuserScreen.class, 80,30,20,30,
                HumanGradeInfuserRecipeCategory.HUMAN_GRADE_INFUSER_RECIPE_TYPE);
    }
}
