package org.example.comp.slots;

import java.util.concurrent.atomic.AtomicBoolean;

import org.example.comp.model.enums.VehicleType;

public abstract class Slot {
    protected final int slotId;
    protected final int levelId;
    protected final VehicleType slotType;
    protected final double hourlyRate;
    protected final AtomicBoolean isOccupied;

    public Slot(int slotId, int levelId, VehicleType slotType, double hourlyRate) {
        this.slotId = slotId;
        this.levelId = levelId;
        this.slotType = slotType;
        this.hourlyRate = hourlyRate;
        this.isOccupied = new AtomicBoolean(false);
    }

    public int getSlotId() {
        return slotId;
    }

    public int getLevelId() {
        return levelId;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }

    public boolean occupySlot() {
        return isOccupied.compareAndSet(false, true);
    }

    public void vacateSlot() {
        isOccupied.set(false);
    }

    public abstract boolean canAccommodate(VehicleType vehicleType);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "slotId=" + slotId +
                ", levelId=" + levelId +
                ", slotType=" + slotType +
                ", hourlyRate=" + hourlyRate +
                ", isOccupied=" + isOccupied.get() +
                '}';
    }
}
