package net.ejibarra.pathtoimmortals.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ReinforcedEffect extends MobEffect {
    public ReinforcedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xC9B458);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return false;
    }
}
