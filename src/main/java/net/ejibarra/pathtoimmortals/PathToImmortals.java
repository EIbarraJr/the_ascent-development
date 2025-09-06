package net.ejibarra.pathtoimmortals;

import com.mojang.logging.LogUtils;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.block.custom.entity.ModBlockEntities;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.client.*;
import net.ejibarra.pathtoimmortals.item.ModCreativeModeTabs;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.ejibarra.pathtoimmortals.item.client.HumanGradeArmorRenderer;
import net.ejibarra.pathtoimmortals.recipe.ModRecipes;
import net.ejibarra.pathtoimmortals.screen.InfusionTable1Screen;
import net.ejibarra.pathtoimmortals.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PathToImmortals.MOD_ID)
public class PathToImmortals {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "pathtoimmortals";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public PathToImmortals(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        ModCreativeModeTabs.register(modEventBus);

        GeckoLib.initialize();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);


    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(()-> {
                EntityRenderers.register(ModEntities.DAO_CORE_PROJECTILE.get(), DaoCoreRenderer::new);
                EntityRenderers.register(ModEntities.SPIKE_PROJECTILE.get(), SpikeProjectileRenderer::new);
                EntityRenderers.register(ModEntities.HERETIC.get(), HereticRenderer::new);
                EntityRenderers.register(ModEntities.SPIKEDRAT.get(), SpikedRatRenderer::new);
                EntityRenderers.register(ModEntities.HOODEDRAT.get(), HoodedSpikedRatRenderer::new);
                EntityRenderers.register(ModEntities.DARKLIN.get(), DarklinRenderer::new);

                MenuScreens.register(ModMenuTypes.INFUSIONTABLE_MENU_1.get(), InfusionTable1Screen::new);

            });
            
        }
    }
}
