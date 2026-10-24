package net.ejibarra.pathtoimmortals.datagen.loot;

import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.block.custom.HumanRealmCropBlock;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
        this.dropSelf(ModBlocks.SPIRIT_MOSS_CARPET.get());
        this.dropSelf(ModBlocks.LEMON_MOSS_CARPET.get());
        this.dropSelf(ModBlocks.ROSY_MOSS_CARPET.get());
        this.dropSelf(ModBlocks.OCTOS_GROWTH_CARPET.get());
        this.dropSelf(ModBlocks.FEYS_SORROW_CARPET.get());
        this.dropSelf(ModBlocks.EVES_BLUES_CARPET.get());
        this.dropSelf(ModBlocks.SPIRIT_MOSS.get());
        this.dropSelf(ModBlocks.LEMON_MOSS.get());
        this.dropSelf(ModBlocks.ROSY_MOSS.get());
        this.dropSelf(ModBlocks.OCTOS_GROWTH.get());
        this.dropSelf(ModBlocks.FEYS_SORROW.get());
        this.dropSelf(ModBlocks.EVES_BLUES.get());
        this.dropSelf(ModBlocks.FEY_CLUSTER.get());
        this.dropSelf(ModBlocks.TURQUOISE_CLUSTER.get());
        this.dropSelf(ModBlocks.TURQUOISE_BLOCK.get());
        this.dropSelf(ModBlocks.FEY_BLOCK.get());
        this.dropSelf(ModBlocks.LARGE_FEY_BUD.get());
        this.dropSelf(ModBlocks.LARGE_TURQUOISE_BUD.get());
        this.dropSelf(ModBlocks.MEDIUM_FEY_BUD.get());
        this.dropSelf(ModBlocks.MEDIUM_TURQUOISE_BUD.get());
        this.dropSelf(ModBlocks.SMALL_TURQUOISE_BUD.get());
        this.dropSelf(ModBlocks.SMALL_FEY_BUD.get());
        this.dropSelf(ModBlocks.TRAPPED_SOUL_SAND.get());
        this.dropSelf(ModBlocks.DESOLATE_SAND.get());
        this.dropSelf(ModBlocks.DESOLATE_SANDSTONE.get());
        this.dropSelf(ModBlocks.EVES_COBBLESTONE.get());
        this.dropSelf(ModBlocks.POISON_MUSHROOM_BLOCK.get());
        this.dropSelf(ModBlocks.PURPLE_BIOSHROOM.get());
        this.dropSelf(ModBlocks.PORTAL_BLOCK_HUMAN_REALM.get());

        this.add(ModBlocks.BLUE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.PINK_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.WHITE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.PURPLE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.LAVENDER_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.YELLOW_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.ORANGE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.SPIRIT_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.SPIRIT_FAE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.FAE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.MAHOGANY_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.MIDNIGHTS_REACH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.COTTONS_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.CARMINE_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.CERULEAN_ROSE_BUSH.get(), block -> createSinglePropConditionTable(block, TallFlowerBlock.HALF, DoubleBlockHalf.LOWER));


        this.dropSelf(ModBlocks.INFUSED_JADE_BLOCK.get());


        this.dropSelf(ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get());
        this.dropSelf(ModBlocks.HUMAN_GRADE_DIVINE_ALTAR_BLOCK.get());

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

        //CROPS
        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HUMAN_REALM_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HumanRealmCropBlock.AGE, 5));
        this.add(ModBlocks.HUMAN_REALM_CROP.get(), this.createCropDrops(ModBlocks.HUMAN_REALM_CROP.get(),
                ModItems.ORIGIN_BERRIES.get(), ModItems.HUMAN_REALM_SEEDS.get(), lootitemcondition$builder1));

        //TREES
        this.add(ModBlocks.FIERO_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.FIERO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.FIERO_SAPLING.get());
        this.add(ModBlocks.JASMINE_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.JASMINE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.JASMINE_SAPLING.get());
        this.add(ModBlocks.KINDER_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.KINDER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.KINDER_SAPLING.get());
        this.add(ModBlocks.MANGO_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.MANGO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.MANGO_SAPLING.get());
        this.add(ModBlocks.SPIRIT_BLOSSOM_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.SPIRIT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.SPIRIT_SAPLING.get());
        this.add(ModBlocks.JADED_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.JADED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.JADED_SAPLING.get());


    }

    // Put this at class scope (inside ModBlockLootTables, but OUTSIDE any method)
    private static LootItemCondition.Builder amountIs(Block block, int n) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(PinkPetalsBlock.AMOUNT, n));
    }

    private void petalsDrops(Block petalsBlock) {
        var entry1 = LootItem.lootTableItem(petalsBlock)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                .when(amountIs(petalsBlock, 1));

        var entry2 = LootItem.lootTableItem(petalsBlock)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))
                .when(amountIs(petalsBlock, 2));

        var entry3 = LootItem.lootTableItem(petalsBlock)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
                .when(amountIs(petalsBlock, 3));

        var entry4 = LootItem.lootTableItem(petalsBlock)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))
                .when(amountIs(petalsBlock, 4));

        add(petalsBlock,
                LootTable.lootTable().withPool(
                        applyExplosionDecay(petalsBlock,
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(AlternativesEntry.alternatives(entry4, entry3, entry2, entry1))
                        )
                )
        );
    }


    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
