package org.example.comp.Factory;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotConfiguration {
    private int numberOfLevels;
    private int slotsPerLevel;
    private int gatesPerLevel;
    private List<Double> levelPricingRates;

    public ParkingLotConfiguration() {
        this.levelPricingRates = new ArrayList<>();
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }

    public void setNumberOfLevels(int numberOfLevels) {
        this.numberOfLevels = numberOfLevels;
    }

    public int getSlotsPerLevel() {
        return slotsPerLevel;
    }

    public void setSlotsPerLevel(int slotsPerLevel) {
        this.slotsPerLevel = slotsPerLevel;
    }

    public int getGatesPerLevel() {
        return gatesPerLevel;
    }

    public void setGatesPerLevel(int gatesPerLevel) {
        this.gatesPerLevel = gatesPerLevel;
    }

    public List<Double> getLevelPricingRates() {
        return levelPricingRates;
    }

    public void setLevelPricingRates(List<Double> levelPricingRates) {
        this.levelPricingRates = levelPricingRates;
    }

    public void addLevelPricingRate(double rate) {
        this.levelPricingRates.add(rate);
    }
}
