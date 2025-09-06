package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.OgreEntity;
import net.ejibarra.pathtoimmortals.entity.custom.RatLord;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class OgreRenderer extends MobRenderer<OgreEntity, Ogre<OgreEntity>> {
    private static final ResourceLocation OGRE_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/club_ogre.png");

    public OgreRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Ogre<>(pContext.bakeLayer(ModModelLayers.OGRE_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(OgreEntity  pEntity) {
        return OGRE_LOCATION;
    }
}
