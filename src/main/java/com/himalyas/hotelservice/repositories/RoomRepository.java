package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    public List<Room> findAllByRoomType(RoomType roomType);

    public List<Room> findByHotelIdAndRoomType (Long hotelId, RoomType roomType);

}
