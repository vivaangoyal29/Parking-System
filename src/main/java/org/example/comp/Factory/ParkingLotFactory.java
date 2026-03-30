package org.example.comp.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.example.comp.Service.SlotManager;
import org.example.comp.model.Gate;
import org.example.comp.model.Level;
import org.example.comp.model.SlotData;
import org.example.comp.model.enums.VehicleType;
import org.example.comp.slots.CompactSlot;
import org.example.comp.slots.LargeSlot;
import org.example.comp.slots.Slot;
import org.example.comp.slots.StandardSlot;

public class ParkingLotFactory {
    private final Random random = new Random();

    public List<Level> createLevels(int numberOfLevels, List<Double> pricingRates) {
        List<Level> levels = new ArrayList<>();
        for (int i = 0; i < numberOfLevels; i++) {
            double rate = i < pricingRates.size() ? pricingRates.get(i) : 5.0;
            levels.add(new Level(i, 30, rate));
        }
        return levels;
    }

    public List<Gate> createGates(int numberOfLevels, int gatesPerLevel) {
        List<Gate> gates = new ArrayList<>();
        int gateId = 0;
        for (int level = 0; level < numberOfLevels; level++) {
            for (int g = 0; g < gatesPerLevel; g++) {
                gates.add(new Gate(gateId++, level, "Gate-L" + level + "-G" + g));
            }
        }
        return gates;
    }

    public SlotManager createSlots(int numberOfLevels, int slotsPerLevel, List<Double> pricingRates) {
        SlotManager slotManager = new SlotManager();
        int slotId = 0;

        for (int level = 0; level < numberOfLevels; level++) {
            double levelRate = level < pricingRates.size() ? pricingRates.get(level) : 5.0;

            for (int slot = 0; slot < slotsPerLevel; slot++) {
                VehicleType slotType = assignSlotType(slot, slotsPerLevel);
                Slot newSlot = createSlot(slotId, level, slotType, levelRate);
                slotManager.registerSlot(newSlot);
                slotId++;
            }
        }

        return slotManager;
    }

    public void registerSlotDistances(SlotManager slotManager, int numberOfLevels, int slotsPerLevel, int gatesPerLevel) {
        int slotId = 0;
        for (int level = 0; level < numberOfLevels; level++) {
            for (int slot = 0; slot < slotsPerLevel; slot++) {
                int gateId = 0;
                for (int g = 0; g < gatesPerLevel; g++) {
                    double distance = generateRandomDistance();
                    SlotData slotData = new SlotData(slotId, level, gateId, distance);
                    slotManager.registerSlotDistance(level, gateId, slotData);
                    gateId++;
                }
                slotId++;
            }
        }
    }

    private Slot createSlot(int slotId, int levelId, VehicleType vehicleType, double hourlyRate) {
        return switch (vehicleType) {
            case COMPACT -> new CompactSlot(slotId, levelId, hourlyRate);
            case STANDARD -> new StandardSlot(slotId, levelId, hourlyRate);
            case LARGE -> new LargeSlot(slotId, levelId, hourlyRate);
        };
    }

    private VehicleType assignSlotType(int slotIndex, int totalSlots) {
        int compact = (int) (totalSlots * 0.5);
        int standard = (int) (totalSlots * 0.35);

        if (slotIndex < compact) {
            return VehicleType.COMPACT;
        } else if (slotIndex < compact + standard) {
            return VehicleType.STANDARD;
        } else {
            return VehicleType.LARGE;
        }
    }

    private double generateRandomDistance() {
        return 10.0 + (random.nextDouble() * 490.0);
    }
}
