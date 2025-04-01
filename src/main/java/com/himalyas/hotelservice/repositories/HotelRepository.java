package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
