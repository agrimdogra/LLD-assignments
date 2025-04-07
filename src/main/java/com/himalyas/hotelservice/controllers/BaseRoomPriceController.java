package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.UpdateRoomBasePriceDto;
import com.himalyas.hotelservice.dtos.response.RoomPriceResponseDto;
import com.himalyas.hotelservice.exceptions.RoomTypeNotFoundException;
import com.himalyas.hotelservice.models.BasePrice;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.services.BaseRoomPriceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/room-price")
public class BaseRoomPriceController {
    BaseRoomPriceService baseRoomPriceService;

    @PostMapping
    public ResponseEntity<RoomPriceResponseDto> updateRoomPrice (UpdateRoomBasePriceDto updateRoomBasePriceDto) {
        if(!isValidRequest(updateRoomBasePriceDto)) {
            throw new RoomTypeNotFoundException("Room type "+ updateRoomBasePriceDto.getRoomType() +" not found." + " " +
                    "Available room types are "+ RoomType.DELUXE +" "+RoomType.DOUBLE +" "+RoomType.SINGLE+" " +RoomType.SUITE);
        }
        else {
            BasePrice basePrice = baseRoomPriceService.updateBasePrice(updateRoomBasePriceDto);
            RoomPriceResponseDto roomPriceResponseDto = RoomPriceResponseDto.builder()
                    .id(basePrice.getId())
                    .roomType(basePrice.getRoomType())
                    .price(basePrice.getBasePrice())
                    .build();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(roomPriceResponseDto);
        }
    }

    @GetMapping("/{roomType}")
    public ResponseEntity<Integer> getRoomPrice(RoomType roomType) {
        Integer basePrice = baseRoomPriceService.getRoomBasePrice(roomType);
        return ResponseEntity.status(HttpStatus.OK)
                .body(basePrice);
    }


    private boolean isValidRequest(UpdateRoomBasePriceDto updateRoomBasePriceDto) {
        return updateRoomBasePriceDto.getRoomType()!=null &&
                updateRoomBasePriceDto.getPrice() !=null;
    }
}
