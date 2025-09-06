package net.ejibarra.pathtoimmortals.event;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.client.DaoCoreProjectileModel;
import net.ejibarra.pathtoimmortals.entity.client.SpikeProjectileModel;
import net.ejibarra.pathtoimmortals.entity.custom.DarklinEntity;
import net.ejibarra.pathtoimmortals.entity.custom.HereticEntity;
import net.ejibarra.pathtoimmortals.entity.custom.HoodedSpikedRatEntity;
import net.ejibarra.pathtoimmortals.entity.custom.SpikedRatEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.DAO_CORE_PROJECTILE_LAYER, DaoCoreProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SPIKE_PROJECTILE_LAYER, SpikeProjectileModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.HERETIC.get(), HereticEntity.createAttributes().build());
        event.put(ModEntities.SPIKEDRAT.get(), SpikedRatEntity.createAttributes().build());
        event.put(ModEntities.DARKLIN.get(), DarklinEntity.createAttributes().build());
        event.put(ModEntities.HOODEDRAT.get(), HoodedSpikedRatEntity.createAttributes().build());
    }
}
