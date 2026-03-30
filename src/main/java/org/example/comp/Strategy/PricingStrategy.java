package org.example.comp.Strategy;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFare(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyRate);
}
