package net.ejibarra.pathtoimmortals.item;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public final class ModToolTiers {
    public static final Tier Jaded_Iron = TierSortingRegistry.registerTier(
            new ForgeTier(5, 3300, 1.6f, 10f, 25,
                    ModTags.Blocks.NEEDS_JADED_IRON_TOOL, ()-> Ingredient.of(ModItems.SPIRIT_IRON_TIER1.get())),
            new ResourceLocation(PathToImmortals.MOD_ID, "spirit_iron_tier1"), List.of(Tiers.NETHERITE), List.of()
    );
}
