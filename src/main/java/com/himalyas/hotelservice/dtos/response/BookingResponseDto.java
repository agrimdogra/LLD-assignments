package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.BookingStatus;
import com.himalyas.hotelservice.models.Guest;
import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.models.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponseDto {
    private Long bookingId;
    private Guest bookingOwner;
    private Staff bookedBy;
    private BookingStatus bookingStatus;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Integer numberOfGuests;
    private LocalDateTime createdAt;
    private List<Room> roomsBooked;

}
