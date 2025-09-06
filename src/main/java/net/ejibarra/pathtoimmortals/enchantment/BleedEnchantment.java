package net.ejibarra.pathtoimmortals.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BleedEnchantment extends Enchantment {
    // Optional: pull from config later
    private static final int MAX_LVL = 1;

    public BleedEnchantment(Rarity rarity, EnchantmentCategory cat, EquipmentSlot... slots) {
        super(rarity, cat, slots);
    }

    @Override public int getMaxLevel() { return MAX_LVL; }
    @Override public int getMinLevel() { return 1; }

    // Typical gates
    @Override public boolean canEnchant(ItemStack stack) { return super.canEnchant(stack); }
}
