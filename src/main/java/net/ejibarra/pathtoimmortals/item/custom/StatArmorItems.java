package net.ejibarra.pathtoimmortals.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.ejibarra.pathtoimmortals.item.client.HumanGradeArmorRenderer;
import net.ejibarra.pathtoimmortals.item.client.MediumHumanGradeArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StatArmorItems extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final ArmorItem.Type slotType;

    // extras (set per item instance in your registry)
    private final double extraHealth;      // +N health (2.0 = +1 heart)
    private final double extraArmor;       // +N armor points (2.0 = +1 icon)
    private final double speedPercent;     // +N% speed as 0.XX (0.05 = +5%)
    private final double extraDamage;     // flat +damage added to total (ADDITION)
    private final double damagePercent;   // +N% total damage as 0.XX (MULTIPLY_TOTAL)

    // NEW: per-item renderer factory
    private final Supplier<GeoArmorRenderer<StatArmorItems>> rendererFactory;

    public StatArmorItems(ArmorMaterial material,
                          ArmorItem.Type type,
                          Item.Properties props,
                          double extraHealth,
                          double extraArmor,
                          double speedPercent,
                          double extraDamage,
                          double damagePercent, Supplier<GeoArmorRenderer<StatArmorItems>> rendererFactory) {
        super(material, type, props);
        this.slotType = type;
        this.extraHealth = extraHealth;
        this.extraArmor = extraArmor;
        this.speedPercent = speedPercent;
        this.extraDamage = extraDamage;
        this.damagePercent = damagePercent;
        this.rendererFactory = rendererFactory;
    }

    /** Stable UUID per item/slot/attribute so stacking behaves correctly. */
    private static UUID seededUUID(String key) {
        return UUID.nameUUIDFromBytes(("pathtoimmortals:" + key).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        // Start with vanilla/armor-material modifiers (armor, toughness, knockback res)
        Multimap<Attribute, AttributeModifier> base = super.getDefaultAttributeModifiers(slot);

        if (slot != this.slotType.getSlot()) {
            return base; // only add extras on the slot this piece actually occupies
        }

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(base);

        // Extra MAX HEALTH (Operation.ADDITION: raw hearts)
        if (extraHealth != 0.0) {
            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":hp:" + slot.getName()),
                    "Armor bonus health",
                    extraHealth,
                    AttributeModifier.Operation.ADDITION
            ));
        }

        // Extra ARMOR (adds to material’s base)
        if (extraArmor != 0.0) {
            builder.put(Attributes.ARMOR, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":arm:" + slot.getName()),
                    "Armor bonus armor",
                    extraArmor,
                    AttributeModifier.Operation.ADDITION
            ));
        }

        // Extra MOVE SPEED (use MULTIPLY_TOTAL for intuitive “+X%”)
        if (speedPercent != 0.0) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":spd:" + slot.getName()),
                    "Armor bonus speed",
                    speedPercent,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
        }
        if (extraDamage != 0.0) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":atk:add:" + slot.getName()),
                    "Armor bonus attack damage",
                    extraDamage,
                    AttributeModifier.Operation.ADDITION));
        }

        // --- NEW: Damage (%) ---
        if (damagePercent != 0.0) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    seededUUID(getDescriptionId() + ":atk:mul:" + slot.getName()),
                    "Armor bonus attack damage %",
                    damagePercent,
                    AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return builder.build();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<StatArmorItems> renderer;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {

                if (renderer == null) renderer = rendererFactory.get();
                // GeoArmorRenderer provides this helper to wire pose etc.
                renderer.prepForRender(entity, stack, slot, original);
                return renderer; // GeoArmorRenderer extends HumanoidModel, so returning it is fine
            }
        });
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                @org.jetbrains.annotations.Nullable net.minecraft.world.level.Level level,
                                java.util.List<net.minecraft.network.chat.Component> tooltip,
                                net.minecraft.world.item.TooltipFlag flag) {
        // Compact header line
        tooltip.add(net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.stats")
                .withStyle(net.minecraft.ChatFormatting.GOLD));

        // If you prefer Shift for details:
        boolean showDetails = true;
        try {
            // Client-only; this method is only called on client during tooltip render
            showDetails = net.minecraft.client.gui.screens.Screen.hasShiftDown();
        } catch (Throwable ignored) {}

        if (!showDetails) {
            // Compact summary (only show non-zero bonuses)
            if (extraHealth != 0.0)
                tooltip.add(line("+ ", fmt(extraHealth) + " " +
                                net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.health").getString(),
                        net.minecraft.ChatFormatting.GREEN));

            if (extraArmor != 0.0)
                tooltip.add(line("+ ", fmt(extraArmor) + " " +
                                net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.armor").getString(),
                        net.minecraft.ChatFormatting.AQUA));

            if (speedPercent != 0.0)
                tooltip.add(line("+ ", pct(speedPercent) + " " +
                                net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.speed").getString(),
                        net.minecraft.ChatFormatting.BLUE));

            if (extraDamage != 0.0)
                tooltip.add(line("+ ", fmt(extraDamage) + " " +
                                net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.armor_attack_damage").getString(),
                        net.minecraft.ChatFormatting.RED));

            if (damagePercent != 0.0)
                tooltip.add(line("+ ", pct(damagePercent) + " " +
                                net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.armor_attack_damage_percent").getString(),
                        net.minecraft.ChatFormatting.RED));

            // Hint
            tooltip.add(net.minecraft.network.chat.Component.translatable("tooltip.pathtoimmortals.hold_shift")
                    .withStyle(net.minecraft.ChatFormatting.DARK_GRAY));
            return;
        }

        // Detailed view (with small explanations)
        if (extraHealth != 0.0) {
            double hearts = extraHealth / 2.0;
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.health_detail", fmt(extraHealth), fmt(hearts))
                    .withStyle(net.minecraft.ChatFormatting.GREEN));
        }
        if (extraArmor != 0.0) {
            double armorIcons = extraArmor / 2.0;
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.armor_detail", fmt(extraArmor), fmt(armorIcons))
                    .withStyle(net.minecraft.ChatFormatting.AQUA));
        }
        if (speedPercent != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.speed_detail", pct(speedPercent))
                    .withStyle(net.minecraft.ChatFormatting.BLUE));
        }
        if (extraDamage != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.armor_attack_damage_detail", fmt(extraDamage))
                    .withStyle(net.minecraft.ChatFormatting.RED));
        }
        if (damagePercent != 0.0) {
            tooltip.add(net.minecraft.network.chat.Component.translatable(
                            "tooltip.pathtoimmortals.armor_attack_damage_percent_detail", pct(damagePercent))
                    .withStyle(net.minecraft.ChatFormatting.RED));
        }
    }

    // --- helpers (put as private static methods in the same class) ---
    private static net.minecraft.network.chat.Component line(String prefix, String value, net.minecraft.ChatFormatting color) {
        return net.minecraft.network.chat.Component.literal(prefix + value).withStyle(color);
    }
    private static String fmt(double x) {
        // trim trailing .0; tweak as you like
        if (Math.abs(x - Math.rint(x)) < 1e-9) return String.valueOf((int)Math.rint(x));
        return String.format(java.util.Locale.ROOT, "%.2f", x);
    }
    private static String pct(double x) {
        return String.format(java.util.Locale.ROOT, "%.0f%%", x * 100.0);
    }


}
