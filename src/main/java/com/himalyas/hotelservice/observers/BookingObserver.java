package com.himalyas.hotelservice.observers;

import com.himalyas.hotelservice.models.Invoice;

public interface BookingObserver {
    void onBookingCreated(Long bookingId);
}
