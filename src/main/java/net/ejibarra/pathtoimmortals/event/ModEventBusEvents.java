package net.ejibarra.pathtoimmortals.event;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.attributes.ModAttributes;
import net.ejibarra.pathtoimmortals.enchantment.ModEnchantments;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.client.CheeseModel;
import net.ejibarra.pathtoimmortals.entity.client.DaoCoreProjectileModel;
import net.ejibarra.pathtoimmortals.entity.client.OriginSwordModel;
import net.ejibarra.pathtoimmortals.entity.client.SpikeProjectileModel;
import net.ejibarra.pathtoimmortals.entity.custom.*;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//Layers & Attribute Creations

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.DAO_CORE_PROJECTILE_LAYER, DaoCoreProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SPIKE_PROJECTILE_LAYER, SpikeProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CHEESE_PROJECTILE_LAYER, CheeseModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ORIGIN_SWORD_LAYER, OriginSwordModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.HERETIC.get(), HereticEntity.createAttributes().build());
        event.put(ModEntities.ASSASSAIN.get(), AssassainEntity.createAttributes().build());
        event.put(ModEntities.SPIKEDRAT.get(), SpikedRatEntity.createAttributes().build());
        event.put(ModEntities.HOODEDRAT.get(), HoodedSpikedRatEntity.createAttributes().build());
        event.put(ModEntities.RAT_LORD.get(), RatLord.createAttributes().build());
        event.put(ModEntities.OGRE.get(), OgreEntity.createAttributes().build());
        event.put(ModEntities.SCORPION.get(), ScorpionEntity.createAttributes().build());
        event.put(ModEntities.FUNGI_SWORDSMAN.get(), FungiSwordsmanEntity.createAttributes().build());
        event.put(ModEntities.SPORE_SPREADER.get(), SporeSpreaderEntity.createAttributes().build());
        event.put(ModEntities.BEEFSTEAK_MUSHROOM.get(), BeefsteakMushroomEntity.createAttributes().build());
        event.put(ModEntities.ORC_WARRIOR.get(), OrcWarrior.createAttributes().build());
    }



}

