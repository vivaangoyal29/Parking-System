package org.example.comp.slots;

import org.example.comp.model.enums.VehicleType;

public class LargeSlot extends Slot {

    public LargeSlot(int slotId, int levelId, double hourlyRate) {
        super(slotId, levelId, VehicleType.LARGE, hourlyRate);
    }

    @Override
    public boolean canAccommodate(VehicleType vehicleType) {
        return true;
    }
}
