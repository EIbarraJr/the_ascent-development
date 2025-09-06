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
                    new RangedAttribute("attribute.name.yourmod.percent_regen",
                            0.0D,   // default base = 0%/sec
                            -1.0D,  // min (allow negative if you want “grievous wounds”-style anti-regen)
                            10.0D   // max (1000%/sec cap, adjust as you like)
                    ).setSyncable(true));
    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

}
