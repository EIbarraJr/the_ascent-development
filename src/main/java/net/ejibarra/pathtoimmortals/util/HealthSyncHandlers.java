package net.ejibarra.pathtoimmortals.util;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID) // <-- runs in SP + dedicated, client + server environments
public class HealthSyncHandlers {
    private static final String MOD_TAG = PathToImmortals.MOD_ID;
    private static final String KEY_HEALTH_FRAC = "SavedHealthFraction";
    private static final String KEY_NEEDS_RESTORE = "NeedsHealthRestore";

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
        Player p = e.getEntity();
        if (p.level().isClientSide) return; // mutate only on server
        saveHealthFraction(p);
        setNeedsRestore(p, true);
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone e) {
        // This fires on both physical sides; only touch data (NBT copy is fine on either)
        CompoundTag oldData = e.getOriginal().getPersistentData();
        CompoundTag newData = e.getEntity().getPersistentData();
        if (oldData.contains(MOD_TAG)) {
            newData.put(MOD_TAG, oldData.getCompound(MOD_TAG).copy());
        }
        // After death we typically don't restore previous fraction
        setNeedsRestore(e.getEntity(), false);
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent e) {
        Player p = e.getEntity();
        if (p.level().isClientSide) return;
        if (!getModData(p).contains(KEY_HEALTH_FRAC)) {
            saveHealthFraction(p);
        }
        setNeedsRestore(p, true);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END) return;
        Player p = e.player;
        if (p.level().isClientSide) return; // only adjust health on server

        CompoundTag data = getModData(p);
        if (!data.getBoolean(KEY_NEEDS_RESTORE)) return;

        double max = p.getAttribute(Attributes.MAX_HEALTH) != null
                ? p.getAttribute(Attributes.MAX_HEALTH).getValue()
                : p.getMaxHealth();

        if (max > 0.0) {
            float fraction = clamp01(data.getFloat(KEY_HEALTH_FRAC));
            float newHealth = (float) Math.max(1.0, fraction * max);
            p.setHealth(Math.min(newHealth, (float) max));
            data.putBoolean(KEY_NEEDS_RESTORE, false);
        } else {
            data.putBoolean(KEY_NEEDS_RESTORE, true);
        }
    }

    // ---- helpers ----
    private static void saveHealthFraction(Player p) {
        double max = p.getMaxHealth();
        float frac = (max > 0.0) ? (p.getHealth() / (float) max) : 1.0f;
        getModData(p).putFloat(KEY_HEALTH_FRAC, clamp01(frac));
    }

    private static void setNeedsRestore(Player p, boolean value) {
        getModData(p).putBoolean(KEY_NEEDS_RESTORE, value);
    }

    private static CompoundTag getModData(Player p) {
        CompoundTag root = p.getPersistentData();
        if (!root.contains(MOD_TAG)) {
            root.put(MOD_TAG, new CompoundTag());
        }
        return root.getCompound(MOD_TAG);
    }

    private static float clamp01(float v) {
        return v < 0f ? 0f : (v > 1f ? 1f : v);
    }
}

