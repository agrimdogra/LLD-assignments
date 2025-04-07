package com.himalyas.hotelservice.stratergies.billing;


import com.himalyas.hotelservice.models.Booking;

public interface BillingStrategy {
    public Integer calculateBillingAmount(Booking booking);
}
