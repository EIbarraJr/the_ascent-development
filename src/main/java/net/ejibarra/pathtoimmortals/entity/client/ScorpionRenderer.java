package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.RatLord;
import net.ejibarra.pathtoimmortals.entity.custom.ScorpionEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScorpionRenderer extends MobRenderer<ScorpionEntity, Scorpion<ScorpionEntity>> {
    private static final ResourceLocation SCORPION_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/scorpion.png");

    public ScorpionRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Scorpion<>(pContext.bakeLayer(ModModelLayers.SCORPION_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ScorpionEntity pEntity) {
        return SCORPION_LOCATION;
    }
}
