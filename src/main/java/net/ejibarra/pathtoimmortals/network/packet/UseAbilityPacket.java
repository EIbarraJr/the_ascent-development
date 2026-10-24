package net.ejibarra.pathtoimmortals.network.packet;

import net.ejibarra.pathtoimmortals.origin.OriginEssence;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.ejibarra.pathtoimmortals.server.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UseAbilityPacket {
    private final int abilityId;

    public UseAbilityPacket(AbilityType type) {
        this.abilityId = type.getId();
    }

    public UseAbilityPacket(FriendlyByteBuf buf) {
        this.abilityId = buf.readVarInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeVarInt(abilityId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            AbilityType type = AbilityType.fromId(abilityId);

            // 🔒 ability lock gate goes here (next section)

            OriginEssence cap = OriginEssenceCapability.OriginEssenceHelper.get(player);

            int tier = cap.getAbilityTier(type);

            if (tier < 1) {
                player.displayClientMessage(
                        Component.literal("Locked ability. Use the skill book to unlock it.")
                                .withStyle(ChatFormatting.RED),
                        true
                );
                player.level().playSound(null, player.blockPosition(),
                        SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 0.8f, 1.0f);
                return;
            }

            switch (type) {
                case ORIGIN_SWORD -> OriginSwordAbilityHandler.tryUseAbility(player);
                case PRESSURE_WAVE -> PressureWaveAbilityHandler.tryCast(player);
                case OVERCHARGED -> OverchargedAbilityHandler.tryActivate(player);
                case HEAL -> HealAbilityHandler.tryActivate(player);
                case REINFORCED -> ReinforcedAbilityHandler.tryToggle(player);
                case LEVITATION -> LevitationAbilityHandler.tryToggle(player);
                case CLOUD_STEP -> CloudStepAbilityHandler.tryActivate(player);
            }
        });
        context.setPacketHandled(true);
        return true;
    }
}
