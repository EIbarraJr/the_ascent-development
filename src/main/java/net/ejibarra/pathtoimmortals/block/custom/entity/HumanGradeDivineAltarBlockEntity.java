package net.ejibarra.pathtoimmortals.block.custom.entity;

import net.ejibarra.pathtoimmortals.block.custom.HumanGradeDivineAltar;
import net.ejibarra.pathtoimmortals.block.custom.InfusionTableBlock1;
import net.ejibarra.pathtoimmortals.item.ModItems;
import net.ejibarra.pathtoimmortals.recipe.HumanGradeInfuserRecipe;
import net.ejibarra.pathtoimmortals.screen.HumanGradeDivineAltarMenu;
import net.ejibarra.pathtoimmortals.util.InventoryDirectionEntry;
import net.ejibarra.pathtoimmortals.util.InventoryDirectionWrapper;
import net.ejibarra.pathtoimmortals.util.ModTags;
import net.ejibarra.pathtoimmortals.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class HumanGradeDivineAltarBlockEntity extends BlockEntity implements MenuProvider {

    // === Adjust these to your registry objects ===
    private static final Supplier<Item> INFUSED_JADE  = ModItems.INFUSED_JADE;
    private static final Supplier<Item> DIVINE_IRON   = ModItems.DIVINE_METAL_TIER1;
    private static final Supplier<Item> SPIRIT_IRON   = ModItems.SPIRIT_IRON_TIER1;
    private static final Supplier<Item> DIVINE_STRAND = ModItems.DIVINE_COIN;

    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0, 1 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();
    private final RandomSource rng = RandomSource.create();
    //ADDING INVENTORY SIDES FOR I/P O/P
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            new InventoryDirectionWrapper(itemHandler,
                    new InventoryDirectionEntry(Direction.DOWN, OUTPUT_SLOT, false),
                    new InventoryDirectionEntry(Direction.NORTH, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.SOUTH, OUTPUT_SLOT, false),
                    new InventoryDirectionEntry(Direction.EAST, OUTPUT_SLOT, false),
                    new InventoryDirectionEntry(Direction.WEST, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.UP, INPUT_SLOT, true)).directionsMap;

    // --- CONFIG ---
    private static final int PARTICLE_EVERY = 5;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20 * 5;

    public ItemStack getRenderStack() {
        ItemStack stack = itemHandler.getStackInSlot(OUTPUT_SLOT);

        if(stack.isEmpty()) {
            stack = itemHandler.getStackInSlot(INPUT_SLOT);
        }

        return stack;
    }

    public HumanGradeDivineAltarBlockEntity(BlockPos pPos, BlockState blockState) {
        super(ModBlockEntities.HUMAN_GRADE_DIVINE_ALTAR_BE.get(), pPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> HumanGradeDivineAltarBlockEntity.this.progress;
                    case 1 -> HumanGradeDivineAltarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> HumanGradeDivineAltarBlockEntity.this.progress = pValue;
                    case 1 -> HumanGradeDivineAltarBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {


        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(HumanGradeDivineAltar.FACING);

                if(side == Direction.DOWN ||side == Direction.UP) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();

    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("human_grade_divine_altar.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("human_grade_divine_altar.progress");
    }
    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private void increaseCraftingProcess() {
        this.progress++;
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() >=
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    //TICK WILL DEFINE THE WORK DONE BY BE
    public void tick(Level level, BlockPos pPos, BlockState pState) {

        if(!this.canProcessOne()){
            this.progress = 0;
            return;
        }
        this.progress++;
        if(this.progress % PARTICLE_EVERY == 0) this.spawnRitualParticles();
        if (this.progress >= maxProgress){
            this.processOneCycle();
            this.resetProgress();
        }
        setChanged(level, pPos, pState);
    }

    private boolean canProcessOne(){
        ItemStack in = itemHandler.getStackInSlot(0);
        if (in.isEmpty() || !isSoul(in)) return false;
        ItemStack out = itemHandler.getStackInSlot(1);
        if (out.isEmpty()) return true;
        if (out.getCount() >= out.getMaxStackSize()) return false;
        Item oi = out.getItem();
        return oi == ModItems.INFUSED_JADE.get()
                || oi == ModItems.DIVINE_METAL_TIER1.get()
                || oi == ModItems.SPIRIT_IRON_TIER1.get()
                || oi == ModItems.DIVINE_COIN.get();
    }

    private void processOneCycle(){
        ItemStack in = itemHandler.getStackInSlot(0);
        if (in.isEmpty() || !isSoul(in)) return;

        SoulTier tier = getTier(in);
        RewardEntry picked = pickReward(tier);

        // consume 1 soul per cycle
        in.shrink(1);
        if (in.isEmpty()) itemHandler.setStackInSlot(0, ItemStack.EMPTY);

        if (picked == null){ fizzledFX(); setChanged(); return; }

        int count = Mth.nextInt(rng, picked.minCount(), picked.maxCount());
        ItemStack payout = new ItemStack(picked.item().get(), count);
        ItemStack out = itemHandler.getStackInSlot(1);

        if (out.isEmpty()){
            itemHandler.setStackInSlot(1, payout);
        } else if (ItemStack.isSameItemSameTags(out, payout)){
            int space = out.getMaxStackSize() - out.getCount();
            int toAdd = Math.min(space, payout.getCount());
            if (toAdd > 0){ out.grow(toAdd); itemHandler.setStackInSlot(1, out); }
            int leftover = payout.getCount() - toAdd;
            if (leftover > 0) Containers.dropItemStack(level, worldPosition.getX()+0.5, worldPosition.getY()+1, worldPosition.getZ()+0.5,
                    new ItemStack(payout.getItem(), leftover));
        } else {
            Containers.dropItemStack(level, worldPosition.getX()+0.5, worldPosition.getY()+1, worldPosition.getZ()+0.5, payout);
        }

        successFX();
        setChanged();
    }


    // ---- Soul tier detection via tags ----
    public boolean isSoul(ItemStack stack) {
        return stack.is(ModTags.Items.HUMAN_GRADE_SOUL_LOWER) || stack.is(ModTags.Items.HUMAN_GRADE_SOUL_MEDIUM) || stack.is(ModTags.Items.HUMAN_GRADE_SOUL_UPPER);
    }

    private SoulTier getTier(ItemStack stack) {
        if (stack.is(ModTags.Items.HUMAN_GRADE_SOUL_UPPER))  return SoulTier.UPPER;
        if (stack.is(ModTags.Items.HUMAN_GRADE_SOUL_MEDIUM)) return SoulTier.MEDIUM;
        return SoulTier.LOWER;
    }
    // ---- Probability tables ----//keep
    private enum SoulTier { LOWER, MEDIUM, UPPER }

    private record RewardEntry(Supplier<Item> item, int weight, int minCount, int maxCount) {}

    // You can tweak min/max to taste.
    // One reward type per soul; counts vary per roll.
    private static final List<RewardEntry> LOWER_TABLE = List.of(
            new RewardEntry(INFUSED_JADE,  50, 2, 5),  // 50%
            new RewardEntry(DIVINE_IRON,   10, 1, 1),  // 10%
            new RewardEntry(SPIRIT_IRON,   20, 2, 6),  // 20%
            new RewardEntry(DIVINE_STRAND, 10, 1, 1)   // 10%
            // sums to 90% → 10% = no reward
    );
    private static final List<RewardEntry> MEDIUM_TABLE = List.of(
            new RewardEntry(INFUSED_JADE,  35, 4, 9),  // 35%
            new RewardEntry(DIVINE_IRON,   20, 2, 3),  // 20%
            new RewardEntry(SPIRIT_IRON,   15, 3, 7),  // 15%
            new RewardEntry(DIVINE_STRAND, 20, 2, 3)   // 20%
            // sums to 90% → 10% = no reward
    );
    private static final List<RewardEntry> UPPER_TABLE = List.of(
            new RewardEntry(INFUSED_JADE,  10, 8, 13), // 10%
            new RewardEntry(DIVINE_IRON,   50, 5, 10),  // 50%
            new RewardEntry(SPIRIT_IRON,   10, 6, 10),  // 10%
            new RewardEntry(DIVINE_STRAND, 30, 4, 8)   // 30% (sums to 100%)
    );

    @Nullable
    private RewardEntry pickReward(SoulTier tier) {
        List<RewardEntry> table = switch (tier) {
            case LOWER -> LOWER_TABLE;
            case MEDIUM -> MEDIUM_TABLE;
            case UPPER -> UPPER_TABLE;
        };

        int total = table.stream().mapToInt(RewardEntry::weight).sum();
        if (total <= 0) return null;

        int roll = rng.nextInt(100); // interpret weights as percents out of 100
        int acc = 0;
        for (RewardEntry e : table) {
            acc += e.weight();
            if (roll < acc) return e;
        }
        // If weights < 100 and roll fell into the “gap”, return null = no reward.
        return null;
    }

    // --- RUNTIME STATE ---
    //private int ritualTicks = 0;
    //private boolean active = false;   // mirrors blockstate for model/FX

    // (… keep items, caps, tables, isSoul(), getTier(), pickReward(), etc. as before …)
    private void spawnRitualParticles() {
        if (level == null) return;
        ((ServerLevel)level).sendParticles(ParticleTypes.ENCHANT,
                worldPosition.getX() + 0.5, worldPosition.getY() + 1.1, worldPosition.getZ() + 0.5,
                6, 0.25, 0.1, 0.25, 0.0);
    }
    private void successFX() {
        if (level == null) return;
        level.playSound(null, worldPosition, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 0.7f, 1.0f);
        ((ServerLevel)level).sendParticles(ParticleTypes.HAPPY_VILLAGER,
                worldPosition.getX()+0.5, worldPosition.getY()+1.2, worldPosition.getZ()+0.5,
                4, 0.2, 0.1, 0.2, 0.0);
    }

    private void fizzledFX() {
        if (level == null) return;
        level.playSound(null, worldPosition, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.4f, 1.2f);
        ((ServerLevel)level).sendParticles(ParticleTypes.SMOKE,
                worldPosition.getX()+0.5, worldPosition.getY()+1.0, worldPosition.getZ()+0.5,
                6, 0.25, 0.1, 0.25, 0.01);
    }

    @Override
    public @org.jetbrains.annotations.Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    // (Optional) If you later want a GUI, wire a Menu here:
    @Override public Component getDisplayName() {
        return Component.translatable("block.pathtoimmortals.human_grade_divine_altar");
    }
    @Nullable @Override public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player player) {
        return new HumanGradeDivineAltarMenu(pContainerId, pPlayerInventory, this, this.data); // no GUI for now
    }

}
