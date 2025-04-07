package com.himalyas.hotelservice.exceptions.ControllerAdvise;

import com.himalyas.hotelservice.dtos.exception.ErrorResponseDto;
import com.himalyas.hotelservice.exceptions.BookingNotFoundException;
import com.himalyas.hotelservice.exceptions.HotelNotCreatedException;
import com.himalyas.hotelservice.exceptions.HotelNotFoundException;
import com.himalyas.hotelservice.exceptions.InvalidAddRomRequest;
import com.himalyas.hotelservice.exceptions.InvalidIdentityProvided;
import com.himalyas.hotelservice.exceptions.InvoiceNotFoundException;
import com.himalyas.hotelservice.exceptions.PaymentPendingException;
import com.himalyas.hotelservice.exceptions.RoomNotFoundException;
import com.himalyas.hotelservice.exceptions.RoomTypeNotFoundException;
import com.himalyas.hotelservice.exceptions.StaffNotSavedException;
import com.himalyas.hotelservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>handleHotelNotFoundException (HotelNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .errorCode("HOTEL_NOT_FOUND")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(HotelNotCreatedException.class)
    public ResponseEntity<ErrorResponseDto> handleHotelNotCreatedException (HotelNotCreatedException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .errorCode("HOTEl_NOT_CREATED")
                .build();
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(InvalidAddRomRequest.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidAddRomRequest (InvalidAddRomRequest ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .errorCode("INVALID_ADD_ROOM_REQUEST")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRoomNotFoundException (RoomNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("ROOM_NOT_FOUND")
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("USER_NOT_FOUND")
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(InvalidIdentityProvided.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidIdentityProvidedException (InvalidIdentityProvided ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("INVALID_GOV_ID")
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(StaffNotSavedException.class)
    public ResponseEntity<ErrorResponseDto> handleStaffNotSavedException (StaffNotSavedException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("STAFF_NOT_SAVED")
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRoomTypeNotFoundException (RoomTypeNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("INVALID_ROOM_TYPE")
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleBookingNotFoundException (BookingNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("BOOKING_NOT_FOUND")
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleBookingNotFoundException (InvoiceNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("INVOICE_NOT_FOUND")
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(PaymentPendingException.class)
    public ResponseEntity<ErrorResponseDto> handlePaymentPendingException (PaymentPendingException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .errorCode("PAYMENT_PENDING")
                .message(ex.getMessage())
                .statusCode(HttpStatus.FORBIDDEN)
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(errorResponseDto);
    }

    // Handle Any Other Exception (Fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .errorCode("GENERIC_ERROR")
                .build();
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }
}
