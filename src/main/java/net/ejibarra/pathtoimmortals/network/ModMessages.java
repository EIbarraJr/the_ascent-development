package net.ejibarra.pathtoimmortals.network;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.network.packet.C2S_ConvertInfusedPacket;
import net.ejibarra.pathtoimmortals.network.packet.S2C_OverchargedBurstPacket;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.network.packet.UseAbilityPacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssence;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(PathToImmortals.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int id() { return packetId++; }

    public static void register() {
        CHANNEL.registerMessage(id(), C2S_ConvertInfusedPacket.class,
                C2S_ConvertInfusedPacket::toBytes,
                C2S_ConvertInfusedPacket::new,
                C2S_ConvertInfusedPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));

        CHANNEL.registerMessage(id(), S2C_SyncOriginEssencePacket.class,
                S2C_SyncOriginEssencePacket::toBytes,
                S2C_SyncOriginEssencePacket::new,
                S2C_SyncOriginEssencePacket::handle);

        CHANNEL.registerMessage(id(), UseAbilityPacket.class,
                UseAbilityPacket::toBytes,
                UseAbilityPacket::new,
                UseAbilityPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));
        CHANNEL.registerMessage(id(), S2C_OverchargedBurstPacket.class,
                S2C_OverchargedBurstPacket::toBytes,
                S2C_OverchargedBurstPacket::new,
                S2C_OverchargedBurstPacket::handle);
    }

    public static <MSG> void sendToServer(MSG message) {
        CHANNEL.sendToServer(message);
    }

    public static void sendToPlayer(Object msg, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void syncOriginEssence(ServerPlayer player, OriginEssence cap) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                new S2C_SyncOriginEssencePacket(
                        cap.getCurrent(),
                        cap.getMax(),
                        cap.getUnlockedMask(),
                        cap.getAbilityTiersCopy()
                ));
    }
    public static void sendToTrackingAndSelf(Object msg, Entity player) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), msg);
    }
}
