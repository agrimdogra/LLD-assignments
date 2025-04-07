package com.himalyas.hotelservice.stratergies.room_allocation;

import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.models.RoomType;
import org.springframework.context.annotation.Primary;

import java.util.List;

public interface RoomAllocationStrategy {
    public List<Room> allocateRoom(RoomType roomType, Integer numberOfRooms, Long bookingId);
}
