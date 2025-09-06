package net.ejibarra.pathtoimmortals.datagen.loot;

import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //Blocks
        this.dropSelf(ModBlocks.JADE_BLOCK.get());
        this.dropSelf(ModBlocks.SPIRIT_BLOCK.get());
        this.dropSelf(ModBlocks.PURPLE_BLOCK.get());
        this.dropSelf(ModBlocks.SAINT_BLOCK.get());
        this.dropSelf(ModBlocks.DIVINE_BLOCK_BLANK.get());
        this.dropSelf(ModBlocks.INFUSTION_TABLE_T1.get());
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK.get());
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED_RUNIC.get());
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get());
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_CRACKED.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_RUNIC.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_CRACKED.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_CRACKED_RUNIC.get());
        this.dropSelf(ModBlocks.CHISELED_DIVINE_BLOCK_NORMAL.get());
        this.dropSelf(ModBlocks.CHISELED_DIVINE_BLOCK_SIDEWAYS.get());
        this.dropSelf(ModBlocks.SPIRIT_BRICK.get());
        this.dropSelf(ModBlocks.PURPLE_BRICK.get());
        this.dropSelf(ModBlocks.SAINT_BRICK.get());

        //Customs

        //STAIRS
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_STAIRS.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_RUNIC_STAIRS.get());
        this.dropSelf(ModBlocks.SPIRIT_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.PURPLE_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.SAINT_BRICK_STAIRS.get());

        //SLABS
        this.add(ModBlocks.LIGHT_DIVINE_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.LIGHT_DIVINE_BRICK_SLAB.get()));
        this.add(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB.get(), block -> createSlabItemTable(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB.get()));
        this.add(ModBlocks.DARK_DIVINE_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.DARK_DIVINE_BRICK_SLAB.get()));
        this.add(ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB.get(), block -> createSlabItemTable(ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB.get()));
        this.add(ModBlocks.SPIRIT_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.SPIRIT_BRICK_SLAB.get()));
        this.add(ModBlocks.PURPLE_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.PURPLE_BRICK_SLAB.get()));
        this.add(ModBlocks.SAINT_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.SAINT_BRICK_SLAB.get()));

        //WALLS
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL.get());
        this.dropSelf(ModBlocks.SPIRIT_BRICK_WALL.get());
        this.dropSelf(ModBlocks.PURPLE_BRICK_WALL.get());
        this.dropSelf(ModBlocks.SAINT_BRICK_WALL.get());

        //ORES
        this.add(ModBlocks.JADE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.JADE_ORE.get(), ModItems.JADE_COIN.get()));

        this.add(ModBlocks.SPIRIT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SPIRIT_ORE.get(), ModItems.SPIRIT_COIN.get()));

        this.add(ModBlocks.PURPLE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.PURPLE_ORE.get(), ModItems.PURPLE_COIN.get()));

        this.add(ModBlocks.SAINT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SAINT_ORE.get(), ModItems.SAINT_COIN.get()));

        this.add(ModBlocks.DIVINE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DIVINE_ORE.get(), ModItems.DIVINE_COIN.get()));

        this.add(ModBlocks.DEEPSLATE_JADE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_JADE_ORE.get(), ModItems.JADE_COIN.get()));

        this.add(ModBlocks.DEEPSLATE_SPIRIT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SPIRIT_ORE.get(), ModItems.SPIRIT_COIN.get()));

        this.add(ModBlocks.DEEPSLATE_PURPLE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_PURPLE_ORE.get(), ModItems.PURPLE_COIN.get()));

        this.add(ModBlocks.DEEPSLATE_SAINT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SAINT_ORE.get(), ModItems.SAINT_COIN.get()));

        this.add(ModBlocks.DEEPSLATE_DIVINE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_DIVINE_ORE.get(), ModItems.DIVINE_COIN.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 13.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
