package org.example.comp.Strategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculateFare(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyRate) {
        if (entryTime == null || exitTime == null) {
            return 0.0;
        }

        long hoursParked = ChronoUnit.HOURS.between(entryTime, exitTime);
        long remainingMinutes = ChronoUnit.MINUTES.between(entryTime, exitTime) % 60;

        if (hoursParked == 0 && remainingMinutes > 0) {
            hoursParked = 1;
        }

        return hoursParked * hourlyRate;
    }
}
