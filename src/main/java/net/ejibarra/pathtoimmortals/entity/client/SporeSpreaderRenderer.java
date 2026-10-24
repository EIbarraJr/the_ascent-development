package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.FungiSwordsmanEntity;
import net.ejibarra.pathtoimmortals.entity.custom.SporeSpreaderEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SporeSpreaderRenderer extends MobRenderer<SporeSpreaderEntity, SporeSpreader<SporeSpreaderEntity>> {
    private static final ResourceLocation SPORE_SPREADER_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/spore_spreader.png");

    public SporeSpreaderRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SporeSpreader<>(pContext.bakeLayer(ModModelLayers.SPORE_SPREADER_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SporeSpreaderEntity pEntity) {
        return SPORE_SPREADER_LOCATION;
    }
}
