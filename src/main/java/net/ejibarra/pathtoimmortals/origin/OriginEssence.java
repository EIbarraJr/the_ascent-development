package net.ejibarra.pathtoimmortals.origin;

import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.EnumSet;

public class OriginEssence implements IOriginEssence {
    public int current;
    public int max;
    public int totalEarned;

    // buffer for fractional regen
    private float regenBuffer = 0f;

    private static final int ABILITY_COUNT = 7; // ids 0..6
    private final int[] abilityTiers = new int[ABILITY_COUNT]; // default 0s

    @Override
    public int getCurrent() {
        return current;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public int getTotalEarned() {
        return totalEarned;
    }

    @Override
    public void setTotalEarned(int value) {
        this.totalEarned = Math.max(0, value);
    }

    @Override
    public void setCurrent(int value) {
        // clamp to [0, max]
        int clamped = Math.max(0, Math.min(value, max));

        // 🔹 if we're gaining essence, add that gain to totalEarned
        if (clamped > this.current) {
            int gain = clamped - this.current;
            this.totalEarned += gain;
        }

        //this.current = clamped;
        this.current = clamped;
    }

    @Override
    public void setMax(int value) {
        this.max = Math.max(0, value);
        if (current > max) current = max;
    }

    /**
     * Adds to max (capacity) but clamps to a provided ceiling.
     * STRICT: if max already >= ceiling, this adds nothing.
     *
     * Returns the amount of max that was actually added (0 if none).
     */
    public int addMaxCapped(int addAmount, int ceiling) {
        if (addAmount <= 0) return 0;
        if (ceiling <= 0) return 0;

        // Already at/above this item's ceiling -> can't increase.
        if (this.max >= ceiling) return 0;

        int beforeMax = this.max;

        int newMax = Math.min(this.max + addAmount, ceiling);
        this.max = Math.max(0, newMax);

        int added = this.max - beforeMax;
        if (added <= 0) return 0;

        // If you want using infused items to also "fill" essence, keep this:
        // Increase current by the same amount, clamped to new max.
        int beforeCurrent = this.current;
        this.current = Math.min(this.current + added, this.max);

        // Track lifetime earned based on current gain:
        int gained = this.current - beforeCurrent;
        if (gained > 0) this.totalEarned += gained;

        return added;
    }

    // ---- Ability unlock API ----

    private int unlockedMask = 0;//bitmask of unlocked abilities

   /* public boolean isAbilityUnlocked(AbilityType ability) {
        return (unlockedMask & ability.bit()) != 0;
    }*/

    public boolean unlockAbility(AbilityType ability) {
        int before = unlockedMask;
        unlockedMask |= ability.bit();
        return unlockedMask != before;
    }

    public int getUnlockedMask() {
        return unlockedMask;
    }

    public void setUnlockedMask(int mask) {
        this.unlockedMask = mask;
    }

    public int getAbilityTier(AbilityType ability) {
        int id = ability.getId();
        if (id < 0 || id >= ABILITY_COUNT) return 0;
        return abilityTiers[id];
    }

    public boolean isAbilityUnlocked(AbilityType ability) {
        return getAbilityTier(ability) >= 1;
    }

    /**
     * Sets tier directly (ex: scroll unlocks tier 3).
     * Returns true if it actually changed.
     */
    public boolean setAbilityTier(AbilityType ability, int newTier) {
        int id = ability.getId();
        if (id < 0 || id >= ABILITY_COUNT) return false;

        newTier = Math.max(0, newTier);
        int before = abilityTiers[id];
        abilityTiers[id] = newTier;
        return before != newTier;
    }

    public void setAbilityTiers(int[] tiers) {
        if (tiers == null) return;
        int len = Math.min(tiers.length, ABILITY_COUNT);
        for (int i = 0; i < len; i++) {
            abilityTiers[i] = Math.max(0, tiers[i]);
        }
    }
    public int[] getAbilityTiersCopy() {
        return abilityTiers.clone();
    }

    /**
     * Upgrades by +1 up to maxTier. Returns true if upgraded.
     */
    public boolean upgradeAbilityTier(AbilityType ability, int maxTier) {
        int id = ability.getId();
        if (id < 0 || id >= ABILITY_COUNT) return false;

        int before = abilityTiers[id];
        if (before >= maxTier) return false;

        abilityTiers[id] = before + 1;
        return true;
    }


    public void copyFrom(OriginEssence other) {
        this.current = other.current;
        this.max = other.max;
        this.totalEarned = other.totalEarned;

        this.regenBuffer = other.regenBuffer;

        this.unlockedMask = other.unlockedMask;
        System.arraycopy(other.abilityTiers, 0, this.abilityTiers, 0, ABILITY_COUNT);
    }

    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Current", current);
        tag.putInt("Max", max);
        tag.putInt("TotalEarned", totalEarned);

        tag.putInt("UnlockedMask", unlockedMask);
        tag.putIntArray("AbilityTiers", abilityTiers);

        return tag;
    }

    public void loadNBT(CompoundTag tag) {
        this.current = tag.getInt("Current");
        this.max = tag.getInt("Max");
        this.totalEarned = tag.getInt("TotalEarned");

        if (current > max) current = max;
        if (current < 0) current = 0;
        if (max < 0) max = 0;

        this.unlockedMask = tag.getInt("UnlockedMask");

        int[] arr = tag.getIntArray("AbilityTiers");
        if (arr.length == ABILITY_COUNT) {
            System.arraycopy(arr, 0, abilityTiers, 0, ABILITY_COUNT);
        } else if (arr.length > 0) {
            // handle older worlds or mismatched lengths gracefully
            int len = Math.min(arr.length, ABILITY_COUNT);
            System.arraycopy(arr, 0, abilityTiers, 0, len);
        }
    }
}
