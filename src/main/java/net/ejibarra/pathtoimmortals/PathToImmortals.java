package net.ejibarra.pathtoimmortals;

import com.mojang.logging.LogUtils;
import net.ejibarra.pathtoimmortals.attributes.ModAttributes;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.block.custom.entity.ModBlockEntities;
import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.ejibarra.pathtoimmortals.enchantment.ModEnchantments;
import net.ejibarra.pathtoimmortals.entity.ModEntities;
import net.ejibarra.pathtoimmortals.entity.client.*;
import net.ejibarra.pathtoimmortals.item.ModCreativeModeTabs;
import net.ejibarra.pathtoimmortals.item.ModItemProperties;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceSources;
import net.ejibarra.pathtoimmortals.particle.ModParticles;
import net.ejibarra.pathtoimmortals.recipe.ModRecipes;
import net.ejibarra.pathtoimmortals.screen.HumanGradeDivineAltarScreen;
import net.ejibarra.pathtoimmortals.screen.HumanGradeInfuserScreen;
import net.ejibarra.pathtoimmortals.screen.ModMenuTypes;
import net.ejibarra.pathtoimmortals.worldgen.biome.surface.ModSurfaceRuleData;
import net.ejibarra.pathtoimmortals.worldgen.features.ModFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
import terrablender.api.SurfaceRuleManager;

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
        ModAttributes.register(modEventBus);

        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEnchantments.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModFeatures.FEATURES.register(modEventBus);

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
                EntityRenderers.register(ModEntities.CHEESE_PROJECTILE.get(), CheeseModelRenderer::new);
                //Ability Entities
                EntityRenderers.register(ModEntities.ORIGIN_SWORD_PROJECTILE.get(), OriginSwordRenderer::new);
                EntityRenderers.register(ModEntities.PRESSURE_WAVE.get(), PressureWaveRenderer::new);
                //MOBS
                EntityRenderers.register(ModEntities.HERETIC.get(), HereticRenderer::new);
                EntityRenderers.register(ModEntities.ASSASSAIN.get(), AssassainRenderer::new);
                EntityRenderers.register(ModEntities.SPIKEDRAT.get(), SpikedRatRenderer::new);
                EntityRenderers.register(ModEntities.HOODEDRAT.get(), HoodedSpikedRatRenderer::new);
                EntityRenderers.register(ModEntities.RAT_LORD.get(), RatLordRenderer::new);
                EntityRenderers.register(ModEntities.OGRE.get(), OgreRenderer::new);
                EntityRenderers.register(ModEntities.SCORPION.get(), ScorpionRenderer::new);
                EntityRenderers.register(ModEntities.FUNGI_SWORDSMAN.get(), FungiSwordsmanRenderer::new);
                EntityRenderers.register(ModEntities.SPORE_SPREADER.get(), SporeSpreaderRenderer::new);
                EntityRenderers.register(ModEntities.BEEFSTEAK_MUSHROOM.get(), BeefSteakMushroomRenderer::new);
                EntityRenderers.register(ModEntities.ORC_WARRIOR.get(), OrcWarriorRenderer::new);

                ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get(), RenderType.solid());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get(), RenderType.solid());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAVENDER_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAHOGANY_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPIRIT_FAE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPIRIT_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.FAE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.CERULEAN_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.CARMINE_ROSE_BUSH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.MIDNIGHTS_REACH.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.COTTONS_ROSE_BUSH.get(), RenderType.cutout());

                ModItemProperties.addCustomItemProperties();
                SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRuleData.overworld());

                MenuScreens.register(ModMenuTypes.HUMAN_GRADE_INFUSER_MENU.get(), HumanGradeInfuserScreen::new);
                MenuScreens.register(ModMenuTypes.HUMAN_GRADE_DIVINE_ALTAR_MENU.get(), HumanGradeDivineAltarScreen::new);

                // Turquoise depracated but works
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_TURQUOISE_BUD.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_TURQUOISE_BUD.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_TURQUOISE_BUD.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.TURQUOISE_CLUSTER.get(), RenderType.cutout());
                // Fey
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.SMALL_FEY_BUD.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.MEDIUM_FEY_BUD.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.LARGE_FEY_BUD.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.FEY_CLUSTER.get(), RenderType.cutout());

                OriginEssenceSources.registerDefaults();
                ModMessages.register();
            });
            
        }
    }
}
