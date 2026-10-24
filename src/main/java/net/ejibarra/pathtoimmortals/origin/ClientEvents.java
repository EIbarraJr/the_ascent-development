package net.ejibarra.pathtoimmortals.origin;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.client.ModKeyBindings;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.C2S_ConvertInfusedPacket;
import net.ejibarra.pathtoimmortals.network.packet.UseAbilityPacket;
import net.ejibarra.pathtoimmortals.screen.AbilityWheelScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        // Only run at END phase to avoid double-firing
        if (event.phase != TickEvent.ClientTickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.player == null) return;
        if (ModKeyBindings.CONSUME_INFUSED_KEY == null) return;
        if (ModKeyBindings.ORIGIN_SWORD_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.ORIGIN_SWORD));
        }
        if (ModKeyBindings.PRESSURE_WAVE_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.PRESSURE_WAVE));
        }
        if (ModKeyBindings.OVERCHARGED_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.OVERCHARGED));
        }
        if (ModKeyBindings.HEAL_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.HEAL));
        }
        if (ModKeyBindings.REINFORCED_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.REINFORCED));
        }
        if (ModKeyBindings.LEVITATION_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.LEVITATION));
        }
        if (ModKeyBindings.CLOUD_STEP_ABILITY.consumeClick()) {
            ModMessages.sendToServer(new UseAbilityPacket(AbilityType.CLOUD_STEP));
        }
        if (ModKeyBindings.ABILITY_WHEEL.consumeClick()) {
            Minecraft.getInstance().setScreen(new AbilityWheelScreen());
        }

        while (ModKeyBindings.CONSUME_INFUSED_KEY.consumeClick()) {
            boolean wholeStack = mc.player.isShiftKeyDown();  // <-- detect Shift
            ModMessages.sendToServer(new C2S_ConvertInfusedPacket(wholeStack));
        }
    }
}
