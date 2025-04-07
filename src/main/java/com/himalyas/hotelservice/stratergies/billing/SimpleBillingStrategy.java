package com.himalyas.hotelservice.stratergies.billing;

import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.services.BaseRoomPriceService;
import org.springframework.context.annotation.Primary;

@Primary
public class SimpleBillingStrategy implements BillingStrategy {
    private static final Integer SERVICE_CHARGE_RATE = 5;
    private static final Integer GST_RATE = 18;

    private BaseRoomPriceService baseRoomPriceService;

    @Override
    public Integer calculateBillingAmount(Booking booking) {
        RoomType roomType = booking.getRooms().get(0).getRoomType();
        Integer basePrice = baseRoomPriceService.getRoomBasePrice(roomType);
        int serviceCharges = (int)(basePrice * SERVICE_CHARGE_RATE/100);
        int gstAmount = (int)(basePrice * GST_RATE/100);
        return basePrice + serviceCharges + gstAmount;
    }
}
