package net.ejibarra.pathtoimmortals.block.custom;

import net.ejibarra.pathtoimmortals.worldgen.dimensions.ModDimensions;
import net.ejibarra.pathtoimmortals.worldgen.portal.HumanRealmTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class HumanRealmPortalBlock extends Block {
    public HumanRealmPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handleHumanPortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    private void handleHumanPortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == ModDimensions.HUMAN_LEVEL_KEY ?
                    Level.OVERWORLD : ModDimensions.HUMAN_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if(resourcekey == ModDimensions.HUMAN_LEVEL_KEY) {
                    player.changeDimension(portalDimension, new HumanRealmTeleporter(pPos, true));
                } else {
                    player.changeDimension(portalDimension, new HumanRealmTeleporter(pPos, false));
                }
            }
        }
    }
}
