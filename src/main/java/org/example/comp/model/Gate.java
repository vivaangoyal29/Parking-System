package org.example.comp.model;

public class Gate {
    private final int gateId;
    private final int levelId;
    private final String location;

    public Gate(int gateId, int levelId, String location) {
        this.gateId = gateId;
        this.levelId = levelId;
        this.location = location;
    }

    public int getGateId() {
        return gateId;
    }

    public int getLevelId() {
        return levelId;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "gateId=" + gateId +
                ", levelId=" + levelId +
                ", location='" + location + '\'' +
                '}';
    }
}
