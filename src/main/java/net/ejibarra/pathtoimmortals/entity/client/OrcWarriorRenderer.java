package net.ejibarra.pathtoimmortals.entity.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.OrcWarrior;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OrcWarriorRenderer extends MobRenderer<OrcWarrior, OrcModel<OrcWarrior>> {
    private static final ResourceLocation ORC_LOCATION = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/orc.png");

    public OrcWarriorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new OrcModel<>(pContext.bakeLayer(ModModelLayers.ORC_WARRIOR_LAYER)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(OrcWarrior pEntity) {
        return ORC_LOCATION;
    }
}
