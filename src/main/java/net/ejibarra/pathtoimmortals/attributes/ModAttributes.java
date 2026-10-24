package net.ejibarra.pathtoimmortals.attributes;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, PathToImmortals.MOD_ID);

    // Value meaning: fraction of max HP healed per second. e.g. 0.10 = 10%/sec
    public static final RegistryObject<Attribute> PERCENT_REGEN =
            ATTRIBUTES.register("percent_regen", () ->
                    new RangedAttribute("attribute.name.pathtoimmortals.percent_regen",
                            0.0D,   // default base = 0%/sec
                            -1.0D,  // min (allow negative if you want “grievous wounds”-style anti-regen)
                            10.0D   // max (1000%/sec cap, adjust as you like)
                    ).setSyncable(true));

    // Crit chance as a percent in decimal: 0.10 = 10%
    public static final RegistryObject<Attribute> CRIT_CHANCE =
            ATTRIBUTES.register("crit_chance", () ->
                    new RangedAttribute("attribute.name.pathtoimmortals.crit_chance", 0.0D, 0.0D, 1.0D)
                            .setSyncable(true));


    // Crit damage bonus multiplier (extra on top of normal): 0.50 = +50% (so total becomes 1.5x)
    public static final RegistryObject<Attribute> CRIT_DAMAGE =
            ATTRIBUTES.register("crit_damage", () ->
                    new RangedAttribute("attribute.name.pathtoimmortals.crit_damage", 0.0D, 0.0D, 10.0D)
                            .setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

}
