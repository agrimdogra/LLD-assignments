package com.himalyas.hotelservice.exceptions;

import com.himalyas.hotelservice.dtos.response.HotelResponseDto;
import lombok.Data;

@Data
public class HotelNotCreatedException extends RuntimeException {
    public HotelNotCreatedException(String message) {
        super(message);
    }
}
