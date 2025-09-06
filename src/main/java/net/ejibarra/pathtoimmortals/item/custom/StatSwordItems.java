package net.ejibarra.pathtoimmortals.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class StatSwordItems extends SwordItem {
    // extra bonuses (on top of vanilla base from SwordItem)
    private final double extraDamage;       // flat +damage (ADDITION)
    private final double damagePercent;     // +% total damage (0.05 = +5%, MULTIPLY_TOTAL)
    private final double extraAtkSpeed;     // flat change to attack speed (ADDITION)  e.g. +0.4 faster, or -0.4 slower
    private final double atkSpeedPercent;   // +% to attack speed (rare; keep small)
    private final double moveSpeedWhileHeld;// +% move speed in hand (0.03 = +3%)

    public StatSwordItems(Tier tier,
                         int baseDamageBonus, float baseAttackSpeed,
                         Item.Properties props,
                         double extraDamage, double damagePercent,
                         double extraAtkSpeed, double atkSpeedPercent,
                         double moveSpeedWhileHeld) {
        super(tier, baseDamageBonus, baseAttackSpeed, props);
        this.extraDamage = extraDamage;
        this.damagePercent = damagePercent;
        this.extraAtkSpeed = extraAtkSpeed;
        this.atkSpeedPercent = atkSpeedPercent;
        this.moveSpeedWhileHeld = moveSpeedWhileHeld;
    }

    private static UUID seededUUID(String key) {
        return UUID.nameUUIDFromBytes(("pathtoimmortals:" + key).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        // Start from vanilla SwordItem’s base modifiers (damage & attack speed)
        Multimap<Attribute, AttributeModifier> base = super.getDefaultAttributeModifiers(slot);
        if (slot != EquipmentSlot.MAINHAND) return base;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> b = ImmutableMultimap.builder();
        b.putAll(base);

        // Extra flat damage
        if (extraDamage != 0.0) {
            b.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":atk_add"),
                    "Sword extra damage",
                    extraDamage,
                    AttributeModifier.Operation.ADDITION));
        }

        // Percent damage (scales final)
        if (damagePercent != 0.0) {
            b.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":atk_pct"),
                    "Sword damage %",
                    damagePercent,
                    AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        // Flat attack speed (vanilla uses ADDITION)
        if (extraAtkSpeed != 0.0) {
            b.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":speed_add"),
                    "Sword attack speed",
                    extraAtkSpeed,
                    AttributeModifier.Operation.ADDITION));
        }

        // Percent attack speed (careful—easy to break balance)
        if (atkSpeedPercent != 0.0) {
            b.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":speed_pct"),
                    "Sword attack speed %",
                    atkSpeedPercent,
                    AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        // Optional: movement speed while held
        if (moveSpeedWhileHeld != 0.0) {
            b.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":movespd_held"),
                    "Move speed while holding sword",
                    moveSpeedWhileHeld,
                    AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return b.build();
    }
}
