package net.ejibarra.pathtoimmortals.origin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.client.ClientEssenceHudState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, value = Dist.CLIENT)
public class OriginEssenceHUD {
    private static final ResourceLocation EMPTY_BAR =
            new ResourceLocation("pathtoimmortals", "textures/gui/origin_bar_empty.png");
    private static final ResourceLocation FULL_BAR =
            new ResourceLocation("pathtoimmortals", "textures/gui/origin_bar_full.png");

    // ⬅ back to your original visual size
    private static final int BAR_WIDTH = 200;
    private static final int BAR_HEIGHT = 32;

    // 🔮 Smoothed HUD value (client only)
    private static float displayedCurrent = -1f;

    @SubscribeEvent
    public static void onRenderHUD(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        GuiGraphics gui = event.getGuiGraphics();
        PoseStack pose = gui.pose();

        // 🔹 Grab capability
        OriginEssence essence = OriginEssenceCapability.OriginEssenceHelper.get(mc.player);
        if (essence == null) return;

        float trueCurrent = essence.getCurrent();
        float max         = Math.max(1, essence.getMax()); // avoid /0

        if (!ClientEssenceHudState.shouldRender((int) trueCurrent, (int) max)) return;

        // ==== Initialize smoothed value on first render or after big jumps ====
        if (displayedCurrent < 0f || displayedCurrent > max + 1f) {
            displayedCurrent = trueCurrent;
        }

        // ==== Smoothly approach the true current value ====
        float delta = mc.getDeltaFrameTime(); // seconds per frame
        float speedPerSecond;

        // You regen in steps of 10/sec, so:
        // - make spending feel snappy
        // - make regen a bit smoother
        if (trueCurrent > displayedCurrent) {
            // Regen → slower, smooth rise
            speedPerSecond = 10.0f;
        } else {
            // Spending → faster, quick drop
            speedPerSecond = 18.0f;
        }

        float alpha = Mth.clamp(speedPerSecond * delta, 0.0f, 1.0f);
        displayedCurrent = Mth.lerp(alpha, displayedCurrent, trueCurrent);

        // Value actually used for drawing
        float hudCurrent = displayedCurrent;
        float percent = hudCurrent / max; // bar fill based on smoothed current / max

        int screenWidth  = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // === Vanilla hotbar position (same as your old class) ===
        int hotbarX = screenWidth / 2 - 91;
        int hotbarY = screenHeight - 22;
        int margin = 4;

        int x = hotbarX + 182 + margin;
        int y = hotbarY + (22 - BAR_HEIGHT + 4);
        // If it would go off the right side, pin it to the right edge
        int maxX = screenWidth - BAR_WIDTH;
        if (x > maxX) x = maxX;

// Also keep it vertically safe
        int maxY = screenHeight - BAR_HEIGHT;
        if (y > maxY) y = maxY;
        if (y < 0) y = 0;

        RenderSystem.enableBlend();

        // Draw empty bar EXACTLY like before
        gui.blit(EMPTY_BAR, x, y, 0, 0,
                BAR_WIDTH, BAR_HEIGHT,
                BAR_WIDTH, BAR_HEIGHT);

        int filledWidth = (int)(BAR_WIDTH * Mth.clamp(percent, 0.0f, 1.0f));
        if (filledWidth > 0) {
            gui.blit(FULL_BAR, x, y, 0, 0,
                    filledWidth, BAR_HEIGHT,
                    BAR_WIDTH, BAR_HEIGHT);
        }
        RenderSystem.disableBlend();

        // === Centered text (smoothed essence) ===
        int shownCurrent = (int) (hudCurrent + 0.5f); // nice rounding
        int shownMax     = (int) max;
        String text = shownCurrent + " / " + shownMax;

        // === Essence text ABOVE the bar ===
        int textWidth = mc.font.width(text);

// Center text horizontally over the bar
        int textX = x + (BAR_WIDTH - textWidth) / 2;

// Place text just above the bar (2px gap)
        int textY = y - mc.font.lineHeight - 2;

// Gold-ish color
        int lightPurple = 0xD8A6FF; // warm gold use for divinity 0xFFD166

        gui.drawString(mc.font, text, textX, textY, lightPurple, false);
    }
}
