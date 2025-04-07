package com.himalyas.hotelservice.dtos.response;

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
public class RoomPriceResponseDto {
    private Long id;
    private RoomType roomType;
    private Integer price;
}
