package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.RoomInventory;
import com.himalyas.hotelservice.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomInventoryRepository extends JpaRepository<RoomInventory, Long> {

    Optional<RoomInventory> findByRoomTypeAndHotelId(RoomType roomType, Long hotelId);
}
