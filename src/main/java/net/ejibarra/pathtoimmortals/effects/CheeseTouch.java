package net.ejibarra.pathtoimmortals.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class CheeseTouch extends MobEffect {
    // Stable, unique UUIDs for attribute modifiers (generate your own if desired)
    public static final UUID MOVE_SLOW_UUID = UUID.fromString("7a2e0b54-0a14-46c5-a89b-4b0bb9d5c0c1");
    public static final UUID WEAKNESS_UUID  = UUID.fromString("e8b2c0e9-6f55-44c0-8c0a-5c32e3d94e77");

    public CheeseTouch() {
        // Color tints particles/inventory icon; we’ll overlay the screen separately
        super(MobEffectCategory.HARMFUL, 0xFFE17A); // warm cheese-yellow

        // -20% movement speed (MULTIPLY_TOTAL with -0.20)
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                MOVE_SLOW_UUID.toString(),
                -0.3D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        // Weakness: choose ONE of these styles:

        // (A) Percentage-based: -20% total attack damage (feels like “weaker”)
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                WEAKNESS_UUID.toString(),
                -0.32D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        // (B) Flat vanilla-like: uncomment to do -4 flat instead (remove the (A) block above)
//        this.addAttributeModifier(
//                Attributes.ATTACK_DAMAGE,
//                WEAKNESS_UUID.toString(),
//                -4.0D,
//                AttributeModifier.Operation.ADDITION
//        );
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            int nauseaDurationTicks = 60 + amplifier * 20; // 3s base, +1s per amp
            // Set 'visible' and 'showIcon' to false if you DON'T want a second HUD icon
            entity.addEffect(new MobEffectInstance(
                    MobEffects.CONFUSION,                // aka “Nausea”
                    nauseaDurationTicks,
                    0,                                   // amplifier for nausea itself
                    false,                               // ambient
                    true,                                // visible particles?
                    true                                 // show HUD icon?
            ));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // No per-tick logic needed; attributes handle slow/weakness. Return false.
        return false;
    }
}
