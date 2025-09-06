package net.ejibarra.pathtoimmortals.attributes;

// RegenHandler.java

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;


@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID)
public class RegenHandler {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity le = event.getEntity();
        if (le.level().isClientSide) return;

        AttributeInstance inst = le.getAttribute(net.ejibarra.pathtoimmortals.attributes.ModAttributes.PERCENT_REGEN.get());
        if (inst == null) return;

        double perSecond = inst.getValue(); // e.g., 0.10 for 10%/sec
        if (perSecond == 0.0D) return;
        if (!le.isAlive()) return;

        float maxHp = le.getMaxHealth();
        if (maxHp <= 0f) return;

        float perTickHeal = (float)(maxHp * (perSecond / 20.0D));

        // Optional: skip if already full
        if (le.getHealth() >= maxHp) return;

        // Optional: throttle if recently hurt (example: only heal after 20 ticks since last damage)
        // if (le.invulnerableTime > 0) return;

        le.heal(perTickHeal);
    }
}

