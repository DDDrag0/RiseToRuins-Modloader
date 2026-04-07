package rtr.map;

public enum TileSetObjectPhase {
    ABANDONED(5500),
    BASE(0),
    CONSTRUCTION_OUTLINE(5000),
    CONSTRUCTION_PHASE_5(4500),
    CONSTRUCTION_PHASE_4(4000),
    CONSTRUCTION_PHASE_3(3500),
    CONSTRUCTION_PHASE_2(3000),
    CONSTRUCTION_PHASE_1(2500),
    FILL_PHASE_4(2000),
    FILL_PHASE_3(1500),
    FILL_PHASE_2(1000),
    FILL_PHASE_1(500);

    private final int tileOffset;

    TileSetObjectPhase(int tileOffset) {
        this.tileOffset = tileOffset;
    }

    public int getTileOffset() {
        return tileOffset;
    }
}