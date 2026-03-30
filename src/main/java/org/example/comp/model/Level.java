package org.example.comp.model;

import org.example.comp.model.enums.LevelStatus;

public class Level {
    private final int levelId;
    private final int totalSlots;
    private LevelStatus status;
    private double hourlyRate;

    public Level(int levelId, int totalSlots, double hourlyRate) {
        this.levelId = levelId;
        this.totalSlots = totalSlots;
        this.hourlyRate = hourlyRate;
        this.status = LevelStatus.OPERATIONAL;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public LevelStatus getStatus() {
        return status;
    }

    public void setStatus(LevelStatus status) {
        this.status = status;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public boolean isOperational() {
        return status == LevelStatus.OPERATIONAL;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelId=" + levelId +
                ", totalSlots=" + totalSlots +
                ", status=" + status +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}
