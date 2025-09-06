package net.ejibarra.pathtoimmortals.recipe;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PathToImmortals.MOD_ID);

    public static final RegistryObject<RecipeSerializer<InfusionTableRecipe>> INFUSION_SERIALIZER =
            SERIALIZERS.register("infusion", ()-> InfusionTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
