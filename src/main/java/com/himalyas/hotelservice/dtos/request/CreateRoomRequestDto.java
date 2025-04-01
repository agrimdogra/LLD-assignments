package com.himalyas.hotelservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRoomRequestDto {
    private Integer roomNumber;
    private Integer floor;
    private String typeOfRoom;
}
