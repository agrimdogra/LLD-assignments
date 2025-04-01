package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.CreateRoomRequestDto;
import com.himalyas.hotelservice.dtos.request.UpdateRoomRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    @GetMapping
    public void getRoom() {

    }

    @PostMapping
    public void createRoom(@RequestBody CreateRoomRequestDto createRoomRequestDto) {

    }

//  Update any room status or booking
    @PatchMapping
    public void updateRoomStatus(@RequestBody UpdateRoomRequestDto updateRoomRequestDto) {

    }
}
