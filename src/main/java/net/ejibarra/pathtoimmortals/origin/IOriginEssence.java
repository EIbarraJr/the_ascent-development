package net.ejibarra.pathtoimmortals.origin;

public interface IOriginEssence {
    int getCurrent();
    int getMax();

    int getTotalEarned();
    void setTotalEarned(int value);

    void setCurrent(int value);
    void setMax(int value);

    default void addEssence(int amount) {
        if (amount <= 0) return;

        // increase lifetime total
        setTotalEarned(getTotalEarned() + amount);

        // increase current but clamp to max
        setCurrent(Math.min(getCurrent() + amount, getMax()));
    }

    default void increaseMax(int amount) {
        if (amount <= 0) return;
        setMax(getMax() + amount);
    }

    default boolean consumeEssence(int amount) {
        if (amount <= 0) return true;
        if (getCurrent() < amount) return false;
        setCurrent(getCurrent() - amount);
        return true;
    }
}
