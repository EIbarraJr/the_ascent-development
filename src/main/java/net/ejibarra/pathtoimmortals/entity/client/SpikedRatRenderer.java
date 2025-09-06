package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.SpikedRatEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpikedRatRenderer extends MobRenderer<SpikedRatEntity, SpikedRatModel<SpikedRatEntity>> {
    private static final ResourceLocation SPIKEDRAT_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/spikedrat.png");

    public SpikedRatRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SpikedRatModel<>(pContext.bakeLayer(ModModelLayers.SPIKEDRAT_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SpikedRatEntity pEntity) {
        return SPIKEDRAT_LOCATION;
    }
}
