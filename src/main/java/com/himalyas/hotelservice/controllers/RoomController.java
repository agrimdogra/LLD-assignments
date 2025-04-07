package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.CreateRoomRequestDto;
import com.himalyas.hotelservice.dtos.request.UpdateRoomRequestDto;
import com.himalyas.hotelservice.dtos.response.RoomResponseDto;
import com.himalyas.hotelservice.exceptions.RoomNotCreatedException;
import com.himalyas.hotelservice.exceptions.RoomNotFoundException;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.services.HotelService;
import com.himalyas.hotelservice.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {
    private RoomService roomService;
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<RoomResponseDto> getRoom(Long roomId) {
        Optional<Room> roomRequested = roomService.getRoomById(roomId);
        if(roomRequested.isPresent()) {
            RoomResponseDto roomResponseDto = RoomResponseDto.builder()
                    .roomNumber(roomRequested.get().getRoomNumber())
                    .roomStatus(roomRequested.get().getRoomStatus())
                    .roomId(roomRequested.get().getId())
                    .floor(roomRequested.get().getFloor())
                    .hotel(roomRequested.get().getHotel())
                    .booking(roomRequested.get().getBooking())
                    .createdOn(roomRequested.get().getCreatedAt())
                    .build();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(roomResponseDto);
        }

        else {
            throw new RoomNotFoundException("Room with id "+roomId+" is not found");
        }
    }

//    @GetMapping

    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody CreateRoomRequestDto createRoomRequestDto) {
        if(isValid(createRoomRequestDto)) {
            Hotel hotel = null;
            if(createRoomRequestDto.getHotelId() != null ){
                hotel = hotelService.getHotelById(createRoomRequestDto.getHotelId());
            }

            Room roomToBeCreated = Room.builder()
                    .roomNumber(createRoomRequestDto.getRoomNumber())
                    .floor(createRoomRequestDto.getFloor())
                    .roomType(createRoomRequestDto.getTypeOfRoom())
                    .roomStatus(createRoomRequestDto.getRoomStatus())
                    .hotel(hotel)
                    .build();

            Room savedRoom = roomService.saveRoom(roomToBeCreated);
            RoomResponseDto roomResponseDto = RoomResponseDto.builder()
                    .roomId(savedRoom.getId())
                    .roomNumber(savedRoom.getRoomNumber())
                    .floor(savedRoom.getFloor())
                    .roomType(savedRoom.getRoomType())
                    .roomStatus(savedRoom.getRoomStatus())
                    .hotel(savedRoom.getHotel())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(roomResponseDto);
        }
        else {
            throw new RoomNotCreatedException("Unable to create the room with given data");
        }
    }


//  Update any room status or booking
    @PatchMapping
    public ResponseEntity<RoomResponseDto> updateRoom(@RequestBody UpdateRoomRequestDto updateRoomRequestDto) {
        if(!isValidUpdateRoomDto(updateRoomRequestDto)) {
            throw new RoomNotCreatedException("Invalid room provided for update");
        }
        else {
            Optional<Room> room = roomService.getRoomById(updateRoomRequestDto.getRoomId());
            if(room.isEmpty()) {
                throw new RoomNotFoundException("Room with id "+updateRoomRequestDto.getRoomId() +" not found");
            }
            else {
                Room roomToBeUpdated = room.get();
                if(updateRoomRequestDto.getRoomNumber() != null) {
                    roomToBeUpdated.setRoomNumber(updateRoomRequestDto.getRoomNumber());
                }
                if(updateRoomRequestDto.getRoomStatus() != null) {
                    roomToBeUpdated.setRoomStatus(updateRoomRequestDto.getRoomStatus());
                }
                if(updateRoomRequestDto.getBookingId() != null) {
//                    roomToBeUpdated.setBooking(updateRoomRequestDto.getBookingId());
                }
                if(updateRoomRequestDto.getFloorNumber() != null) {
                    roomToBeUpdated.setFloor(updateRoomRequestDto.getFloorNumber());
                }
                if(updateRoomRequestDto.getHotelId() != null) {
                    Hotel hotel = hotelService.getHotelById(updateRoomRequestDto.getHotelId());
                    roomToBeUpdated.setHotel(hotel);
                }
                Room savedRoom = roomService.saveRoom(roomToBeUpdated);
                RoomResponseDto roomResponseDto = RoomResponseDto.builder()
                        .roomId(savedRoom.getId())
                        .roomNumber(savedRoom.getRoomNumber())
                        .floor(savedRoom.getFloor())
                        .roomType(savedRoom.getRoomType())
                        .roomStatus(savedRoom.getRoomStatus())
                        .hotel(savedRoom.getHotel())
                        .build();
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(roomResponseDto);

            }
        }
    }

    public boolean isValid(CreateRoomRequestDto createRoomRequestDto) {
        /**
         * Perform validations of dto if needed.
         */
        return true;
    }

    public boolean isValidUpdateRoomDto(UpdateRoomRequestDto updateRoomRequestDto) {
        if(updateRoomRequestDto==null || updateRoomRequestDto.getRoomId() == null) {
            return false;
        }
        return true;
    }
}
