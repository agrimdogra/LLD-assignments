package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponseDto {
    private Long hotelId;
    private LocalDateTime createdOn;
    private String hotelName;
    private String hotelLocation;
    private String hotelAddress;
    private Integer numberOfFloors;
    private List<Room> rooms;
}
