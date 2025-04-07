package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.repositories.BookingRepository;
import com.himalyas.hotelservice.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RoomService {
    RoomRepository roomRepository;
    BookingService bookingService;

    public List<Room> getRoomsById(List<Long> roomIds) {
        return roomRepository.findAllById(roomIds);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> saveAllRooms(List<Room> rooms) {
        return roomRepository.saveAll(rooms);
    }

    public List<Room> getRoomByRoomType(RoomType roomType) {
        return  roomRepository.findAllByRoomType(roomType);
    }

    public boolean areRoomsAvailable(Long hotelId, RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut, int requiredCount) {
        // Step 1: Get all rooms of that type in the hotel
        List<Room> rooms = roomRepository.findByHotelIdAndRoomType(hotelId, roomType);

        if (rooms.isEmpty()) return false;

        // Step 2: Get rooms already booked in that date range
        List<Long> bookedRoomIds = bookingService.findBookedRoomIds(hotelId, roomType, checkIn, checkOut);

        int availableCount = rooms.size() - bookedRoomIds.size();

        return availableCount >= requiredCount;
    }
}
