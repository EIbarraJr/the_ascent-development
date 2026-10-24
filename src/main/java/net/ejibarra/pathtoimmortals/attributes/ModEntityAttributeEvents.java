package net.ejibarra.pathtoimmortals.attributes;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributeEvents {

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.PERCENT_REGEN.get());
        event.add(EntityType.PLAYER, ModAttributes.CRIT_CHANCE.get());
        event.add(EntityType.PLAYER, ModAttributes.CRIT_DAMAGE.get());
    }
}
