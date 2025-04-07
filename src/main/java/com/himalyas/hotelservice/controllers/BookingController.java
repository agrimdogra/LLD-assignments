package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.CreateBookingRequestDto;
import com.himalyas.hotelservice.dtos.response.BookingResponseDto;
import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.BookingStatus;
import com.himalyas.hotelservice.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody CreateBookingRequestDto request) {
        Booking booking = bookingService.createBooking(request);
        BookingResponseDto bookingResponseDto = BookingResponseDto.builder()
                .bookingId(booking.getId())
                .bookedBy(booking.getBookedBy())
                .bookingOwner(booking.getGuest())
                .createdAt(booking.getCreatedAt())
                .bookingStatus(booking.getBookingStatus())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .numberOfGuests(booking.getNumberOfGuests())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingResponseDto);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<BookingResponseDto> completeBooking(@PathVariable Long id) {
        Booking booking = bookingService.completeBooking(id);
        BookingResponseDto bookingResponseDto = BookingResponseDto.builder()
                .bookingId(booking.getId())
                .bookedBy(booking.getBookedBy())
                .bookingOwner(booking.getGuest())
                .createdAt(booking.getCreatedAt())
                .bookingStatus(booking.getBookingStatus())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .numberOfGuests(booking.getNumberOfGuests())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookingResponseDto);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDto> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.cancellBooking(id);
        BookingResponseDto bookingResponseDto = BookingResponseDto.builder()
                .bookingId(booking.getId())
                .bookedBy(booking.getBookedBy())
                .bookingOwner(booking.getGuest())
                .createdAt(booking.getCreatedAt())
                .bookingStatus(booking.getBookingStatus())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .numberOfGuests(booking.getNumberOfGuests())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookingResponseDto);
    }

}
