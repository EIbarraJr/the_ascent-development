package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.DarklinEntity;
import net.ejibarra.pathtoimmortals.entity.custom.HereticEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DarklinRenderer extends MobRenderer<DarklinEntity, DarklinModel<DarklinEntity>> {
    private static final ResourceLocation DARKLIN_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/darklin.png");

    public DarklinRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DarklinModel<>(pContext.bakeLayer(ModModelLayers.DARKLIN_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(DarklinEntity pEntity) {
        return DARKLIN_LOCATION;
    }
}
