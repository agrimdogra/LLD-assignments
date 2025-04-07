package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.dtos.request.RoomInventoryRequestDto;
import com.himalyas.hotelservice.dtos.response.RoomInventoryResponseDto;
import com.himalyas.hotelservice.exceptions.RoomTypeNotFoundException;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.models.RoomInventory;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.repositories.RoomInventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomInventoryService {
    RoomInventoryRepository roomInventoryRepository;
    HotelService hotelService;

    public RoomInventoryResponseDto getRoomInventory (RoomType roomType, Long hotelId) {
        Optional<RoomInventory> roomInventoryFound = roomInventoryRepository.findByRoomTypeAndHotelId(roomType,hotelId);
        if(roomInventoryFound.isPresent()) {
            RoomInventory roomInventory = roomInventoryFound.get();
            return RoomInventoryResponseDto.builder()
                    .id(roomInventory.getId())
                    .hotel(roomInventory.getHotel())
                    .roomType(roomInventory.getRoomType())
                    .totalRooms(roomInventory.getTotalRooms())
                    .roomsAvailable(roomInventory.getAvailableRooms())
                    .build();
        }
        else{
            throw new RoomTypeNotFoundException("Room type "+ roomType +" not found." + " " +
                    "Available room types are "+ RoomType.DELUXE +" "+RoomType.DOUBLE +" "+RoomType.SINGLE+" " +RoomType.SUITE);
        }
    }

    public RoomInventoryResponseDto decreaseAvailability(RoomType roomType, Integer count, Long hotelId) {
        RoomInventory inventory = roomInventoryRepository
                .findByRoomTypeAndHotelId(roomType, hotelId)
                .orElseThrow(() -> new RoomTypeNotFoundException("Room type " + roomType + " not found for hotel ID " + hotelId));

        if (inventory.getAvailableRooms() < count) {
            throw new IllegalArgumentException("Not enough available rooms to decrease by " + count);
        }

        inventory.setAvailableRooms(inventory.getAvailableRooms() - count);
        RoomInventory updatedInventory = roomInventoryRepository.save(inventory);

        return RoomInventoryResponseDto.builder()
                .id(updatedInventory.getId())
                .hotel(updatedInventory.getHotel())
                .roomType(updatedInventory.getRoomType())
                .totalRooms(updatedInventory.getTotalRooms())
                .roomsAvailable(updatedInventory.getAvailableRooms())
                .build();
    }

    public RoomInventoryResponseDto increaseAvailability(RoomType roomType, Integer count, Long hotelId) {
        RoomInventory inventory = roomInventoryRepository
                .findByRoomTypeAndHotelId(roomType, hotelId)
                .orElseThrow(() -> new RoomTypeNotFoundException("Room type " + roomType + " not found for hotel ID " + hotelId));

        if (inventory.getAvailableRooms() + count > inventory.getTotalRooms()) {
            throw new IllegalArgumentException("Available rooms cannot exceed total rooms.");
        }

        inventory.setAvailableRooms(inventory.getAvailableRooms() + count);
        RoomInventory updatedInventory = roomInventoryRepository.save(inventory);

        return RoomInventoryResponseDto.builder()
                .id(updatedInventory.getId())
                .hotel(updatedInventory.getHotel())
                .roomType(updatedInventory.getRoomType())
                .totalRooms(updatedInventory.getTotalRooms())
                .roomsAvailable(updatedInventory.getAvailableRooms())
                .build();
    }

    public RoomInventoryResponseDto addRoomInventory(RoomInventoryRequestDto roomInventoryRequestDto) {
        Optional<RoomInventory> existingInventory = roomInventoryRepository
                .findByRoomTypeAndHotelId(roomInventoryRequestDto.getRoomType(), roomInventoryRequestDto.getHotelId());
        RoomInventory roomInventory = null;
        if (existingInventory.isPresent()) {
            RoomInventory inventory = existingInventory.get();
            inventory.setTotalRooms(roomInventoryRequestDto.getTotalRooms());
            inventory.setAvailableRooms(roomInventoryRequestDto.getAvailableRooms());
            roomInventory =  roomInventoryRepository.save(inventory);
        }

        else {
            Hotel hotel = hotelService.getHotelById(roomInventoryRequestDto.getHotelId());
            RoomInventory roomInventoryToBeSaved = RoomInventory.builder()
                    .roomType(roomInventoryRequestDto.getRoomType())
                    .totalRooms(roomInventoryRequestDto.getTotalRooms())
                    .availableRooms(roomInventoryRequestDto.getAvailableRooms())
                    .hotel(hotel)
                    .build();
            roomInventory = roomInventoryRepository.save(roomInventoryToBeSaved);
        }

        return RoomInventoryResponseDto.builder()
                .id(roomInventory.getId())
                .hotel(roomInventory.getHotel())
                .roomType(roomInventory.getRoomType())
                .totalRooms(roomInventory.getTotalRooms())
                .roomsAvailable(roomInventory.getAvailableRooms())
                .build();

    }
}
