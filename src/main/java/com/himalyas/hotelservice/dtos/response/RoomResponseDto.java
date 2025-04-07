package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.models.RoomStatus;
import com.himalyas.hotelservice.models.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDto {
    private Long roomId;
    private LocalDateTime createdOn;
    private RoomStatus roomStatus;
    private Booking booking;
    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private Hotel hotel;

}
