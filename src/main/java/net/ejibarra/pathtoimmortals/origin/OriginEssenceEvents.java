package net.ejibarra.pathtoimmortals.origin;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID)
public class OriginEssenceEvents {
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();

        event.getOriginal().getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(oldCap ->
                event.getEntity().getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(newCap -> {
                    newCap.copyFrom(oldCap);

                    if (event.getEntity() instanceof ServerPlayer player) {
                        ModMessages.syncOriginEssence(player, newCap);
                    }
                })
        );

        event.getOriginal().invalidateCaps();
    }
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(cap -> {
            ModMessages.syncOriginEssence(player, cap);
        });
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(cap -> {
            ModMessages.syncOriginEssence(player, cap);
        });
    }

}
