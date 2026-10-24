package net.ejibarra.pathtoimmortals.effects;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PathToImmortals.MOD_ID);

    public static final RegistryObject<MobEffect> CHEESE_TOUCH = MOB_EFFECTS.register("cheesed", CheeseTouch::new);
    public static final RegistryObject<MobEffect> REINFORCED = MOB_EFFECTS.register("reinforced_icon", ReinforcedEffect::new);
    public static final RegistryObject<MobEffect> FLIGHT_ACTIVE = MOB_EFFECTS.register("levitation_icon", FlightEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
