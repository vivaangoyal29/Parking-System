package org.example.comp;

import java.util.Arrays;
import java.util.List;

import org.example.comp.Controller.ParkingManager;
import org.example.comp.Factory.ParkingLotConfiguration;
import org.example.comp.Factory.ParkingLotFactory;
import org.example.comp.model.Vehicle;
import org.example.comp.model.enums.VehicleType;
import org.example.comp.model.parkingTicket;

public class Main {
    public static void main(String[] args) {

        ParkingLotConfiguration config = new ParkingLotConfiguration();
        config.setNumberOfLevels(3);
        config.setSlotsPerLevel(30);
        config.setGatesPerLevel(2);

        config.setLevelPricingRates(Arrays.asList(5.0, 6.0, 7.0));

        ParkingLotFactory factory = new ParkingLotFactory();
        ParkingManager parkingManager = new ParkingManager(factory, config);

        parkingManager.displayParkingStatus();

        testVehicleEntry(parkingManager);

        testVehicleExit(parkingManager);

        parkingManager.displayParkingStatus();

        System.out.println("[INFO] Simulation completed successfully!");
    }

    private static void testVehicleEntry(ParkingManager parkingManager) {

        Vehicle[] vehicles = {
                new Vehicle("ABC-123", VehicleType.COMPACT),
                new Vehicle("XYZ-789", VehicleType.STANDARD),
                new Vehicle("LMN-456", VehicleType.LARGE),
                new Vehicle("PQR-234", VehicleType.COMPACT),
                new Vehicle("DEF-567", VehicleType.STANDARD)
        };

        List<parkingTicket> issuedTickets = new java.util.ArrayList<>();

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle vehicle = vehicles[i];
            int gateId = i % 2;
            System.out.println("[ENTRY] Attempting to park: " + vehicle);
            parkingTicket ticket = parkingManager.parkVehicle(vehicle, gateId);
            if (ticket != null) {
                issuedTickets.add(ticket);
            }
            System.out.println();
        }

        for (parkingTicket ticket : issuedTickets) {
            Main.parkingTickets.add(ticket);
        }
    }

    static List<parkingTicket> parkingTickets = new java.util.ArrayList<>();

    private static void testVehicleExit(ParkingManager parkingManager) {

        for (parkingTicket ticket : parkingTickets) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("EXIT Processing exit for ticket: " + ticket.getTicketId());
            double charges = parkingManager.exitVehicle(ticket.getTicketId());
            if (charges >= 0) {
                System.out.println("BILLING Charges calculated: $" + String.format("%.2f", charges));
            }
            System.out.println();
        }
    }
}