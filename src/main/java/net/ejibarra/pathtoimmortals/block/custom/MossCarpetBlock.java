package net.ejibarra.pathtoimmortals.block.custom;

import net.minecraft.world.level.block.CarpetBlock;

public class MossCarpetBlock extends CarpetBlock {
    private final int lightLevel; // optional custom lighting per variant

    public MossCarpetBlock(Properties props, int lightLevel) {
        super(props);
        this.lightLevel = lightLevel;
    }

    public MossCarpetBlock(Properties props) {
        this(props, 0);
    }

    @Override
    public int getLightEmission(net.minecraft.world.level.block.state.BlockState state,
                                net.minecraft.world.level.BlockGetter level,
                                net.minecraft.core.BlockPos pos) {
        return lightLevel;
    }
}
