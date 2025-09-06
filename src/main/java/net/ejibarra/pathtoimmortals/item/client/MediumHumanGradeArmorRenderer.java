package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MediumHumanGradeArmorRenderer extends GeoArmorRenderer<StatArmorItems> {
    public MediumHumanGradeArmorRenderer() {
        super(new MediumHumanGradeArmorModel());
    }
}
