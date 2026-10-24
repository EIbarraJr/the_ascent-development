package net.ejibarra.pathtoimmortals.network.packet;

import net.ejibarra.pathtoimmortals.client.ClientEssenceHudState;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2C_SyncOriginEssencePacket {
    private final int current;
    private final int max;
    private final int unlockedMask;
    private final int[] abilityTiers;

    public S2C_SyncOriginEssencePacket(int current, int max, int unlockedMask, int[] abilityTiers) {
        this.current = current;
        this.max = max;
        this.unlockedMask = unlockedMask;
        this.abilityTiers = abilityTiers.clone();
    }

    public S2C_SyncOriginEssencePacket(FriendlyByteBuf buf) {
        this.current = buf.readInt();
        this.max = buf.readInt();
        this.unlockedMask = buf.readInt();
        this.abilityTiers = buf.readVarIntArray();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(current);
        buf.writeInt(max);
        buf.writeInt(unlockedMask);
        buf.writeVarIntArray(abilityTiers);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(cap -> {
                cap.setMax(max);
                cap.setCurrent(current);
                cap.setUnlockedMask(unlockedMask);
                cap.setAbilityTiers(abilityTiers);

                ClientEssenceHudState.onEssenceUpdate(current, max);
            });
        });
        context.setPacketHandled(true);
        return true;
    }
}
