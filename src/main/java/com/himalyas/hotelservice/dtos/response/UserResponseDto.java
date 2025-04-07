package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.UserIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {
    private Long userId;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String address;
    private UserIdentity govIdentity;
}
