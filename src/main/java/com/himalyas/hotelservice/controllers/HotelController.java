package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.AddRoomsRequestDto;
import com.himalyas.hotelservice.dtos.request.HotelRequestDto;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.services.HotelService;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {

    private HotelService hotelService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addHotel(@RequestBody HotelRequestDto hotelRequestDTo) {
        System.out.println("Raw Request Body: " + hotelRequestDTo);
        Hotel hotel = Hotel.builder()
                .hotelName(hotelRequestDTo.getHotelName())
                .location(hotelRequestDTo.getHotelLocation())
                .address(hotelRequestDTo.getHotelAddress())
                .floors(hotelRequestDTo.getNumOfFloors())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
        Hotel hotel1 = hotelService.addHotel(hotel);
    }

    @PutMapping("/{hotelId}/rooms")
    public void addRooms(@PathVariable Long hotelId, @RequestBody AddRoomsRequestDto addRoomsRequestDto) {

    }

    @DeleteMapping("/{id}")
    public void removeHotel(@PathVariable Long id) {

    }

}
