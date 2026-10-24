package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.AssassainEntity;
import net.ejibarra.pathtoimmortals.entity.custom.BeefsteakMushroomEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class AssassainRenderer extends MobRenderer<AssassainEntity, AssassianModel<AssassainEntity>> {
    private static final ResourceLocation ASSASSAIN_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/assassian.png");

    public AssassainRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AssassianModel<>(pContext.bakeLayer(ModModelLayers.ASSASSAIN_LAYER)), .5f);
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(AssassainEntity pEntity) {
        return ASSASSAIN_LOCATION;
    }
}
