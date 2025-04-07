package com.himalyas.hotelservice.dtos.request;

import com.himalyas.hotelservice.models.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBookingRequestDto {
    private Long guestId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RoomType roomType;
    private Integer numberOfRooms;
    private Integer numberOfGuests;
    private Long staffId;
    private Long hotelId;
 }
