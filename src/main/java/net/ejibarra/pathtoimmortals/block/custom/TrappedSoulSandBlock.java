package net.ejibarra.pathtoimmortals.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TrappedSoulSandBlock extends SoulSandBlock {

    public TrappedSoulSandBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // keep any vanilla behavior
        super.animateTick(state, level, pos, random);

        // spawn soul particles occasionally
        if (random.nextFloat() < 0.15F) { // chance per frame per block
            double x = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.4D;
            double y = pos.getY() + 0.6D + random.nextDouble() * 0.4D;
            double z = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.4D;

            // SOUL = floaty soul wisps, SOUL_FIRE_FLAME = blue fire sparks
            level.addParticle(
                    ParticleTypes.SOUL, // or ParticleTypes.SOUL_FIRE_FLAME
                    x, y, z,
                    0.0D,
                    0.02D + random.nextDouble() * 0.02D, // slight upward drift
                    0.0D
            );
        }
    }
}
