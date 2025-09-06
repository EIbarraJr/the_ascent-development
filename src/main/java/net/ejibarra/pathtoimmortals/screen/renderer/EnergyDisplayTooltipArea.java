package net.ejibarra.pathtoimmortals.screen.renderer;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  https://github.com/BluSunrize/ImmersiveEngineering/blob/1.19.2/LICENSE
 *
 *  Slightly Modified Version by: Kaupenjoe
 */

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.List;

public class EnergyDisplayTooltipArea {
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private final IEnergyStorage energy;

    public EnergyDisplayTooltipArea(int xMin, int yMin, IEnergyStorage energy)  {
        this(xMin, yMin, energy,8,64);
    }

    public EnergyDisplayTooltipArea(int xMin, int yMin, IEnergyStorage energy, int width, int height)  {
        xPos = xMin;
        yPos = yMin;
        this.width = width;
        this.height = height;
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.getEnergyStored()+" / "+energy.getMaxEnergyStored()+" SE"));
    }

    public void render(GuiGraphics guiGraphics) {
        int max = Math.max(1, energy.getMaxEnergyStored()); // avoid /0
        float ratio = Math.min(1.0F, Math.max(0.0F, energy.getEnergyStored() / (float) max));

        int filled = Math.max(1, Math.round(height * ratio)); // at least 1px so you can see it
        int x1 = xPos;
        int y1 = yPos + (height - filled); // top of the filled region
        int x2 = xPos + width;
        int y2 = yPos + height;            // bottom of the filled region

        // ARGB (alpha included) — full opacity
        int GOLD_TOP    = 0xFFF4c430; // lighter gold
        int GOLD_BOTTOM = 0xFF8B7500; // darker gold

        guiGraphics.fillGradient(x1, y1, x2, y2, GOLD_TOP, GOLD_BOTTOM);
    }
}
