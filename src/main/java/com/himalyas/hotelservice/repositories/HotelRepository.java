package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
