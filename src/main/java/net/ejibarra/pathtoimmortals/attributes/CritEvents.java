package net.ejibarra.pathtoimmortals.attributes;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CritEvents {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide) return;


        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        if (event.getSource().isIndirect()) return;


        double rawChance = player.getAttributeValue(ModAttributes.CRIT_CHANCE.get());
        if (rawChance <= 0.0) return;


        double baseCritDamage = player.getAttributeValue(ModAttributes.CRIT_DAMAGE.get());


// Clamp chance and convert overflow → crit damage
        double critChance = Math.min(1.0, rawChance);
        double overflowChance = Math.max(0.0, rawChance - 1.0);
        double totalCritDamage = Math.max(0.0,
                baseCritDamage + (overflowChance * 1.75));


// Roll
        if (player.getRandom().nextDouble() < critChance) {
            event.setAmount((float) (event.getAmount() * (1.0 + totalCritDamage)));
        }
    }
}
