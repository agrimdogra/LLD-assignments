package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.RoomInventoryRequestDto;
import com.himalyas.hotelservice.dtos.response.RoomInventoryResponseDto;
import com.himalyas.hotelservice.models.RoomInventory;
import com.himalyas.hotelservice.services.RoomInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/room-inventory")
public class RoomInventoryController {
    RoomInventoryService roomInventoryService;

    @PostMapping
    public ResponseEntity<RoomInventoryResponseDto> createRoomRepository(RoomInventoryRequestDto roomInventoryRequestDto) {
        RoomInventoryResponseDto roomInventoryResponseDto = roomInventoryService.addRoomInventory(roomInventoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomInventoryResponseDto);
    }
}
