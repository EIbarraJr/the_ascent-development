package net.ejibarra.pathtoimmortals.client;

import net.minecraft.client.Minecraft;

public class ClientEssenceHudState {
    private ClientEssenceHudState() {}

    private static int lastCurrent = -1;
    private static int lastMax = -1;
    private static long lastChangeGameTime = 0L;

    // how long to keep HUD visible after last change (in ticks)
    private static final int SHOW_AFTER_CHANGE_TICKS = 20 * 2; // 2 seconds

    /** Call whenever the client receives updated essence values. */
    public static void onEssenceUpdate(int current, int max) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        if (current != lastCurrent || max != lastMax) {
            lastCurrent = current;
            lastMax = max;
            lastChangeGameTime = mc.level.getGameTime();
        }
    }

    /** HUD visibility rule */
    public static boolean shouldRender(int current, int max) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return true;

        // Always show if not full (regen/in use)
        if (current < max) return true;

        // If full, show only briefly after last change
        long now = mc.level.getGameTime();
        return (now - lastChangeGameTime) <= SHOW_AFTER_CHANGE_TICKS;
    }
}
