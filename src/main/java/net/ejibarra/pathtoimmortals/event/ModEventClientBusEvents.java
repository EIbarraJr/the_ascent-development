package net.ejibarra.pathtoimmortals.event;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.custom.entity.ModBlockEntities;
import net.ejibarra.pathtoimmortals.block.custom.entity.renderer.InfusionBlock1EntityRenderer;
import net.ejibarra.pathtoimmortals.entity.client.DarklinModel;
import net.ejibarra.pathtoimmortals.entity.client.HereticModel;
import net.ejibarra.pathtoimmortals.entity.client.HoodedSpikedRatModel;
import net.ejibarra.pathtoimmortals.entity.client.SpikedRatModel;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.INFUSION_TABLE_BLOCK1.get(), InfusionBlock1EntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.HERETIC_LAYER, HereticModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SPIKEDRAT_LAYER, SpikedRatModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DARKLIN_LAYER, DarklinModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HOODEDRAT_LAYER, HoodedSpikedRatModel::createBodyLayer);
    }


    //To Read Armor and Health and Display it
    @Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registrar {
        @net.minecraftforge.eventbus.api.SubscribeEvent
        public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerBelow(VanillaGuiOverlay.PLAYER_HEALTH.id(), "hud_numbers", (gui, gg, partialTick, screenW, screenH) -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.options.hideGui) return;
                Player p = mc.player;
                if (p == null) return;

                Font font = mc.font;

                // ===== PLAYER READOUT =====
                float currentHealth = p.getHealth();
                float absorption = p.getAbsorptionAmount();
                double maxHealth = p.getAttributeValue(Attributes.MAX_HEALTH);
                int hpNow = Mth.floor(currentHealth);
                int hpMax = (int) Math.ceil(maxHealth);
                int hpAbs = Mth.floor(absorption);

                double rawArmor = p.getAttributeValue(Attributes.ARMOR);          // can exceed 20
                double armorTough = p.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
                int vanillaIcons = p.getArmorValue();

                String hpText = "HP: " + hpNow + " / " + hpMax + (hpAbs > 0 ? " (+" + hpAbs + ")" : "");
                String armorText = "Armor: " + trim1(rawArmor) + " (tough: " + trim1(armorTough) + ", icons: " + vanillaIcons + ")";

                int x = 10;
                int baseY = screenH - 52;
                int line = 12;

                gg.drawString(font, hpText, x, baseY, 0xFFFFFFFF, true);
                gg.drawString(font, armorText, x, baseY + line, 0xFFFFFFFF, true);

                // ===== TARGETED ENTITY READOUT (under crosshair) =====
                HitResult hr = mc.hitResult;
                if (hr != null && hr.getType() == HitResult.Type.ENTITY) {
                    var ehr = (EntityHitResult) hr;
                    if (ehr.getEntity() instanceof LivingEntity le) {
                        // Basic values
                        float eNow = le.getHealth();
                        float eAbs = le.getAbsorptionAmount(); // can be 0 for most mobs
                        double eMax = le.getAttributeValue(Attributes.MAX_HEALTH);
                        double eArmor = le.getAttributeValue(Attributes.ARMOR);
                        double eTough = le.getAttributeValue(Attributes.ARMOR_TOUGHNESS);

                        String name = le.getDisplayName().getString();
                        String eHp = "HP: " + Mth.floor(eNow) + " / " + (int) Math.ceil(eMax) + (eAbs > 0 ? " (+" + Mth.floor(eAbs) + ")" : "");
                        String eAr = "Armor: " + trim1(eArmor) + " (tough: " + trim1(eTough) + ")";

                        // Position: top-left, below debug line; adjust to taste
                        int ex = 10;
                        int ey = 10;
                        gg.drawString(font, "Target: " + name, ex, ey, 0xFFFFFFAA, true);
                        gg.drawString(font, eHp, ex, ey + line, 0xFFFFFFFF, true);
                        gg.drawString(font, eAr, ex, ey + line * 2, 0xFFFFFFFF, true);
                    }
                }
            });
        }
    }

    private static String trim1(double d) {
        return String.format(java.util.Locale.ROOT, "%.1f", d);
    }
}
