package org.example.comp.slots;

import org.example.comp.model.enums.VehicleType;

public class CompactSlot extends Slot {

    public CompactSlot(int slotId, int levelId, double hourlyRate) {
        super(slotId, levelId, VehicleType.COMPACT, hourlyRate);
    }

    @Override
    public boolean canAccommodate(VehicleType vehicleType) {
        return vehicleType == VehicleType.COMPACT;
    }
}
