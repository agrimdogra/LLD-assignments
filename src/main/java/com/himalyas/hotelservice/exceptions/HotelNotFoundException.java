package com.himalyas.hotelservice.exceptions;

import lombok.Data;

@Data
public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String message) {
        super(message);
    }
}
