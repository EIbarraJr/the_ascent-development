package net.ejibarra.pathtoimmortals.util;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks{
        public static final TagKey<Block> NEEDS_JADED_IRON_TOOL =
                TagKey.create(Registries.BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "needs_jaded_iron_tool"));
        public static final TagKey<Block> PAXEL_MINEABLE =
                TagKey.create(Registries.BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "mineable/paxel"));//duplicate expression for more tiers

    }
}
