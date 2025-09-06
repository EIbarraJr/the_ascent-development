package net.ejibarra.pathtoimmortals.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class GrievousWoundsEnchantment extends Enchantment {
    public GrievousWoundsEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.WEAPON, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3; // could allow II, III if you want stronger reductions
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment other) {
        // Prevent combining with other "healing" curses if you make any
        return super.checkCompatibility(other);
    }
}
