package com.himalyas.hotelservice.stratergies.billing;

import com.himalyas.hotelservice.models.Booking;

public class DynamicPricingStrategy implements BillingStrategy {
    @Override
    public Integer calculateBillingAmount(Booking booking) {
        return 0;
    }
}
