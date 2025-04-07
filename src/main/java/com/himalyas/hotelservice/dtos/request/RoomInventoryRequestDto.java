package com.himalyas.hotelservice.dtos.request;

import com.himalyas.hotelservice.models.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomInventoryRequestDto {
    private RoomType roomType;
    private Integer totalRooms;
    private Integer availableRooms;
    private Long hotelId;
}
