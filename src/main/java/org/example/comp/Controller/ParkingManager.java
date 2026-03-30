package org.example.comp.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.comp.Factory.ParkingLotConfiguration;
import org.example.comp.Factory.ParkingLotFactory;
import org.example.comp.Service.BillingService;
import org.example.comp.Service.SlotManager;
import org.example.comp.Strategy.HourlyPricingStrategy;
import org.example.comp.Strategy.NearestAvailableSlotStrategy;
import org.example.comp.Strategy.SlotAllocationStrategy;
import org.example.comp.model.Gate;
import org.example.comp.model.Level;
import org.example.comp.model.SlotData;
import org.example.comp.model.Vehicle;
import org.example.comp.model.parkingTicket;
import org.example.comp.slots.Slot;

public class ParkingManager {
    private final List<Level> levels;
    private final List<Gate> gates;
    private final SlotManager slotManager;
    private final BillingService billingService;
    private final SlotAllocationStrategy allocationStrategy;
    private final Map<String, parkingTicket> tickets;
    private final AtomicInteger ticketCounter;

    public ParkingManager(ParkingLotFactory factory, ParkingLotConfiguration config) {
        this.tickets = new ConcurrentHashMap<>();
        this.ticketCounter = new AtomicInteger(0);
        this.allocationStrategy = new NearestAvailableSlotStrategy();
        this.billingService = new BillingService(new HourlyPricingStrategy());

        this.levels = factory.createLevels(config.getNumberOfLevels(), config.getLevelPricingRates());
        this.gates = factory.createGates(config.getNumberOfLevels(), config.getGatesPerLevel());
        this.slotManager = factory.createSlots(config.getNumberOfLevels(), config.getSlotsPerLevel(), config.getLevelPricingRates());
        factory.registerSlotDistances(slotManager, config.getNumberOfLevels(), config.getSlotsPerLevel(), config.getGatesPerLevel());
    }

    public parkingTicket parkVehicle(Vehicle vehicle, int gateId) {
        Gate gate = gates.stream().filter(g -> g.getGateId() == gateId).findFirst().orElse(null);
        if (gate == null) {
            System.out.println("Invalid gate: " + gateId);
            return null;
        }

        Level level = levels.get(gate.getLevelId());
        if (!level.isOperational()) {
            System.out.println("Level " + level.getLevelId() + " is not operational");
            return null;
        }

        List<SlotData> availableSlots = slotManager.getSlotsByLevelAndGate(gate.getLevelId(), gateId);
        availableSlots = availableSlots.stream()
                .filter(slotData -> {
                    Slot slot = slotManager.getSlot(slotData.getSlotId());
                    return slot != null && !slot.isOccupied() && slot.canAccommodate(vehicle.getVehicleType());
                })
                .toList();

        SlotData selectedSlotData = allocationStrategy.findSlot(availableSlots);
        if (selectedSlotData == null) {
            System.out.println("No available slot for vehicle: " + vehicle.getLicensePlate());
            return null;
        }

        Slot slot = slotManager.getSlot(selectedSlotData.getSlotId());
        if (slot.occupySlot()) {
            String ticketId = "TKT-" + ticketCounter.incrementAndGet();
            parkingTicket ticket = new parkingTicket(ticketId, vehicle, slot.getSlotId(), level.getLevelId());
            tickets.put(ticketId, ticket);
            System.out.println("Vehicle parked successfully: " + ticket);
            return ticket;
        }

        return null;
    }

    public double exitVehicle(String ticketId) {
        parkingTicket ticket = tickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket: " + ticketId);
            return -1;
        }

        Slot slot = slotManager.getSlot(ticket.getSlotId());
        if (slot == null || !slot.isOccupied()) {
            System.out.println("Slot is not occupied or invalid");
            return -1;
        }

        Level level = levels.get(ticket.getLevelId());
        LocalDateTime exitTime = LocalDateTime.now();
        ticket.setExitTime(exitTime);

        double charges = billingService.calculateCharge(ticket.getEntryTime(), exitTime, level.getHourlyRate());
        ticket.setCharges(charges);

        slot.vacateSlot();
        System.out.println("Vehicle exited successfully: " + ticket);
        return charges;
    }

    public void displayParkingStatus() {
        System.out.println("\n Parking Lot Status ");
        System.out.println("Total Slots: " + slotManager.getTotalSlots());
        System.out.println("Available Slots: " + slotManager.getAvailableSlotsCount());
        System.out.println("Occupied Slots: " + (slotManager.getTotalSlots() - slotManager.getAvailableSlotsCount()));
        System.out.println("Levels: " + levels.size());
        System.out.println("Gates: " + gates.size());
    }

    public List<Level> getLevels() {
        return levels;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public SlotManager getSlotManager() {
        return slotManager;
    }

    public Map<String, parkingTicket> getTickets() {
        return new HashMap<>(tickets);
    }
}
