package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemProperties {
    public static void addCustomItemProperties(){
        ItemProperties.register(ModItems.LOWER_HUMAN_GRADE_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, level, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ModItems.MEDIUM_HUMAN_GRADE_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, level, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ModItems.UPPER_HUMAN_GRADE_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, level, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
        ItemProperties.register(ModItems.DIVINE_HUMAN_GRADE_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, level, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });

    }


}
