package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class UpperHumanGradeArmorModel extends GeoModel<StatArmorItems> {
    @Override
    public ResourceLocation getModelResource(StatArmorItems mediumHumanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "geo/upper_hum_arm_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StatArmorItems mediumHumanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "textures/armor/upper_human_grade_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StatArmorItems mediumHumanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "animations/upper_hum_arm_anim.json");
    }
}
