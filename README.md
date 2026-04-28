# Parking Management System

A robust, thread-safe Java implementation of a multi-level parking lot management system. This project demonstrates high-level system design principles, including concurrency management and several object-oriented design patterns.

## System Architecture

The system is designed to handle multiple levels, gates, and vehicle types (Compact, Standard, Large) while optimizing for slot allocation based on proximity to entry points.

### UML Design Diagram

```mermaid
classDiagram
    class Slot {
        <<abstract>>
        #int slotId
        #int levelId
        #VehicleType slotType
        #AtomicBoolean isOccupied
        +occupySlot() boolean
        +vacateSlot() void
        +canAccommodate(VehicleType) boolean*
    }

    class CompactSlot
    class StandardSlot
    class LargeSlot

    Slot <|-- CompactSlot
    Slot <|-- StandardSlot
    Slot <|-- LargeSlot

    class PricingStrategy {
        <<interface>>
        +calculateFare(LocalDateTime, LocalDateTime, double) double
    }
    class HourlyPricingStrategy
    PricingStrategy <|.. HourlyPricingStrategy

    class SlotAllocationStrategy {
        <<interface>>
        +findSlot(List~SlotData~) SlotData
    }
    class NearestAvailableSlotStrategy
    SlotAllocationStrategy <|.. NearestAvailableSlotStrategy

    class ParkingManager {
        -List~Level~ levels
        -List~Gate~ gates
        -SlotManager slotManager
        -BillingService billingService
        -SlotAllocationStrategy allocationStrategy
        +parkVehicle(Vehicle, int) parkingTicket
        +exitVehicle(String) double
    }

    class SlotManager {
        -Map~Integer, Slot~ allSlots
        -Map~Integer, Map~Integer, List~SlotData~~~ slotsByLevelAndGate
        +registerSlot(Slot)
        +getSlotsByLevelAndGate(int, int) List~SlotData~
    }

    class BillingService {
        -PricingStrategy pricingStrategy
        +calculateCharge(LocalDateTime, LocalDateTime, double) double
    }

    class ParkingLotFactory {
        +createLevels() List~Level~
        +createGates() List~Gate~
        +createSlots() SlotManager
    }

    ParkingManager --> SlotManager : uses
    ParkingManager --> BillingService : uses
    ParkingManager --> SlotAllocationStrategy : uses
    BillingService --> PricingStrategy : uses
    ParkingLotFactory ..> Slot : creates
    ParkingLotFactory ..> ParkingManager : initializes
    SlotManager o-- Slot : manages
