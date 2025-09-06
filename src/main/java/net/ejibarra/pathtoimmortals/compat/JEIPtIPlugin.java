package net.ejibarra.pathtoimmortals.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.recipe.InfusionTableRecipe;
import net.ejibarra.pathtoimmortals.screen.InfusionTable1Screen;
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
        registration.addRecipeCategories(new InfusionTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<InfusionTableRecipe> infusionRecipes = recipeManager.getAllRecipesFor(InfusionTableRecipe.Type.INSTANCE);
        registration.addRecipes(InfusionTableRecipeCategory.INFUSION_TABLE_TYPE, infusionRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(InfusionTable1Screen.class, 80,30,20,30,
                InfusionTableRecipeCategory.INFUSION_TABLE_TYPE);
    }
}
