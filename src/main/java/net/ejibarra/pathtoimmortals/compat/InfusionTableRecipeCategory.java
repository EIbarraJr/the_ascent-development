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
import net.ejibarra.pathtoimmortals.recipe.InfusionTableRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class InfusionTableRecipeCategory implements IRecipeCategory<InfusionTableRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PathToImmortals.MOD_ID, "infusion");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PathToImmortals.MOD_ID,
            "textures/gui/infusion_table1_gui.png");

    public static final RecipeType<InfusionTableRecipe> INFUSION_TABLE_TYPE =
            new RecipeType<>(UID, InfusionTableRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public InfusionTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0,0,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.INFUSTION_TABLE_T1.get()));
    }

    @Override
    public RecipeType<InfusionTableRecipe> getRecipeType() {
        return INFUSION_TABLE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Infusion Table");
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
    public void setRecipe(IRecipeLayoutBuilder builder, InfusionTableRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 80,10).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80,59).addItemStack(recipe.getResultItem(null));

    }
}
