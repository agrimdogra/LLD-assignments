package com.himalyas.hotelservice.dtos.request;

import com.himalyas.hotelservice.models.RoomStatus;
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
public class UpdateRoomRequestDto {
    private Long roomId;
    private Long bookingId;
    private RoomStatus roomStatus;
    private Integer roomNumber;
    private Integer floorNumber;
    private Long hotelId;
}
