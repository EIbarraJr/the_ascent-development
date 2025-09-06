package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    SPIRIT_IRON_TIER1("spirit_iron_tier1", 69, new int[]{5, 8, 7, 5}, 30,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 1.5f, 0.0f, ()-> Ingredient.of(ModItems.SPIRIT_IRON_TIER1.get()));
    /*example adding more materials
    * MOON_STEEL("moon_steel", 37, new int[]{3,7,9,3}, 22, SoundEvents.ARMOR_EQUIP_IRON, 2.5F, 0.05F,
            () -> Ingredient.of(ModItems.MOON_STEEL_INGOT.get())),
    METEOR_STEEL("meteor_steel", 40, new int[]{4,8,10,4}, 24, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.10F,
            () -> Ingredient.of(ModItems.METEOR_STEEL_INGOT.get()));*/

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equidSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {20, 25, 25, 20};

    private ModArmorMaterials(String name, int DurabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equidSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = DurabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equidSound = equidSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient ;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.equidSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return PathToImmortals.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
