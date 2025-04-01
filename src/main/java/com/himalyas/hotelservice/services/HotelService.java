package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.models.Hotel;
import com.himalyas.hotelservice.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelService {

    HotelRepository hotelRepository;
    public Hotel addHotel(Hotel hotel) {
        Hotel h = hotelRepository.save(hotel);
        return h;
    }
}
