package com.himalyas.hotelservice.exceptions;

public class RoomTypeNotFoundException extends RuntimeException {
    public RoomTypeNotFoundException(String message) {
        super(message);
    }
}
