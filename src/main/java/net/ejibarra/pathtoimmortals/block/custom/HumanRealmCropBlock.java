package net.ejibarra.pathtoimmortals.block.custom;

import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.checkerframework.checker.units.qual.A;

public class HumanRealmCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    // Option A: one specific block
    private final Block requiredBlock;

    // Option B: any block in a tag
    private final TagKey<Block> requiredTag;

    // How far to search (Manhattan radius)
    private static final int RADIUS = 2;


    public HumanRealmCropBlock(Properties pProperties, Block requiredBlock) {
        super(pProperties);
        this.requiredBlock = requiredBlock;
        this.requiredTag = null;
    }

    /* ---------- Core idea: only allow growth when requirement is nearby ---------- */

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rng) {
        if (!hasRequiredBlockNearby(level, pos)) {
            // Do nothing → crop will not advance this tick
            return;
        }
        // Requirement met → proceed with vanilla growth rules
        super.randomTick(state, level, pos, rng);
    }

    /* ---------- Gate bonemeal as well (optional but recommended) ---------- */

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource rng, BlockPos pos, BlockState state) {
        return hasRequiredBlockNearby(level, pos) && super.isBonemealSuccess(level, rng, pos, state);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rng, BlockPos pos, BlockState state) {
        if (!hasRequiredBlockNearby(level, pos)) return;
        super.performBonemeal(level, rng, pos, state);
    }

    /* ---------- Helper: scan for required block/tag within RADIUS ---------- */

    private boolean hasRequiredBlockNearby(LevelReader level, BlockPos pos) {
        // Cheap early exit: check a small cube using withinManhattan
        for (BlockPos p : BlockPos.withinManhattan(pos, RADIUS, RADIUS, RADIUS)) {
            BlockState bs = level.getBlockState(p);
            if (requiredBlock != null && bs.is(requiredBlock)) return true;
            if (requiredTag != null && bs.is(requiredTag)) return true;
        }
        return false;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.HUMAN_REALM_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
