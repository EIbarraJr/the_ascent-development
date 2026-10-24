package net.ejibarra.pathtoimmortals.network.packet;

public enum AbilityType {
    ORIGIN_SWORD(0),
    PRESSURE_WAVE(1),
    OVERCHARGED(2),
    HEAL(3),
    REINFORCED(4),
    LEVITATION(5),
    CLOUD_STEP(6);

    private final int id;

    AbilityType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int bit() {
        return 1 << this.id;
    }

    public static AbilityType fromId(int id) {
        for (AbilityType type : values()) {
            if (type.id == id) return type;
        }
        return ORIGIN_SWORD; // safe fallback
    }
}
