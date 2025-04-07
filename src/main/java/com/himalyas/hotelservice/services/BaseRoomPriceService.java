package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.dtos.request.UpdateRoomBasePriceDto;
import com.himalyas.hotelservice.exceptions.RoomTypeNotFoundException;
import com.himalyas.hotelservice.models.BasePrice;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.repositories.BaseRoomPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BaseRoomPriceService {
    BaseRoomPriceRepository roomPriceRepository;

    public BasePrice updateBasePrice(UpdateRoomBasePriceDto updateRoomBasePriceDto) {
        Optional<BasePrice> basePrice = roomPriceRepository.findByRoomType(updateRoomBasePriceDto.getRoomType());
        if (basePrice.isPresent()) {
            BasePrice basePrice1 = basePrice.get();
            basePrice1.setModifiedAt(LocalDateTime.now());
            basePrice1.setBasePrice(updateRoomBasePriceDto.getPrice());

            return roomPriceRepository.save(basePrice1);
        }

        else {
            BasePrice basePriceToBeSaved = BasePrice.builder()
                    .roomType(updateRoomBasePriceDto.getRoomType())
                    .basePrice(updateRoomBasePriceDto.getPrice())
                    .build();

            return roomPriceRepository.save(basePriceToBeSaved);
        }
    }

    public Integer getRoomBasePrice (RoomType roomType) {
        Optional<BasePrice> basePrice = roomPriceRepository.findByRoomType(roomType);
        if (basePrice.isPresent()) {
            return  basePrice.get().getBasePrice();
        }
        else {
            throw new RoomTypeNotFoundException("Room type "+ roomType +" not found." + " " +
                    "Available room types are "+ RoomType.DELUXE +" "+RoomType.DOUBLE +" "+RoomType.SINGLE+" " +RoomType.SUITE);
        }
    }

}
