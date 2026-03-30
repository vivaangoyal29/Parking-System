package org.example.comp.Strategy;

import java.util.List;

import org.example.comp.model.SlotData;

public interface SlotAllocationStrategy {
    SlotData findSlot(List<SlotData> availableSlots);
}
