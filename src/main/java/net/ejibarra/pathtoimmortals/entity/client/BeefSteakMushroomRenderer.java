package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.BeefsteakMushroomEntity;
import net.ejibarra.pathtoimmortals.entity.custom.FungiSwordsmanEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BeefSteakMushroomRenderer extends MobRenderer<BeefsteakMushroomEntity, BeefsteakMushroomModel<BeefsteakMushroomEntity>> {
    private static final ResourceLocation BEEFSTEAK_MUSHROOM_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/beefsteak_mushroom.png");

    public BeefSteakMushroomRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BeefsteakMushroomModel<>(pContext.bakeLayer(ModModelLayers.BEEFSTEAK_MUSHROOM_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(BeefsteakMushroomEntity pEntity) {
        return BEEFSTEAK_MUSHROOM_LOCATION;
    }
}
