package net.ejibarra.pathtoimmortals.block.custom.entity;

import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LightDivineRunicBlockEntity extends BlockEntity {

    private boolean hasJadeNearby = false;
    private int checkCooldown = 0;

    public LightDivineRunicBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LIGHT_DIVINE_BRICK_RUNIC_BE.get(), pPos, pBlockState);
    }

    // Called from the block ticker on the CLIENT side
    public static void clientTick(Level level, BlockPos pos, BlockState state,
                                  LightDivineRunicBlockEntity be) {
        // Only care about client rendering
        if (level == null || !level.isClientSide) return;

        if (be.checkCooldown > 0) {
            be.checkCooldown--;
            return;
        }

        be.checkCooldown = 100; // check every 100 ticks (~5s at 20 TPS)
        be.hasJadeNearby = scanForJade(level, pos, 5); // radius 5 total of 11, tweak if you want
    }

    private static boolean scanForJade(Level level, BlockPos center, int radius) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    mutable.set(center.getX() + dx, center.getY() + dy, center.getZ() + dz);
                    if (level.getBlockState(mutable).is(ModBlocks.INFUSED_JADE_BLOCK.get())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasJadeNearby() {
        return hasJadeNearby;
    }
}
