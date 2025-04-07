package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.dtos.request.AddRoomsRequestDto;
import com.himalyas.hotelservice.exceptions.HotelNotFoundException;
import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class HotelService {

    HotelRepository hotelRepository;
    RoomService roomService;

    public Optional<Hotel> addHotel(Hotel hotel) {
        Hotel hotel1 = hotelRepository.save(hotel);
        return Optional.of(hotel);
    }

    public  void removeHotel(Long hotelId) {
        Optional<Hotel> hotelToBeRemoved = hotelRepository.findById(hotelId);
        if(hotelToBeRemoved.isPresent()) {
            hotelRepository.delete(hotelToBeRemoved.get());
        }
        else {
            throw new HotelNotFoundException("Hotel with given ID "+ hotelId +" does not exist");
        }
    }

    public Hotel getHotelById(Long hotelId) {
        Optional<Hotel> hotelFound = hotelRepository.findById(hotelId);
        if (hotelFound.isPresent()){
            return hotelFound.get();
        }
        else {
            throw new HotelNotFoundException("Hotel with given ID "+ hotelId +" does not exist");
        }
    }

    public void attachRooms(Long hotelId, AddRoomsRequestDto addRoomsRequestDto) {
        Optional<Hotel> hotel =  hotelRepository.findById(hotelId);
        // Check if Hotel a valid hotel
        if(hotel.isEmpty()) {
            throw new HotelNotFoundException("Hotel with given ID "+ hotelId +" does not exist");
        }

        // Keep only valid rooms to be added in hotel entity
        List<Long> roomIds = addRoomsRequestDto.getRoomsIds();
        List<Room> rooms = roomService.getRoomsById(roomIds);
        List<Room> validRooms = new ArrayList<>();
        List<Long> invalidRoomIds = new ArrayList<>();

        for(Room room : rooms) {
            if(room.getHotel() == null) {
                room.setHotel(hotel.get());
                validRooms.add(room);
            }
            else{
                invalidRoomIds.add(room.getId());
            }
        }

        if(!validRooms.isEmpty()) {
            roomService.saveAllRooms(validRooms);
        }

        log.warn("Invalid rooms will not be added to hotel:  {}", invalidRoomIds.toString());

    }

    private boolean isValidRoom(Long roomId) {
        //To BE IMPLEMENTED
        return true;
    }
}
