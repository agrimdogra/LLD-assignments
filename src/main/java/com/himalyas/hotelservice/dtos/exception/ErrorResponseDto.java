package com.himalyas.hotelservice.dtos.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseDto {
    private HttpStatus statusCode;
    private String message;
    private String errorCode;
}
