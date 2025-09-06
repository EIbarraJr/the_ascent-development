package net.ejibarra.pathtoimmortals.datagen;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.ejibarra.pathtoimmortals.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, future, completableFuture, PathToImmortals.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    //Add Tags Here
        // Child tags: each item goes into its tier tag
        this.tag(ModTags.Items.HUMAN_GRADE_SOUL_LOWER)
                .add(ModItems.HUMAN_GRADE_SOUL_LOWER.get());

        this.tag(ModTags.Items.HUMAN_GRADE_SOUL_MEDIUM)
                .add(ModItems.HUMAN_GRADE_SOUL_MEDIUM.get());

        this.tag(ModTags.Items.HUMAN_GRADE_SOUL_UPPER)
                .add(ModItems.HUMAN_GRADE_SOUL_UPPER.get());

        // Parent tag: includes all three child tags
        this.tag(ModTags.Items.HUMAN_GRADE_SOULS)
                .addTag(ModTags.Items.HUMAN_GRADE_SOUL_LOWER)
                .addTag(ModTags.Items.HUMAN_GRADE_SOUL_MEDIUM)
                .addTag(ModTags.Items.HUMAN_GRADE_SOUL_UPPER);

    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
