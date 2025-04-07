package com.himalyas.hotelservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room extends BaseModel {
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    private Integer roomNumber;
    private Integer floor;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
