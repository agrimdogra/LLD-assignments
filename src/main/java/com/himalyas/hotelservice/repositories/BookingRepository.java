package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT r.id FROM Booking b JOIN b.rooms r " +
            "WHERE r.hotel.id = :hotelId " +
            "AND r.roomType = :roomType " +
            "AND b.bookingStatus != 'CANCELLED' " +
            "AND (b.checkInDate < :checkOut AND b.checkOutDate > :checkIn)")
    List<Long> findBookedRoomIds(@Param("hotelId") Long hotelId,
                                 @Param("roomType") RoomType roomType,
                                 @Param("checkIn") LocalDateTime checkIn,
                                 @Param("checkOut") LocalDateTime checkOut);
}
