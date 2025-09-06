package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.HereticEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HereticRenderer extends MobRenderer<HereticEntity, HereticModel<HereticEntity>> {
    private static final ResourceLocation HERETIC_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/heretic.png");

    public HereticRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HereticModel<>(pContext.bakeLayer(ModModelLayers.HERETIC_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HereticEntity pEntity) {
        return HERETIC_LOCATION;
    }
}
