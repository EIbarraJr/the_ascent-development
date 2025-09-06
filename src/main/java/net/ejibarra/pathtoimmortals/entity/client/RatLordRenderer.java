package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.HoodedSpikedRatEntity;
import net.ejibarra.pathtoimmortals.entity.custom.RatLord;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RatLordRenderer extends MobRenderer<RatLord, RatLordModel<RatLord>> {
    private static final ResourceLocation RAT_LORD_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/rat_lord.png");

    public RatLordRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RatLordModel<>(pContext.bakeLayer(ModModelLayers.RAT_LORD_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RatLord pEntity) {
        return RAT_LORD_LOCATION;
    }
}
