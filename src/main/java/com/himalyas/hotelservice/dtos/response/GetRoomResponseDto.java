package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.models.RoomStatus;
import com.himalyas.hotelservice.models.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetRoomResponseDto {
    private RoomStatus roomStatus;
    private Booking booking;
    private int roomNumber;
    private int floor;
    private RoomType roomType;
    private String hotelName;
}
