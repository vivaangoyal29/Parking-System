package org.example.comp.Service;

import java.time.LocalDateTime;

import org.example.comp.Strategy.PricingStrategy;

public class BillingService {
    private final PricingStrategy pricingStrategy;

    public BillingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateCharge(LocalDateTime entryTime, LocalDateTime exitTime, double hourlyRate) {
        return pricingStrategy.calculateFare(entryTime, exitTime, hourlyRate);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        throw new UnsupportedOperationException("Strategy is immutable after initialization");
    }
}
