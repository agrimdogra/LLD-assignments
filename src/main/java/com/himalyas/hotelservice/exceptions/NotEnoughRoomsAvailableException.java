package com.himalyas.hotelservice.exceptions;

public class NotEnoughRoomsAvailableException extends RuntimeException {
    public NotEnoughRoomsAvailableException(String message) {
        super(message);
    }
}
