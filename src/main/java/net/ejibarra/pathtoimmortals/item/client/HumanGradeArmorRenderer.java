package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class HumanGradeArmorRenderer extends GeoArmorRenderer<StatArmorItems> {
    public HumanGradeArmorRenderer() {
        super(new HumanGradeArmorModel());
    }
}
