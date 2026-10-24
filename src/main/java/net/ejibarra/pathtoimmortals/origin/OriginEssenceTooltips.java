package net.ejibarra.pathtoimmortals.origin;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OriginEssenceTooltips {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;

        int value = OriginEssenceSources.getValue(stack.getItem());
        if (value <= 0) return; // only show for items that give essence

        // Light purple color
        int lightPurpleRgb = 0xD8A6FF;

        MutableComponent line = Component.literal("+" + value + " Origin Essence")
                .withStyle(style -> style
                        .withColor(TextColor.fromRgb(lightPurpleRgb))
                        .withItalic(true)); // italic is optional, just looks nice

        // Add near the top or bottom. This adds at the end of the tooltip:
        event.getToolTip().add(line);

        // If you want it directly under the name instead:
        // event.getToolTip().add(1, line);
    }
}
