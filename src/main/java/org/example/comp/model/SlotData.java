package org.example.comp.model;

public class SlotData implements Comparable<SlotData> {
    private final int slotId;
    private final int levelId;
    private final int gateId;
    private final double distance;

    public SlotData(int slotId, int levelId, int gateId, double distance) {
        this.slotId = slotId;
        this.levelId = levelId;
        this.gateId = gateId;
        this.distance = distance;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getGateId() {
        return gateId;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(SlotData other) {
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return "SlotData{" +
                "slotId=" + slotId +
                ", levelId=" + levelId +
                ", gateId=" + gateId +
                ", distance=" + distance +
                '}';
    }
}
