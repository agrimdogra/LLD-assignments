package com.himalyas.hotelservice.dtos.request;

import com.himalyas.hotelservice.models.RoomType;
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
public class UpdateRoomBasePriceDto {
    private RoomType roomType;
    private Integer price;
}
