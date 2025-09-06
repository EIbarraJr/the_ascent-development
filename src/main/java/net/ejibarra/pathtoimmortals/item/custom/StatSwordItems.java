package net.ejibarra.pathtoimmortals.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    @Override
    public void appendHoverText(ItemStack stack,
                                @org.jetbrains.annotations.Nullable net.minecraft.world.level.Level level,
                                java.util.List<net.minecraft.network.chat.Component> tooltip,
                                net.minecraft.world.item.TooltipFlag flag) {
        // Header
        tooltip.add(net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.weapon_stats")
                .withStyle(net.minecraft.ChatFormatting.GOLD));

        boolean showDetails = true;
        try {
            // client-only helper; safe inside try
            showDetails = net.minecraft.client.gui.screens.Screen.hasAltDown();
        } catch (Throwable ignored) {}

        if (!showDetails) {
            // Compact lines (only show non-zero bonuses)
            /*if (extraDamage != 0.0)
                tooltip.add(net.minecraft.network.chat.Component.translatable(
                                "tooltip.pathtoimmortals.attack_damage", fmt(extraDamage))//
                        .withStyle(net.minecraft.ChatFormatting.RED));

            if (damagePercent != 0.0)
                tooltip.add(net.minecraft.network.chat.Component.translatable(
                                "tooltip.pathtoimmortals.attack_damage_percent", pct(damagePercent))
                        .withStyle(net.minecraft.ChatFormatting.RED));

            if (extraAtkSpeed != 0.0)
                tooltip.add(coloredLine(fmtSigned(extraAtkSpeed) + " " +
                        Component.translatable("tooltip.pathtoimmortals.attack_speed"), net.minecraft.ChatFormatting.BLUE));

            if (atkSpeedPercent != 0.0)
                tooltip.add(coloredLine(fmtSignedPercent(atkSpeedPercent) + " " +
                        Component.translatable("tooltip.pathtoimmortals.attack_speed_percent"), net.minecraft.ChatFormatting.BLUE));

            if (moveSpeedWhileHeld != 0.0)
                tooltip.add(coloredLine(fmtSignedPercent(moveSpeedWhileHeld) + " " +
                        Component.translatable("tooltip.pathtoimmortals.move_speed_while_held"), net.minecraft.ChatFormatting.DARK_AQUA));*/

            // Hint
            tooltip.add(net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.hold_alt")
                    .withStyle(net.minecraft.ChatFormatting.DARK_GRAY));
            return;
        }

        // Detailed view
        if (extraDamage != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.attack_damage_detail", fmt(extraDamage))
                    .withStyle(net.minecraft.ChatFormatting.RED));
        }
        if (damagePercent != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.attack_damage_percent_detail", pct(damagePercent))
                    .withStyle(net.minecraft.ChatFormatting.RED));
        }
        if (extraAtkSpeed != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.attack_speed_detail", fmtSigned(extraAtkSpeed))
                    .withStyle(net.minecraft.ChatFormatting.BLUE));
        }
        if (atkSpeedPercent != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.attack_speed_percent_detail", fmtSignedPercent(atkSpeedPercent))
                    .withStyle(net.minecraft.ChatFormatting.BLUE));
        }
        if (moveSpeedWhileHeld != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.move_speed_while_held_detail", fmtSignedPercent(moveSpeedWhileHeld))
                    .withStyle(net.minecraft.ChatFormatting.DARK_AQUA));
        }


        // --- Totals (independent of Shift; or show only in detailed view if you prefer) ---
        var mainhandMods = stack.getAttributeModifiers(net.minecraft.world.entity.EquipmentSlot.MAINHAND);

// Player defaults: 1.0 damage, 4.0 attack speed
        double totalDmg = computeFinalAttr(1.0, mainhandMods, net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE);
        double totalSpd = computeFinalAttr(4.0, mainhandMods, net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED);

// quick DPS estimate (vanilla DPS-ish, not counting crits/effects)
        double dps = totalDmg * totalSpd;

        tooltip.add(net.minecraft.network.chat.Component.translatable(
                        "tooltip.pathtoimmortals.total_attack_damage", fmt1(totalDmg))
                .withStyle(net.minecraft.ChatFormatting.YELLOW));

        tooltip.add(net.minecraft.network.chat.Component.translatable(
                        "tooltip.pathtoimmortals.total_attack_speed", fmt1(totalSpd))
                .withStyle(net.minecraft.ChatFormatting.YELLOW));

        tooltip.add(net.minecraft.network.chat.Component.translatable(
                        "tooltip.pathtoimmortals.est_dps", fmt1(dps))
                .withStyle(net.minecraft.ChatFormatting.GOLD));
    }

    private static net.minecraft.network.chat.Component coloredLine(String s, net.minecraft.ChatFormatting color) {
        return net.minecraft.network.chat.Component.literal(s).withStyle(color);
    }
    private static String fmt(double x) {
        if (Math.abs(x - Math.rint(x)) < 1e-9) return String.valueOf((int)Math.rint(x));
        return String.format(java.util.Locale.ROOT, "%.2f", x);
    }
    private static String pct(double x) {
        return String.format(java.util.Locale.ROOT, "%.0f%%", x * 100.0);
    }
    private static String fmtSigned(double x) {
        String s = fmt(Math.abs(x));
        return (x >= 0 ? "+ " : "− ") + s; // use unicode minus for nicer look
    }
    private static String fmtSignedPercent(double x) {
        String s = String.format(java.util.Locale.ROOT, "%.0f%%", Math.abs(x) * 100.0);
        return (x >= 0 ? "+ " : "− ") + s;
    }

    // Compute final attribute value from the item's modifiers (MAINHAND),
// using a supplied base (1.0 for damage, 4.0 for attack speed).
    private static double computeFinalAttr(double base,
                                           com.google.common.collect.Multimap<net.minecraft.world.entity.ai.attributes.Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> mods,
                                           net.minecraft.world.entity.ai.attributes.Attribute attr) {
        var coll = mods.get(attr);
        if (coll == null || coll.isEmpty()) return base;

        double add = 0.0;
        double multBase = 0.0;
        double multTotal = 0.0;

        for (var m : coll) {
            switch (m.getOperation()) {
                case ADDITION -> add += m.getAmount();
                case MULTIPLY_BASE -> multBase += m.getAmount();
                case MULTIPLY_TOTAL -> multTotal += m.getAmount();
            }
        }
        double value = (base + add) * (1.0 + multBase);
        value = value * (1.0 + multTotal);
        return value;
    }

    private static String fmt1(double x) {
        return String.format(java.util.Locale.ROOT, "%.1f", x);
    }



}
