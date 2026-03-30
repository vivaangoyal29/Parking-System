package org.example.comp.Strategy;

import java.util.Collections;
import java.util.List;

import org.example.comp.model.SlotData;

public class NearestAvailableSlotStrategy implements SlotAllocationStrategy {

    @Override
    public SlotData findSlot(List<SlotData> availableSlots) {
        if (availableSlots == null || availableSlots.isEmpty()) {
            return null;
        }
        return Collections.min(availableSlots);
    }
}
