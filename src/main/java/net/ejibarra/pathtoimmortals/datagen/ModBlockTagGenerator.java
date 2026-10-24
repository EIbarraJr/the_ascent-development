package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PathToImmortals.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.DEEPSLATE_JADE_ORE.get(),
                        ModBlocks.JADE_ORE.get(),
                        ModBlocks.DESOLATE_SANDSTONE.get());//END OF NEEDS_NETHERITETOOL

        this.tag(ModTags.Blocks.NEEDS_JADED_IRON_TOOL)
                .add(ModBlocks.SPIRIT_ORE.get())
                .add(ModBlocks.DEEPSLATE_SPIRIT_ORE.get())
                .add(ModBlocks.PURPLE_ORE.get())
                .add(ModBlocks.DEEPSLATE_PURPLE_ORE.get())
                .add(ModBlocks.SAINT_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAINT_ORE.get())
                .add(ModBlocks.DIVINE_ORE.get())
                .add(ModBlocks.DEEPSLATE_DIVINE_ORE.get())
                .add(ModBlocks.PORTAL_BLOCK_HUMAN_REALM.get())
                .add(ModBlocks.HUMAN_GRADE_DIVINE_ALTAR_BLOCK.get())
                .add(ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get());
        //SWITCH ALL ORES TO THEIR RESPECTIVE TOOL LEVELS IN FUTURE

        this.tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.JADE_ORE.get(),
                        ModBlocks.PORTAL_BLOCK_HUMAN_REALM.get(),
                        ModBlocks.HUMAN_GRADE_INFUSER_BLOCK.get(),
                        ModBlocks.HUMAN_GRADE_DIVINE_ALTAR_BLOCK.get(),
                        ModBlocks.DEEPSLATE_JADE_ORE.get(),
                        ModBlocks.SPIRIT_ORE.get(),
                        ModBlocks.DEEPSLATE_SPIRIT_ORE.get(),
                        ModBlocks.PURPLE_ORE.get(),
                        ModBlocks.DEEPSLATE_PURPLE_ORE.get(),
                        ModBlocks.SAINT_ORE.get(),
                        ModBlocks.DEEPSLATE_SAINT_ORE.get(),
                        ModBlocks.DIVINE_ORE.get(),
                        ModBlocks.DEEPSLATE_DIVINE_ORE.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_CRACKED.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_CRACKED_RUNIC.get(),
                        ModBlocks.CHISELED_DIVINE_BLOCK_NORMAL.get(),
                        ModBlocks.CHISELED_DIVINE_BLOCK_SIDEWAYS.get(),
                        ModBlocks.DARK_DIVINE_BRICK.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC.get(),
                        ModBlocks.DARK_DIVINE_BRICK_CRACKED.get(),
                        ModBlocks.DARK_DIVINE_BRICK_CRACKED_RUNIC.get(),
                        ModBlocks.SPIRIT_BRICK.get(),
                        ModBlocks.PURPLE_BRICK.get(),
                        ModBlocks.SAINT_BRICK.get(),
                        ModBlocks.EVES_COBBLESTONE.get(),
                        ModBlocks.DESOLATE_SANDSTONE.get(),

                        //Stairs
                        ModBlocks.LIGHT_DIVINE_BRICK_STAIRS.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_STAIRS.get(),
                        ModBlocks.DARK_DIVINE_BRICK_STAIRS.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_STAIRS.get(),
                        ModBlocks.SPIRIT_BRICK_STAIRS.get(),
                        ModBlocks.PURPLE_BRICK_STAIRS.get(),
                        ModBlocks.SAINT_BRICK_STAIRS.get(),


                        //Slabs
                        ModBlocks.LIGHT_DIVINE_BRICK_SLAB.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB.get(),
                        ModBlocks.DARK_DIVINE_BRICK_SLAB.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB.get(),
                        ModBlocks.SPIRIT_BRICK_SLAB.get(),
                        ModBlocks.PURPLE_BRICK_SLAB.get(),
                        ModBlocks.SAINT_BRICK_SLAB.get(),

                        //WALL
                        ModBlocks.LIGHT_DIVINE_BRICK_WALL.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL.get(),
                        ModBlocks.DARK_DIVINE_BRICK_WALL.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL.get(),
                        ModBlocks.SPIRIT_BRICK_WALL.get(),
                        ModBlocks.PURPLE_BRICK_WALL.get(),
                        ModBlocks.SAINT_BRICK_WALL.get());//END OF MINEABLE W PICKAXE



        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LIGHT_DIVINE_BRICK.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_CRACKED.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_CRACKED_RUNIC.get(),
                        ModBlocks.CHISELED_DIVINE_BLOCK_NORMAL.get(),
                        ModBlocks.CHISELED_DIVINE_BLOCK_SIDEWAYS.get(),
                        ModBlocks.DARK_DIVINE_BRICK.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC.get(),
                        ModBlocks.DARK_DIVINE_BRICK_CRACKED.get(),
                        ModBlocks.DARK_DIVINE_BRICK_CRACKED_RUNIC.get(),
                        ModBlocks.SPIRIT_BRICK.get(),
                        ModBlocks.PURPLE_BRICK.get(),
                        ModBlocks.SAINT_BRICK.get(),
                        ModBlocks.EVES_COBBLESTONE.get(),

                        //STAIRS
                        ModBlocks.LIGHT_DIVINE_BRICK_STAIRS.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_STAIRS.get(),
                        ModBlocks.DARK_DIVINE_BRICK_STAIRS.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_STAIRS.get(),
                        ModBlocks.SPIRIT_BRICK_STAIRS.get(),
                        ModBlocks.PURPLE_BRICK_STAIRS.get(),
                        ModBlocks.SAINT_BRICK_STAIRS.get(),

                        //SLABS
                        ModBlocks.LIGHT_DIVINE_BRICK_SLAB.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_SLAB.get(),
                        ModBlocks.DARK_DIVINE_BRICK_SLAB.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_SLAB.get(),
                        ModBlocks.SPIRIT_BRICK_SLAB.get(),
                        ModBlocks.PURPLE_BRICK_SLAB.get(),
                        ModBlocks.SAINT_BRICK_SLAB.get(),

                        //WALLS
                        ModBlocks.LIGHT_DIVINE_BRICK_WALL.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL.get(),
                        ModBlocks.DARK_DIVINE_BRICK_WALL.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL.get(),
                        ModBlocks.SPIRIT_BRICK_WALL.get(),
                        ModBlocks.PURPLE_BRICK_WALL.get(),
                        ModBlocks.SAINT_BRICK_WALL.get());//END OF NEEDS IRON TOOL

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.LIGHT_DIVINE_BRICK_WALL.get(),
                        ModBlocks.LIGHT_DIVINE_BRICK_RUNIC_WALL.get(),
                        ModBlocks.DARK_DIVINE_BRICK_WALL.get(),
                        ModBlocks.DARK_DIVINE_BRICK_RUNIC_WALL.get(),
                        ModBlocks.SPIRIT_BRICK_WALL.get(),
                        ModBlocks.PURPLE_BRICK_WALL.get(),
                        ModBlocks.SAINT_BRICK_WALL.get());//END OF WALLS TAG

        this.tag(ModTags.Blocks.SPIRIT_MOSS_SPREADABLE)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.RED_SAND)
                .add(Blocks.SAND)
                .add(Blocks.STONE);
        this.tag(ModTags.Blocks.LEMON_MOSS_SPREADABLE)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.RED_SAND)
                .add(Blocks.SAND)
                .add(Blocks.STONE);
        this.tag(ModTags.Blocks.ROSY_MOSS_SPREADABLE)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.RED_SAND)
                .add(Blocks.SAND)
                .add(Blocks.STONE);
        this.tag(ModTags.Blocks.OCTOS_GROWTH_SPREADABLE)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.RED_SAND)
                .add(Blocks.SAND)
                .add(Blocks.STONE);
        this.tag(ModTags.Blocks.FEYS_SORROW_SPREADABLE)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.RED_SAND)
                .add(Blocks.SAND)
                .add(Blocks.STONE);
        this.tag(ModTags.Blocks.EVES_BLUES_SPREADABLE)
                .add(Blocks.DIRT)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.RED_SAND)
                .add(Blocks.SAND)
                .add(Blocks.STONE);

        this.tag(BlockTags.DIRT)
                .add(ModBlocks.SPIRIT_MOSS.get())
                .add(ModBlocks.LEMON_MOSS.get())
                .add(ModBlocks.ROSY_MOSS.get())
                .add(ModBlocks.OCTOS_GROWTH.get())
                .add(ModBlocks.FEYS_SORROW.get())
                .add(ModBlocks.EVES_BLUES.get());

        this.tag(BlockTags.NYLIUM)
                .add(ModBlocks.SPIRIT_MOSS.get())
                .add(ModBlocks.LEMON_MOSS.get())
                .add(ModBlocks.ROSY_MOSS.get())
                .add(ModBlocks.OCTOS_GROWTH.get())
                .add(ModBlocks.FEYS_SORROW.get())
                .add(ModBlocks.EVES_BLUES.get());

        this.tag(BlockTags.SAND)
                .add(ModBlocks.DESOLATE_SAND.get())
                .add(ModBlocks.TRAPPED_SOUL_SAND.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.FIERO_LEAVES.get())
                .add(ModBlocks.SPIRIT_BLOSSOM_LEAVES.get())
                .add(ModBlocks.MANGO_LEAVES.get())
                .add(ModBlocks.KINDER_LEAVES.get())
                .add(ModBlocks.JADED_LEAVES.get())
                .add(ModBlocks.JASMINE_LEAVES.get());


    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
