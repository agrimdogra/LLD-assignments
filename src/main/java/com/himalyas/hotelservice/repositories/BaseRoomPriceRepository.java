package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.BasePrice;
import com.himalyas.hotelservice.models.RoomType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseRoomPriceRepository extends JpaRepository<BasePrice, Long> {
    public Optional<BasePrice> findByRoomType(RoomType roomType);
}
