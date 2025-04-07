package com.himalyas.hotelservice.exceptions;

import lombok.Data;

@Data
public class InvalidAddRomRequest extends RuntimeException{
    public InvalidAddRomRequest(String message) {
        super(message);
    }
}
