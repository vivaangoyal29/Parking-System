package org.example.comp.slots;

import org.example.comp.model.enums.VehicleType;

public class StandardSlot extends Slot {

    public StandardSlot(int slotId, int levelId, double hourlyRate) {
        super(slotId, levelId, VehicleType.STANDARD, hourlyRate);
    }

    @Override
    public boolean canAccommodate(VehicleType vehicleType) {
        return vehicleType == VehicleType.COMPACT || vehicleType == VehicleType.STANDARD;
    }
}
