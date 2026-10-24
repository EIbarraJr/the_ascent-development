package net.ejibarra.pathtoimmortals.network.packet;

import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceSources;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2S_ConvertInfusedPacket {
    private final boolean wholeStack;

    // client → create with flag
    public C2S_ConvertInfusedPacket(boolean wholeStack) {
        this.wholeStack = wholeStack;
    }

    // decode
    public C2S_ConvertInfusedPacket(FriendlyByteBuf buf) {
        this.wholeStack = buf.readBoolean();
    }

    // encode
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(wholeStack);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            ItemStack stack = player.getMainHandItem();
            if (stack.isEmpty()) return;

            Item item = stack.getItem();

            int valuePer = OriginEssenceSources.getValue(item);
            if (valuePer <= 0) return;

            // 🔥 NEW: per-item ceiling (jade=2500, purple=5000, etc.)
            int capCeiling = OriginEssenceSources.getCap(item);
            if (capCeiling <= 0) return;

            int requestedCount = wholeStack ? stack.getCount() : 1;
            if (requestedCount <= 0) return;

            player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE).ifPresent(cap -> {
                // How much room is left until THIS item's ceiling?
                int room = capCeiling - cap.getMax();
                if (room <= 0) {
                    // Already at/above this item's ceiling -> do not consume anything
                    return;
                }

                // Total we *could* add if we consumed requestedCount
                int requestedTotal = valuePer * requestedCount;

                // Total we are actually allowed to add
                int actualAdd = Math.min(room, requestedTotal);
                if (actualAdd <= 0) return;

                // Consume only as many items as needed to produce actualAdd
                int itemsToConsume = (actualAdd + valuePer - 1) / valuePer; // ceil div
                if (itemsToConsume <= 0) return;

                // Safety clamp
                itemsToConsume = Math.min(itemsToConsume, requestedCount);

                // Recompute actualAdd based on consumed items (and room)
                actualAdd = Math.min(room, itemsToConsume * valuePer);
                if (actualAdd <= 0) return;

                // shrink stack (only what we actually used)
                stack.shrink(itemsToConsume);

                // update capability (your existing methods)
                cap.increaseMax(actualAdd);
                cap.addEssence(actualAdd);

                ModMessages.syncOriginEssence(player, cap);
            });
        });
        return true;
    }
}
