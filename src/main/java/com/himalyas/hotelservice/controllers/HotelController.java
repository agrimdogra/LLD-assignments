package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.AddRoomsRequestDto;
import com.himalyas.hotelservice.dtos.request.HotelRequestDto;
import com.himalyas.hotelservice.dtos.response.HotelResponseDto;
import com.himalyas.hotelservice.exceptions.HotelNotCreatedException;
import com.himalyas.hotelservice.exceptions.InvalidAddRomRequest;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.services.HotelService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotel")
public class HotelController {

    private HotelService hotelService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelResponseDto> addHotel(@RequestBody HotelRequestDto hotelRequestDTo) {
        Hotel hotel = Hotel.builder()
                .hotelName(hotelRequestDTo.getHotelName())
                .location(hotelRequestDTo.getHotelLocation())
                .address(hotelRequestDTo.getHotelAddress())
                .floors(hotelRequestDTo.getNumOfFloors())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
        Optional<Hotel> savedHotel = hotelService.addHotel(hotel);
        if(savedHotel.isPresent()) {
            HotelResponseDto hotelResponseDto = HotelResponseDto.builder()
                    .hotelId(savedHotel.get().getId())
                    .hotelName(savedHotel.get().getHotelName())
                    .hotelLocation(savedHotel.get().getLocation())
                    .hotelLocation(savedHotel.get().getLocation())
                    .hotelAddress(savedHotel.get().getAddress())
                    .numberOfFloors(savedHotel.get().getFloors())
                    .numberOfFloors(savedHotel.get().getFloors())
                    .createdOn(savedHotel.get().getCreatedAt())
                    .rooms(savedHotel.get().getRooms())
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(hotelResponseDto);
        }
        else{
                throw new HotelNotCreatedException("Not able to save the hotel successfully !!");
        }
    }

    @PutMapping("/{hotelId}/rooms")
    public void attachRooms(@PathVariable Long hotelId, @RequestBody AddRoomsRequestDto addRoomsRequestDto) {
        if(!validateAddRoomsDto(addRoomsRequestDto)) {
            throw new InvalidAddRomRequest("the request is invalid, either hotelId is empty or rooms.");
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeHotel(@PathVariable Long id) {
        hotelService.removeHotel(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Hotel with ID " + id + " has been removed successfully.");
    }

    public boolean validateAddRoomsDto(AddRoomsRequestDto addRoomsRequestDto) {
//        return addRoomsRequestDto.getHotelId() != null && addRoomsRequestDto.getRoomsIds() != null;
        return true;
    }

}
