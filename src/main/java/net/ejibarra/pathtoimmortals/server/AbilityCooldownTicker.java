package net.ejibarra.pathtoimmortals.server;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AbilityCooldownTicker {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide) return;

        CompoundTag tag = event.player.getPersistentData();
        int cd = tag.getInt(OriginSwordAbilityHandler.NBT_COOLDOWN);

        //=================Origin Sword Cooldown=================
        if (cd > 0) {
            tag.putInt(OriginSwordAbilityHandler.NBT_COOLDOWN, cd - 1);
        }

        //==================Reinforced Tick======================
        if (event.player instanceof ServerPlayer serverPlayer){
            ReinforcedAbilityHandler.serverTick(serverPlayer);
            LevitationAbilityHandler.serverTick(serverPlayer);
        }

    }
}
