package com.himalyas.hotelservice.exceptions;

public class PaymentPendingException extends RuntimeException{
    public PaymentPendingException(String message) {
        super(message);
    }
}
