package net.ejibarra.pathtoimmortals.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class EnergyUtils {
    public static int pushEnergyToNeighbors(Level level, BlockPos pos, int perSideLimit) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) return 0;

        int totalPushed = 0;

        for (Direction dir : Direction.values()) {
            var selfCap = be.getCapability(ForgeCapabilities.ENERGY, dir).orElse(null);
            if (selfCap == null || !selfCap.canExtract()) continue;

            BlockPos neighborPos = pos.relative(dir);
            var neighborBe = level.getBlockEntity(neighborPos);
            if (neighborBe == null) continue;

            var neighborCap = neighborBe.getCapability(ForgeCapabilities.ENERGY, dir.getOpposite()).orElse(null);
            if (neighborCap == null || !neighborCap.canReceive()) continue;

            int available = selfCap.extractEnergy(perSideLimit, true);
            if (available <= 0) continue;

            int accepted = neighborCap.receiveEnergy(available, false);
            if (accepted > 0) {
                selfCap.extractEnergy(accepted, false);
                totalPushed += accepted;
            }
        }
        return totalPushed;
    }

    // Solar gen scaling for tiers 1..5
    public static int solarGenPerTick(int tier) {
        return switch (tier) {
            case 1 -> 20;
            case 2 -> 60;
            case 3 -> 120;
            case 4 -> 240;
            default -> 480; // tier 5
        };
    }
}
