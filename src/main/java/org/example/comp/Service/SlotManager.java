package org.example.comp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.example.comp.model.SlotData;
import org.example.comp.slots.Slot;

public class SlotManager {
    private final Map<Integer, Slot> allSlots;
    private final Map<Integer, Map<Integer, List<SlotData>>> slotsByLevelAndGate;

    public SlotManager() {
        this.allSlots = new ConcurrentHashMap<>();
        this.slotsByLevelAndGate = new ConcurrentHashMap<>();
    }

    public void registerSlot(Slot slot) {
        allSlots.put(slot.getSlotId(), slot);
    }

    public void registerSlotDistance(int levelId, int gateId, SlotData slotData) {
        slotsByLevelAndGate.computeIfAbsent(levelId, k -> new ConcurrentHashMap<>())
                           .computeIfAbsent(gateId, k -> new ArrayList<>())
                           .add(slotData);
    }

    public Slot getSlot(int slotId) {
        return allSlots.get(slotId);
    }

    public List<SlotData> getSlotsByLevelAndGate(int levelId, int gateId) {
        Map<Integer, List<SlotData>> levelMap = slotsByLevelAndGate.get(levelId);
        if (levelMap == null) {
            return new ArrayList<>();
        }
        List<SlotData> slots = levelMap.get(gateId);
        return slots != null ? new ArrayList<>(slots) : new ArrayList<>();
    }

    public List<Slot> getAllAvailableSlots() {
        List<Slot> available = new ArrayList<>();
        for (Slot slot : allSlots.values()) {
            if (!slot.isOccupied()) {
                available.add(slot);
            }
        }
        return available;
    }

    public int getTotalSlots() {
        return allSlots.size();
    }

    public long getAvailableSlotsCount() {
        return allSlots.values().stream()
                       .filter(slot -> !slot.isOccupied())
                       .count();
    }
}
