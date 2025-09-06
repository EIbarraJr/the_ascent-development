package net.ejibarra.pathtoimmortals.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class HumanGradeInfuserRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public HumanGradeInfuserRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputItems) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide) {
            return false;
        }
        return inputItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return HumanGradeInfuserRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return HumanGradeInfuserRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<HumanGradeInfuserRecipe> {
        private Type() {
        }

        public static final HumanGradeInfuserRecipe.Type INSTANCE = new HumanGradeInfuserRecipe.Type();
        public static final String ID = "human_grade_infusion";
    }

    public static class Serializer implements RecipeSerializer<HumanGradeInfuserRecipe> {

        public static final HumanGradeInfuserRecipe.Serializer INSTANCE = new HumanGradeInfuserRecipe.Serializer();

        public static final ResourceLocation ID =
                new ResourceLocation(PathToImmortals.MOD_ID, "human_grade_infusion");

        @Override
        public HumanGradeInfuserRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);//size would be asking for how many inputs

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new HumanGradeInfuserRecipe(id, output, inputs);
        }

        @Override
        public HumanGradeInfuserRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new HumanGradeInfuserRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, HumanGradeInfuserRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}
