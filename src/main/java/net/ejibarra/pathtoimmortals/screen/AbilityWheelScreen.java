package net.ejibarra.pathtoimmortals.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.client.AbilityWheelInfo;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.UseAbilityPacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;

import java.util.List;

public class AbilityWheelScreen extends Screen {
    // Wheel layout
    private static final int RADIUS = 62;
    private static final int ICON_SIZE = 16;
    private static final int CENTER_DOT = 2;

    // You can reorder this list however you want
    private static final List<AbilityEntry> ENTRIES = List.of(
            new AbilityEntry(AbilityType.ORIGIN_SWORD, "Origin Sword",
                    rl("textures/gui/ability_wheel/origin_sword_icon.png")),
            new AbilityEntry(AbilityType.PRESSURE_WAVE, "Pressure Wave",
                    rl("textures/gui/ability_wheel/pressure_wave.png")),
            new AbilityEntry(AbilityType.OVERCHARGED, "Overcharged",
                    rl("textures/gui/ability_wheel/overcharged_icon.png")),
            new AbilityEntry(AbilityType.HEAL, "Heal",
                    rl("textures/gui/ability_wheel/heal_icon.png")),
            new AbilityEntry(AbilityType.REINFORCED, "Reinforced",
                    rl("textures/gui/ability_wheel/reinforced_icon.png")),
            new AbilityEntry(AbilityType.LEVITATION, "Levitation",
                    rl("textures/gui/ability_wheel/levitation_icon.png")),
            new AbilityEntry(AbilityType.CLOUD_STEP, "Cloud Step",
                    rl("textures/gui/ability_wheel/cloud_step_icon.png"))
    );

    private int hoveredIndex = -1;

    public AbilityWheelScreen() {
        super(Component.literal("Ability Wheel"));
    }

    private static ResourceLocation rl(String path) {
        return new ResourceLocation(PathToImmortals.MOD_ID, path);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();
        // makes mouse visible & stops camera turning
        Minecraft.getInstance().mouseHandler.releaseMouse();
    }

    @Override
    public void removed() {
        super.removed();
        // re-grab mouse for gameplay
        Minecraft.getInstance().mouseHandler.grabMouse();
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        // darken background slightly
        gui.fill(0, 0, this.width, this.height, 0x66000000);

        int cx = this.width / 2;
        int cy = this.height / 2;

        hoveredIndex = pickHoveredIndex(cx, cy, mouseX, mouseY);

        // draw center dot
        gui.fill(cx - CENTER_DOT, cy - CENTER_DOT, cx + CENTER_DOT, cy + CENTER_DOT, 0xFFFFFFFF);

        // draw icons in a circle
        int n = ENTRIES.size();
        for (int i = 0; i < n; i++) {
            float angle = (float) ((Math.PI * 2.0) * i / n - Math.PI / 2.0); // start at top
            int ix = cx + (int) (Math.cos(angle) * RADIUS) - ICON_SIZE / 2;
            int iy = cy + (int) (Math.sin(angle) * RADIUS) - ICON_SIZE / 2;

            // highlight hovered with a soft box
            if (i == hoveredIndex) {
                gui.fill(ix - 3, iy - 3, ix + ICON_SIZE + 3, iy + ICON_SIZE + 3, 0xAAFFFFFF);
                gui.fill(ix - 2, iy - 2, ix + ICON_SIZE + 2, iy + ICON_SIZE + 2, 0x99000000);
            }

            AbilityEntry entry = ENTRIES.get(i);
            int tier = getTier(entry.type());
            boolean unlocked = tier >= 1;

            // draw icon
            RenderSystem.enableBlend();
            if (!unlocked) {
                // tint/alpha: dim it
                RenderSystem.setShaderColor(1f, 1f, 1f, 0.25f);
            } else {
                RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            }

            gui.blit(entry.icon(), ix, iy, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
            if (tier > 0) {
                String t = String.valueOf(tier);
                gui.drawString(this.font, t, ix + ICON_SIZE - 6, iy + ICON_SIZE - 9, 0xFFFFFF, true);
            } else {
                // Optional "X" marker for locked
                gui.drawString(this.font, "X", ix + ICON_SIZE - 6, iy + ICON_SIZE - 9, 0xFF5555, true);
            }

// reset color so later stuff isn't affected
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        }

        // show hovered name
        if (hoveredIndex >= 0) {
            AbilityEntry hovered = ENTRIES.get(hoveredIndex);
            int tier = getTier(hovered.type());

            var tooltip = AbilityWheelInfo.tooltip(hovered.type(), hovered.name(), tier);

            // Render near bottom text, slightly above it (or near mouse if you prefer)
            int tipX = cx - 70;
            int tipY = cy + RADIUS + 6;

            gui.renderComponentTooltip(this.font, tooltip, tipX, tipY);

            String name = hovered.name();
            if (tier <= 0) {
                name = name + " (Locked)";
            } else {
                name = name + " (Tier " + tier + ")";
            }
            int w = this.font.width(name);
            gui.drawString(this.font, name, cx - w / 2, cy + RADIUS + 18, 0xFFFFFF, true);
        } else {
            String hint = "Move mouse to select";
            int w = this.font.width(hint);
            gui.drawString(this.font, hint, cx - w / 2, cy + RADIUS + 18, 0xDDDDDD, true);
        }

        super.render(gui, mouseX, mouseY, partialTick);
    }

    // Mouse release selects, right click cancels
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 1) { // right click cancel
            this.onClose();
            return true;
        }

        if (button == 0 && hoveredIndex >= 0) {
            AbilityEntry entry = ENTRIES.get(hoveredIndex);

            int tier = getTier(entry.type());
            if (tier >= 1) {
                ModMessages.sendToServer(new UseAbilityPacket(entry.type()));
                this.onClose();
                return true;
            } else {
                Minecraft.getInstance().player.playSound(SoundEvents.VILLAGER_NO, 0.8f, 1.0f);
                return true;
            }
        }

        this.onClose();
        return true;
    }

    // ESC closes
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private int pickHoveredIndex(int cx, int cy, int mouseX, int mouseY) {
        double dx = mouseX - cx;
        double dy = mouseY - cy;
        double dist = Math.sqrt(dx * dx + dy * dy);

        // must be "out of center" to count as selection
        if (dist < 18) return -1;

        // angle where 0 is up
        double angle = Math.atan2(dy, dx) + Math.PI / 2.0;
        if (angle < 0) angle += Math.PI * 2.0;

        int n = ENTRIES.size();
        int idx = (int) Math.floor(angle / (Math.PI * 2.0) * n);

        return Mth.clamp(idx, 0, n - 1);
    }

    private int getTier(AbilityType type) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return 0;

        return mc.player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE)
                .map(cap -> cap.getAbilityTier(type)) // <- you add this in OriginEssence
                .orElse(0);
    }

    private boolean isUnlocked(AbilityType type) {
        return getTier(type) >= 1;
    }

    private record AbilityEntry(AbilityType type, String name, ResourceLocation icon) {}
}
