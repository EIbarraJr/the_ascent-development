package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HumanGradeArmorModel extends GeoModel<StatArmorItems> {
    @Override
    public ResourceLocation getModelResource(StatArmorItems humanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "geo/hum_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StatArmorItems humanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "textures/armor/human_grade_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StatArmorItems humanGradeArmorItem) {
        return new ResourceLocation(PathToImmortals.MOD_ID, "animations/human_grade_armor_anim.json");
    }
}
