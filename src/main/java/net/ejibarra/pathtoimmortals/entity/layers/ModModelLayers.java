package net.ejibarra.pathtoimmortals.entity.layers;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation DAO_CORE_PROJECTILE_LAYER = new ModelLayerLocation(
            new ResourceLocation(PathToImmortals.MOD_ID, "dao_core_projectile_layer"), "dao_core_projectile_layer");
    public static final ModelLayerLocation SPIKE_PROJECTILE_LAYER = new ModelLayerLocation(
            new ResourceLocation(PathToImmortals.MOD_ID, "spike_projectile_layer"), "spike_projectile_layer");

    public static final ModelLayerLocation HERETIC_LAYER = new ModelLayerLocation(
            new ResourceLocation(PathToImmortals.MOD_ID, "heretic_layer"), "heretic_layer");
    public static final ModelLayerLocation SPIKEDRAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(PathToImmortals.MOD_ID, "spikedrat_layer"), "spikedrat_layer");
    public static final ModelLayerLocation DARKLIN_LAYER = new ModelLayerLocation(
            new ResourceLocation(PathToImmortals.MOD_ID, "darklin_layer"), "darklin_layer");
    public static final ModelLayerLocation HOODEDRAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(PathToImmortals.MOD_ID, "hooded_spiked_rat_layer"), "hooded_spiked_rat_layer");
}
