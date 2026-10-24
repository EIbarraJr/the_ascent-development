package net.ejibarra.pathtoimmortals.network.packet;

import net.ejibarra.pathtoimmortals.client.OverchargedParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2C_OverchargedBurstPacket {
    private final int entityId;

    public S2C_OverchargedBurstPacket(int entityId) {
        this.entityId = entityId;
    }

    public S2C_OverchargedBurstPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level == null) return;

            Entity e = mc.level.getEntity(entityId);
            if (!(e instanceof Player p)) return;

            OverchargedParticles.spawn(mc.level, p); // client-side velocity particles
        });
        context.setPacketHandled(true);
        return true;
    }
}
