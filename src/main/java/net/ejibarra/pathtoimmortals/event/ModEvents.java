package net.ejibarra.pathtoimmortals.event;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        // Only continue when the dead entity is the Ender Dragon.
        if (!(event.getEntity() instanceof EnderDragon dragon)) {
            return;
        }

        // Entity events can occur on both logical sides.
        // Items must only be created by the server.
        if (dragon.level().isClientSide()) {
            return;
        }

        ItemStack reward = new ItemStack(
                ModBlocks.HUMAN_REALM_PORTAL.get(),
                1
        );

        dragon.spawnAtLocation(reward);
    }

    private static final String RECEIVED_STARTER_ITEM =
            "pathtoimmortals_received_starter_item";

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        /*
         * Data placed inside Player.PERSISTED_NBT_TAG is preserved
         * when the player dies and is cloned.
         */
        CompoundTag playerData = player.getPersistentData();

        CompoundTag persistedData;
        if (playerData.contains(Player.PERSISTED_NBT_TAG)) {
            persistedData = playerData.getCompound(Player.PERSISTED_NBT_TAG);
        } else {
            persistedData = new CompoundTag();
        }

        if (persistedData.getBoolean(RECEIVED_STARTER_ITEM)) {
            return;
        }

        ItemStack starterItem = new ItemStack(ModItems.INFUSED_JADE.get(), 1);

        /*
         * addItem returns false when the player's inventory is full.
         * In that case, drop the item beside the player.
         */
        if (!player.getInventory().add(starterItem)) {
            player.drop(starterItem, false);
        }

        persistedData.putBoolean(RECEIVED_STARTER_ITEM, true);
        playerData.put(Player.PERSISTED_NBT_TAG, persistedData);
    }
}
