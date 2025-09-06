package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.DarklinEntity;
import net.ejibarra.pathtoimmortals.entity.custom.HoodedSpikedRatEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HoodedSpikedRatRenderer extends MobRenderer<HoodedSpikedRatEntity, HoodedSpikedRatModel<HoodedSpikedRatEntity>> {
    private static final ResourceLocation HOODEDRAT_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/hooded_spiked_rat.png");

    public HoodedSpikedRatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HoodedSpikedRatModel<>(pContext.bakeLayer(ModModelLayers.HOODEDRAT_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HoodedSpikedRatEntity pEntity) {
        return HOODEDRAT_LOCATION;
    }
}
