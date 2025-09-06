package net.ejibarra.pathtoimmortals.item.client;

import net.ejibarra.pathtoimmortals.item.custom.StatArmorItems;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DivineEarthArmorRenderer extends GeoArmorRenderer<StatArmorItems> {
    public DivineEarthArmorRenderer() {
        super(new DivineEarthArmorModel());
    }
}
