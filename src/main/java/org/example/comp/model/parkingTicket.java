package org.example.comp.model;

import java.time.LocalDateTime;

public class parkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final int slotId;
    private final int levelId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double charges;

    public parkingTicket(String ticketId, Vehicle vehicle, int slotId, int levelId) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slotId = slotId;
        this.levelId = levelId;
        this.entryTime = LocalDateTime.now();
        this.charges = 0.0;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getLevelId() {
        return levelId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    @Override
    public String toString() {
        return "parkingTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", slotId=" + slotId +
                ", levelId=" + levelId +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", charges=" + charges +
                '}';
    }
}
