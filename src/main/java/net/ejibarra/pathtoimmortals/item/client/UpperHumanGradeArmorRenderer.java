package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class UpperHumanGradeArmorRenderer extends GeoArmorRenderer<StatArmorItems> {
    public UpperHumanGradeArmorRenderer() {
        super(new UpperHumanGradeArmorModel());
    }
}
