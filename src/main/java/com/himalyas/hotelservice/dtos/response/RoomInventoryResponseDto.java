package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.models.RoomType;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class RoomInventoryResponseDto {
    private Long id;
    private Hotel hotel;
    private RoomType roomType;
    private Integer totalRooms;
    private Integer roomsAvailable;
}
