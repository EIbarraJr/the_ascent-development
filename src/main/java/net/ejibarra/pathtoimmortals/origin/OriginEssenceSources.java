package net.ejibarra.pathtoimmortals.origin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.world.item.Item;

public class OriginEssenceSources {
    private static final Object2IntMap<Item> VALUES = new Object2IntOpenHashMap<>();
    private static final Object2IntMap<Item> CAPS = new Object2IntOpenHashMap<>();

    public static void registerDefaults() {
        // Jade tier (cap 2500)
        register(ModItems.INFUSED_JADE.get(), 1, OriginEssenceCaps.JADE_CAP);
        register(ModBlocks.INFUSED_JADE_BLOCK.get().asItem(), 10, OriginEssenceCaps.JADE_CAP);

        // Purple tier (cap 5000) - example item names, replace with yours
        // register(ModItems.PURPLE_CRYSTAL.get(), 25, OriginEssenceCaps.PURPLE_CAP);

        // Saint tier (cap 10000)
        // register(ModItems.SAINT_CRYSTAL.get(), 75, OriginEssenceCaps.SAINT_CAP);

        // Divine tier (cap 20000)
        // register(ModItems.DIVINE_CRYSTAL.get(), 150, OriginEssenceCaps.DIVINE_CAP);
    }

    public static void register(Item item, int value, int cap) {
        VALUES.put(item, Math.max(0, value));
        CAPS.put(item, Math.max(0, cap));
    }

    public static int getValue(Item item) {
        return VALUES.getOrDefault(item, 0);
    }

    public static int getCap(Item item) {
        return CAPS.getOrDefault(item, 0);
    }
}
