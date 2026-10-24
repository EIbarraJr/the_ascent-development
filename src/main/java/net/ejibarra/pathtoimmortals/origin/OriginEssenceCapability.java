package net.ejibarra.pathtoimmortals.origin;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID)
public class OriginEssenceCapability {
    public static final ResourceLocation ID =
            new ResourceLocation(PathToImmortals.MOD_ID, "origin_essence");

    public static final Capability<OriginEssence> ORIGIN_ESSENCE =
            CapabilityManager.get(new CapabilityToken<>() {});

    // Provider for players
    public static class Provider implements ICapabilitySerializable<CompoundTag> {

        private final OriginEssence backend = new OriginEssence();
        private final LazyOptional<OriginEssence> optional = LazyOptional.of(() -> backend);

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == ORIGIN_ESSENCE ? optional.cast() : LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            return backend.saveNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            backend.loadNBT(nbt);
        }
    }

    public static class OriginEssenceHelper {

        public static OriginEssence get(Player player) {
            return player.getCapability(OriginEssenceCapability.ORIGIN_ESSENCE)
                    .orElseThrow(() -> new IllegalStateException("Origin Essence capability missing on player!"));
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(ID, new Provider());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        // Once per second
        if (player.tickCount % 20 != 0) return;

        OriginEssence essence = OriginEssenceHelper.get(player);

        int current = essence.getCurrent();
        int max = essence.getMax();
        if (max <= 0) return;

        // ---- Base regen ----
        int baseRegen = 10;

        // ---- Bonus regen from "pill" buff (using vanilla LUCK as placeholder) ----
        int bonusRegen = 0;

        MobEffectInstance luck = player.getEffect(MobEffects.LUCK);
        if (luck != null) {
            int amplifier = luck.getAmplifier(); // 0,1,2,...

            // 5% per level: 0 -> 5%, 1 -> 10%, 2 -> 15%, ...
            float percentPerLevel = 0.10f;
            float bonusPercent = percentPerLevel * (amplifier + 1);

            // important: this is based ONLY on max, not including the base 10.
            bonusRegen = (int) (max * bonusPercent);
        }

        int totalRegen = baseRegen + bonusRegen;

        if (current < max && totalRegen > 0) {
            essence.setCurrent(current + totalRegen);
            // your setCurrent already clamps and updates totalEarned, etc.
            if (player instanceof ServerPlayer sp) {
                System.out.println("[OriginEssence] " + sp.getName().getString()
                        + " essence = " + essence.getCurrent() + "/" + essence.getMax());
            }
            if (player instanceof ServerPlayer serverPlayer) {
                ModMessages.sendToPlayer( // <- use your actual helper / channel
                        new S2C_SyncOriginEssencePacket(
                                essence.getCurrent(),
                                essence.getMax(), essence.getUnlockedMask(), essence.getAbilityTiersCopy()
                        ),
                        serverPlayer
                );
            }

        }
    }
}
