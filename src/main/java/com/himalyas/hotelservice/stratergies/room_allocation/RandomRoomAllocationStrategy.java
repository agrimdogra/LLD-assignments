package com.himalyas.hotelservice.stratergies.room_allocation;

import com.himalyas.hotelservice.exceptions.NotEnoughRoomsAvailableException;
import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.models.RoomInventory;
import com.himalyas.hotelservice.models.RoomStatus;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.services.BookingService;
import com.himalyas.hotelservice.services.RoomService;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Primary
public class RandomRoomAllocationStrategy implements RoomAllocationStrategy{
    RoomService roomService;
    RoomInventory roomInventory;
    BookingService bookingService;


    @Override
    public List<Room> allocateRoom(RoomType roomType, Integer numberOfRooms, Long bookingId) {
        List<Room> roomsAssigned = new ArrayList<>();
        List<Room> roomsAvailable = roomService.getRoomByRoomType(roomType);
        Booking booking = bookingService.getBooking(bookingId);
        if(roomsAvailable.size() < numberOfRooms) {
            throw new NotEnoughRoomsAvailableException(
                    String.format("Only %d %s rooms are available, but %d were requested. Please try with fewer rooms or choose a different room type.",
                            roomsAvailable.size(), roomType.name(), numberOfRooms)
            );
        }
        List<Room> roomsAvailableForBooking = roomsAvailable.stream()
                .filter(room -> room.getRoomStatus()== RoomStatus.AVAILABLE)
                .toList();

        for(int i=0; i<numberOfRooms; i++) {
            Room room = roomsAvailableForBooking.get(i);
            room.setRoomStatus(RoomStatus.BOOKED);
            room.setBooking(booking);
            roomsAssigned.add(room);
        }
        return roomsAssigned;
    }
}
