package net.ejibarra.pathtoimmortals.util;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.minecraft.core.registries.Registries.BLOCK;

public class ModTags {

    public static class Blocks{
        public static final TagKey<Block> NEEDS_JADED_IRON_TOOL = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "needs_jaded_iron_tool"));
        public static final TagKey<Block> PAXEL_MINEABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "mineable/paxel"));//duplicate expression for more tiers

        public static final TagKey<Block> SPIRIT_MOSS_SPREADABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "spirit_moss_spreadable"));
        public static final TagKey<Block> OCTOS_GROWTH_SPREADABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "octos_growth_spreadable"));
        public static final TagKey<Block> LEMON_MOSS_SPREADABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "lemon_moss_spreadable"));
        public static final TagKey<Block> ROSY_MOSS_SPREADABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "rosy_moss_spreadable"));
        public static final TagKey<Block> EVES_BLUES_SPREADABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "eves_blues_spreadable"));
        public static final TagKey<Block> FEYS_SORROW_SPREADABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "feys_sorrow_spreadable"));

        public static final TagKey<Block> EVES_REPLACEABLE = TagKey.create(BLOCK, new ResourceLocation(PathToImmortals.MOD_ID, "eves_replaceable"));

    }
    public static class Items{

        public static final TagKey<Item> HUMAN_GRADE_SOUL_LOWER = tag("human_grade_soul_lower");
        public static final TagKey<Item> HUMAN_GRADE_SOUL_MEDIUM = tag("human_grade_soul_medium");
        public static final TagKey<Item> HUMAN_GRADE_SOUL_UPPER = tag("human_grade_soul_upper");
        public static final TagKey<Item> HUMAN_GRADE_SOULS = tag("human_grade_souls");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(PathToImmortals.MOD_ID, name));
        }
    }
}
