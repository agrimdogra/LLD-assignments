package com.himalyas.hotelservice.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Booking extends  BaseModel {
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Room> rooms;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User guest;
    private int numberOfGuests;
}
