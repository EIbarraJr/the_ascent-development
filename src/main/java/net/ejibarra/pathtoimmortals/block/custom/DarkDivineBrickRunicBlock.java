package net.ejibarra.pathtoimmortals.block.custom;

import net.ejibarra.pathtoimmortals.block.custom.entity.DarkDivineBrickRunicBlockEntity;
import net.ejibarra.pathtoimmortals.block.custom.entity.LightDivineRunicBlockEntity;
import net.ejibarra.pathtoimmortals.block.custom.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class DarkDivineBrickRunicBlock extends Block implements EntityBlock {
    public DarkDivineBrickRunicBlock(Properties p) { super(p); }

    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DarkDivineBrickRunicBlockEntity( pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type) {

        if (level.isClientSide) {
            return createTickerHelper(
                    type,
                    ModBlockEntities.DARK_DIVINE_BRICK_RUNIC_BE.get(),
                    DarkDivineBrickRunicBlockEntity::clientTick
            );
        }

        return null;
    }

    // Same signature + behavior used by vanilla blocks with tickers
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A>
    createTickerHelper(BlockEntityType<A> givenType,
                       BlockEntityType<E> expectedType,
                       BlockEntityTicker<? super E> ticker) {

        return expectedType == givenType
                ? (BlockEntityTicker<A>) ticker
                : null;
    }
}
