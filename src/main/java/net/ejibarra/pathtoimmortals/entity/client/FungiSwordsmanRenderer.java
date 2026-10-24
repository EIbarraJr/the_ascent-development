package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.FungiSwordsmanEntity;
import net.ejibarra.pathtoimmortals.entity.custom.HereticEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FungiSwordsmanRenderer extends MobRenderer<FungiSwordsmanEntity, FungiSwordsmanModel<FungiSwordsmanEntity>> {
    private static final ResourceLocation FUNGI_SWORDSMAN_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/fungi_swordsman.png");

    public FungiSwordsmanRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FungiSwordsmanModel<>(pContext.bakeLayer(ModModelLayers.FUNGI_SWORDSMAN_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FungiSwordsmanEntity pEntity) {
        return FUNGI_SWORDSMAN_LOCATION;
    }
}
