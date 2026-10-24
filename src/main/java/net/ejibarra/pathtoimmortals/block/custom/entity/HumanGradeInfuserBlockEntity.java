package net.ejibarra.pathtoimmortals.block.custom.entity;

import net.ejibarra.pathtoimmortals.block.custom.HumanGradeInfuserBlock;
import net.ejibarra.pathtoimmortals.recipe.HumanGradeInfuserRecipe;
import net.ejibarra.pathtoimmortals.screen.HumanGradeInfuserMenu;
import net.ejibarra.pathtoimmortals.util.InventoryDirectionEntry;
import net.ejibarra.pathtoimmortals.util.InventoryDirectionWrapper;
import net.ejibarra.pathtoimmortals.util.WrappedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class HumanGradeInfuserBlockEntity extends BlockEntity implements MenuProvider {

    @Override
    public Component getDisplayName() {
        return Component.literal("Human Grade Infuser");
    }

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

    //ADDING INVENTORY SIDES FOR I/P O/P
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            new InventoryDirectionWrapper(itemHandler,
                    new InventoryDirectionEntry(Direction.DOWN, OUTPUT_SLOT, false),
                    new InventoryDirectionEntry(Direction.NORTH, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.SOUTH, OUTPUT_SLOT, false),
                    new InventoryDirectionEntry(Direction.EAST, OUTPUT_SLOT, false),
                    new InventoryDirectionEntry(Direction.WEST, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.UP, INPUT_SLOT, true)).directionsMap;



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


    public HumanGradeInfuserBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.HUMAN_GRADE_INFUSER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> HumanGradeInfuserBlockEntity.this.progress;
                    case 1 -> HumanGradeInfuserBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> HumanGradeInfuserBlockEntity.this.progress = pValue;
                    case 1 -> HumanGradeInfuserBlockEntity.this.maxProgress = pValue;
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
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new HumanGradeInfuserMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(HumanGradeInfuserBlock.FACING);

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
        pTag.putInt("human_grade_infuser.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("human_grade_infuser.progress");
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {

        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
            increaseCraftingProcess();

            setChanged(level, pPos, pState);

            // While infusing: subtle rotating rings every few ticks (client-only)
            if (level.isClientSide && progress > 0) {
                if (level.getGameTime() % 4 == 0) {
                    // Slightly vary radius to feel alive; END_ROD looks great
                    double base = 0.35;
                    double wobble = Math.sin(level.getGameTime() * 0.2) * 0.05;
                    spawnRingClient(level, pPos, ParticleTypes.END_ROD,
                            base + wobble, /*points*/ 18, /*yOff*/ 0.95, /*speed*/ 0.015);
                }
            }

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();

                if (!level.isClientSide) {
                    ServerLevel sl = (ServerLevel) level;
                    // One or two concentric rings for drama
                    spawnRingServer(sl, pPos, ParticleTypes.END_ROD, 0.45, 26, 1.00);
                    spawnRingServer(sl, pPos, ParticleTypes.CRIT,    0.65, 32, 1.02);
                    sl.playSound(null, pPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 0.8f, 1.1f);
                }
            }
        } else {
            resetProgress();
        }
    }


    // Client-side ring: uses level.addParticle (only call when level.isClientSide)
    private static void spawnRingClient(Level level, BlockPos pos, ParticleOptions type,
                                        double radius, int points, double yOff, double speed) {
        double cx = pos.getX() + 0.5;
        double cy = pos.getY() + yOff;
        double cz = pos.getZ() + 0.5;

        for (int i = 0; i < points; i++) {
            double ang = (i / (double) points) * Math.PI * 2.0;
            double x = cx + Math.cos(ang) * radius;
            double z = cz + Math.sin(ang) * radius;

            // gentle outward drift from center
            double dx = Math.cos(ang) * speed;
            double dy = 0.01; // slight lift
            double dz = Math.sin(ang) * speed;

            level.addParticle(type, x, cy, z, dx, dy, dz);
        }
    }

    // Server-broadcast ring: uses sendParticles so every client sees it
    private static void spawnRingServer(ServerLevel sl, BlockPos pos, ParticleOptions type,
                                        double radius, int points, double yOff) {
        double cx = pos.getX() + 0.5;
        double cy = pos.getY() + yOff;
        double cz = pos.getZ() + 0.5;

        for (int i = 0; i < points; i++) {
            double ang = (i / (double) points) * Math.PI * 2.0;
            double x = cx + Math.cos(ang) * radius;
            double z = cz + Math.sin(ang) * radius;

            // count=1, no spread, zero speed (pure spawn at ring points)
            sl.sendParticles(type, x, cy, z, 1, 0, 0, 0, 0);
        }
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private void increaseCraftingProcess() {
        this.progress++;
    }

    private boolean hasRecipe() {

        Optional<HumanGradeInfuserRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(resultItem.getCount())
                && canInsertItemIntoOutputSlot(resultItem.getItem());
    }


    private Optional<HumanGradeInfuserRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++){
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(HumanGradeInfuserRecipe.Type.INSTANCE, inventory, level);

    }

    private boolean canInsertItemIntoOutputSlot(@NotNull Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
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

    private void craftItem() {
        Optional<HumanGradeInfuserRecipe> recipe = getCurrentRecipe();
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
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
}
