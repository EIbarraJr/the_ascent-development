package net.ejibarra.pathtoimmortals.event;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
}
