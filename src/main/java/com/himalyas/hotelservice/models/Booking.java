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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private Guest guest;
    private Integer numberOfGuests;
    private Staff bookedBy;
}
