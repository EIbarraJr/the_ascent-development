package net.ejibarra.pathtoimmortals.block.custom;

// ColoredPetalLeavesBlock.java

import net.ejibarra.pathtoimmortals.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.particles.ParticleOptions;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

import java.util.function.Supplier;

// ColoredPetalLeavesBlock.java
public class ColoredPetalLeavesBlock extends LeavesBlock {
    private final Supplier<SimpleParticleType> particle;

    public ColoredPetalLeavesBlock(Supplier<SimpleParticleType> petal, BlockBehaviour.Properties props) {
        super(props);
        this.particle = petal;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        super.animateTick(state, level, pos, rand);
        if (rand.nextInt(8) == 0) { // same 1-in-10 chance as cherry
            BlockPos below = pos.below();
            if (!isFaceFull(level.getBlockState(below).getCollisionShape(level, below), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, rand, particle.get());
            }
        }
    }
}



