package com.himalyas.hotelservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class HotelRequestDto {
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public HotelRequestDto() {
    }

    public void setNumOfFloors(Integer numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public HotelRequestDto(String hotelName, String hotelLocation, String hotelAddress, Integer numOfFloors) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.hotelAddress = hotelAddress;
        this.numOfFloors = numOfFloors;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public Integer getNumOfFloors() {
        return numOfFloors;
    }

    private String hotelName;
    private String hotelLocation;
    private String hotelAddress;
    private Integer numOfFloors;
}
