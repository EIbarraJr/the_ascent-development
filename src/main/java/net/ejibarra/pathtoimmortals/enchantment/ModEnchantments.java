package net.ejibarra.pathtoimmortals.enchantment;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PathToImmortals.MOD_ID);

    public static final RegistryObject<Enchantment> GRIEVOUS_WOUND =
            ENCHANTMENTS.register("grievous_wounds",
                    () -> new GrievousWoundsEnchantment(Enchantment.Rarity.COMMON,
                            EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> BLEED =
            ENCHANTMENTS.register("bleed", () ->
                    new BleedEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
