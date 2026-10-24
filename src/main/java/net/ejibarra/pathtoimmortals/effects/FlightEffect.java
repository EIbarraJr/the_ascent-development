package net.ejibarra.pathtoimmortals.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class FlightEffect extends MobEffect {
    protected FlightEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x7EC8FF);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return false;
    }
}
