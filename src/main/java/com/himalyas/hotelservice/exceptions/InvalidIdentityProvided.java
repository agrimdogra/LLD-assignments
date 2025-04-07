package com.himalyas.hotelservice.exceptions;

public class InvalidIdentityProvided extends RuntimeException {
    public InvalidIdentityProvided(String message) {
        super(message);
    }
}
