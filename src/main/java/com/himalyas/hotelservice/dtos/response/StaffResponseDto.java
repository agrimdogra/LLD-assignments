package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.UserIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffResponseDto {
    private Long userId;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String address;
    private UserIdentity govIdentity;
    private String empId;
    private String empDepartment;
}
