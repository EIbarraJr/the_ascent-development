package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DivineEarthArmorModel extends GeoModel<StatArmorItems> {

    @Override
    public ResourceLocation getModelResource(StatArmorItems divineHumanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "geo/divine_hum_arm_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StatArmorItems divineHumanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "textures/armor/divine_human_grade_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StatArmorItems divineHumanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "animations/divine_hum_arm_anim.json");
    }
}