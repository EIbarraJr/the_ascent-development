package net.ejibarra.pathtoimmortals.util;

import net.minecraftforge.energy.IEnergyStorage;

public class SunEnergyStorage implements IEnergyStorage {
    private int energy;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;

    public SunEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this.capacity = Math.max(0, capacity);
        this.maxReceive = Math.max(0, maxReceive);
        this.maxExtract = Math.max(0, maxExtract);
    }

    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(amount, capacity));
    }

    @Override public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive()) return 0;
        int toReceive = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate && toReceive > 0) energy += toReceive;
        return toReceive;
    }

    @Override public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract()) return 0;
        int toExtract = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate && toExtract > 0) energy -= toExtract;
        return toExtract;
    }

    @Override public int getEnergyStored() { return energy; }
    @Override public int getMaxEnergyStored() { return capacity; }
    @Override public boolean canExtract() { return maxExtract > 0; }
    @Override public boolean canReceive() { return maxReceive > 0; }
}
