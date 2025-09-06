package net.ejibarra.pathtoimmortals.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class StatShieldItems extends ShieldItem {
    // Stable UUIDs (1 per attribute+slot+type) so stacking is deterministic
    private static final UUID UUID_OFFHAND_HEALTH_ADD     = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1001");
    private static final UUID UUID_OFFHAND_HEALTH_PCT     = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1002");
    private static final UUID UUID_OFFHAND_ARMOR_ADD      = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1003");
    private static final UUID UUID_OFFHAND_ARMOR_PCT      = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1004");
    private static final UUID UUID_OFFHAND_TOUGH_PCT      = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1005");
    private static final UUID UUID_OFFHAND_ATKD_ADD       = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1007");
    private static final UUID UUID_OFFHAND_ATKD_PCT       = UUID.fromString("6b9a7b5a-13c2-4b8a-a4c4-5c1d4a0a1008");

    // --- Tunables ---
    private final double healthAdd;       // flat hearts (e.g., 4.0 = +4 HP = +2 hearts)
    private final double healthPct;       // % max health (0.10 = +10%)
    private final double armorAdd;        // flat armor
    private final double armorPct;        // % armor
    private final double armorToughPct;   // % armor toughness
    private final double atkDamageAdd;    // flat attack damage
    private final double atkDamagePct;    // % attack damage (MULTIPLY_TOTAL)

    public StatShieldItems(Properties props,
                          double healthAdd,
                          double healthPct,
                          double armorAdd,
                          double armorPct,
                          double armorToughPct,
                          double atkDamageAdd,
                          double atkDamagePct) {
        super(props);
        this.healthAdd = healthAdd;
        this.healthPct = healthPct;
        this.armorAdd = armorAdd;
        this.armorPct = armorPct;
        this.armorToughPct = armorToughPct;
        this.atkDamageAdd = atkDamageAdd;
        this.atkDamagePct = atkDamagePct;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> b = ImmutableMultimap.builder();
        // Preserve any base modifiers (usually none for shields/offhand, but safe to include)
        b.putAll(super.getDefaultAttributeModifiers(slot));

        if (slot == EquipmentSlot.OFFHAND) {
            // Health
            if (healthAdd != 0) {
                b.put(Attributes.MAX_HEALTH,
                        new AttributeModifier(UUID_OFFHAND_HEALTH_ADD, "shield health +", healthAdd, AttributeModifier.Operation.ADDITION));
            }
            if (healthPct != 0) {
                b.put(Attributes.MAX_HEALTH,
                        new AttributeModifier(UUID_OFFHAND_HEALTH_PCT, "shield health %", healthPct, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            // Armor & Toughness
            if (armorAdd != 0) {
                b.put(Attributes.ARMOR,
                        new AttributeModifier(UUID_OFFHAND_ARMOR_ADD, "shield armor +", armorAdd, AttributeModifier.Operation.ADDITION));
            }
            if (armorPct != 0) {
                b.put(Attributes.ARMOR,
                        new AttributeModifier(UUID_OFFHAND_ARMOR_PCT, "shield armor %", armorPct, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            if (armorToughPct != 0) {
                b.put(Attributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(UUID_OFFHAND_TOUGH_PCT, "shield armor tough %", armorToughPct, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }

            // Attack Damage (flat + %)
            if (atkDamageAdd != 0) {
                b.put(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(UUID_OFFHAND_ATKD_ADD, "shield atk +", atkDamageAdd, AttributeModifier.Operation.ADDITION));
            }
            if (atkDamagePct != 0) {
                // MULTIPLY_TOTAL scales the final total (after flats), which is usually what you want for “% damage”
                b.put(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(UUID_OFFHAND_ATKD_PCT, "shield atk %", atkDamagePct, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }

        return b.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> tip, TooltipFlag flag) {
        if (healthAdd != 0)      tip.add(Component.literal(String.format("+%.1f Max Health", healthAdd)).withStyle(ChatFormatting.GREEN));
        if (healthPct != 0)      tip.add(Component.literal(String.format("+%.0f%% Max Health", 100 * healthPct)).withStyle(ChatFormatting.GREEN));
        if (armorAdd != 0)       tip.add(Component.literal(String.format("+%.1f Armor", armorAdd)).withStyle(ChatFormatting.BLUE));
        if (armorPct != 0)       tip.add(Component.literal(String.format("+%.0f%% Armor", 100 * armorPct)).withStyle(ChatFormatting.BLUE));
        if (armorToughPct != 0)  tip.add(Component.literal(String.format("+%.0f%% Armor Toughness", 100 * armorToughPct)).withStyle(ChatFormatting.DARK_AQUA));
        if (atkDamageAdd != 0)   tip.add(Component.literal(String.format("+%.1f Attack Damage", atkDamageAdd)).withStyle(ChatFormatting.RED));
        if (atkDamagePct != 0)   tip.add(Component.literal(String.format("+%.0f%% Attack Damage", 100 * atkDamagePct)).withStyle(ChatFormatting.RED));
    }
}
