package com.himalyas.hotelservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAdminRequestDto {
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String address;
    private String identityType;
    private String idNumber;
}
