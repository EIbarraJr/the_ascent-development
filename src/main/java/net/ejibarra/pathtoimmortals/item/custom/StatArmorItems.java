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

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;

public class StatArmorItems extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final ArmorItem.Type slotType;

    // extras (set per item instance in your registry)
    private final double extraHealth;      // +N health (2.0 = +1 heart)
    private final double extraArmor;       // +N armor points (2.0 = +1 icon)
    private final double speedPercent;     // +N% speed as 0.XX (0.05 = +5%)
    private final double extraDamage;     // flat +damage added to total (ADDITION)
    private final double damagePercent;   // +N% total damage as 0.XX (MULTIPLY_TOTAL)

    public StatArmorItems(ArmorMaterial material,
                         ArmorItem.Type type,
                         Item.Properties props,
                         double extraHealth,
                         double extraArmor,
                         double speedPercent,
                          double extraDamage,
                          double damagePercent) {
        super(material, type, props);
        this.slotType = type;
        this.extraHealth = extraHealth;
        this.extraArmor = extraArmor;
        this.speedPercent = speedPercent;
        this.extraDamage = extraDamage;
        this.damagePercent = damagePercent;
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
            private HumanGradeArmorRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    this.renderer = new HumanGradeArmorRenderer();
                }


                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
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
}
