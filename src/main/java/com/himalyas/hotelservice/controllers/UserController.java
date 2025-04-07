package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.request.CreateAdminRequestDto;
import com.himalyas.hotelservice.dtos.request.CreateGuestRequestDto;
import com.himalyas.hotelservice.dtos.request.CreateStaffRequestDto;
import com.himalyas.hotelservice.dtos.response.GuestResponseDto;
import com.himalyas.hotelservice.dtos.response.StaffResponseDto;
import com.himalyas.hotelservice.dtos.response.UserResponseDto;
import com.himalyas.hotelservice.exceptions.InvalidIdentityProvided;
import com.himalyas.hotelservice.models.Admin;
import com.himalyas.hotelservice.models.Guest;
import com.himalyas.hotelservice.models.IdentityType;
import com.himalyas.hotelservice.models.Staff;
import com.himalyas.hotelservice.models.User;
import com.himalyas.hotelservice.models.UserIdentity;
import com.himalyas.hotelservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class UserController {
    UserService userService;
    // COMMON USER ENDPOINTS

    // Get User by ID (Generic - Works for Guest, Staff, and Admin)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User userRequested = userService.getUserById(id);
        UserResponseDto user = UserResponseDto.builder()
                .name(userRequested.getName())
                .age(userRequested.getAge())
                .email(userRequested.getEmail())
                .address(userRequested.getAddress())
                .phoneNumber(userRequested.getPhoneNumber())
                .govIdentity(userRequested.getGovIdentity())
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    // Get all Users (Guests, Staff, Admins)
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userResponseDtos =  users.stream().map(user->{
            return UserResponseDto.builder()
                    .name(user.getName())
                    .age(user.getAge())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .phoneNumber(user.getPhoneNumber())
                    .govIdentity(user.getGovIdentity())
                    .build();
        })
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userResponseDtos);
    }

    // GUEST ENDPOINTS

    // Create Guest
    @PostMapping("/guest")
    public ResponseEntity<GuestResponseDto> createGuest(@RequestBody CreateGuestRequestDto createGuestRequestDto) {
        if(!isValidRequest(createGuestRequestDto)) {
            throw new InvalidIdentityProvided("The Government ID provided is invalid.");
        }
        else {
            UserIdentity userIdentity = UserIdentity.builder()
                    .type(IdentityType.AADHAR_CARD)             //hardCoded to aadhaar card for now
                    .identityNumber(createGuestRequestDto.getIdNumber())
                    .build();

            Guest guest = Guest.builder()
                    .name(createGuestRequestDto.getName())
                    .age(createGuestRequestDto.getAge())
                    .address(createGuestRequestDto.getAddress())
                    .email(createGuestRequestDto.getEmail())
                    .phoneNumber(createGuestRequestDto.getPhoneNumber())
                    .govIdentity(userIdentity)
                    .build();

            Guest guestSaved = userService.createGuest(guest);

            GuestResponseDto guestResponseDto = GuestResponseDto.builder()
                    .userId(guestSaved.getId())
                    .name(guestSaved.getName())
                    .age(guestSaved.getAge())
                    .address(guestSaved.getAddress())
                    .email(guestSaved.getEmail())
                    .phoneNumber(guestSaved.getPhoneNumber())
                    .govIdentity(guestSaved.getGovIdentity())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(guestResponseDto);
        }
    }

    // Get Guest by ID
    @GetMapping("/guest/{id}")
    public ResponseEntity<GuestResponseDto> getGuestById(@PathVariable Long id) {
        Guest guest = userService.getGuestById(id);
        GuestResponseDto guestResponseDto = GuestResponseDto.builder()
                .userId(guest.getId())
                .name(guest.getName())
                .age(guest.getAge())
                .address(guest.getAddress())
                .email(guest.getEmail())
                .phoneNumber(guest.getPhoneNumber())
                .govIdentity(guest.getGovIdentity())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(guestResponseDto);
    }

    // Get all Guests
    @GetMapping("/guest")
    public ResponseEntity<List<GuestResponseDto>> getAllGuests() {
        List<Guest> guests = userService.getAllGuests();
        List<GuestResponseDto> guestResponse = guests.stream()
                .map(guest -> {
                    return GuestResponseDto.builder()
                            .userId(guest.getId())
                            .name(guest.getName())
                            .age(guest.getAge())
                            .address(guest.getAddress())
                            .email(guest.getEmail())
                            .phoneNumber(guest.getPhoneNumber())
                            .govIdentity(guest.getGovIdentity())
                            .build();
                }).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(guestResponse);
    }

    //  STAFF ENDPOINTS

    // Create Staff
    @PostMapping("/staff")
    public ResponseEntity<StaffResponseDto> createStaff(@RequestBody CreateStaffRequestDto staffRequestDto) {
        UserIdentity userIdentity = UserIdentity.builder()
                .type(IdentityType.AADHAR_CARD)             //hardCoded to aadhaar card for now
                .identityNumber(staffRequestDto.getIdNumber())
                .build();
        Staff staffToBeSaved = Staff.builder()
                .name(staffRequestDto.getName())
                .age(staffRequestDto.getAge())
                .email(staffRequestDto.getEmail())
                .address(staffRequestDto.getAddress())
                .phoneNumber(staffRequestDto.getPhoneNumber())
                .govIdentity(userIdentity)
                .build();

        Staff staffSaved = userService.createStaff(staffToBeSaved);
        StaffResponseDto staffResponseDto = StaffResponseDto.builder()
                .userId(staffSaved.getId())
                .name(staffSaved.getName())
                .age(staffSaved.getAge())
                .email(staffSaved.getEmail())
                .phoneNumber(staffSaved.getPhoneNumber())
                .govIdentity(staffSaved.getGovIdentity())
                .address(staffSaved.getAddress())
                .empId(staffSaved.getEmpId())
                .empDepartment(staffSaved.getDepartment())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(staffResponseDto);
    }

    // Get Staff by ID
    @GetMapping("/staff/{id}")
    public ResponseEntity<StaffResponseDto> getStaffById(@PathVariable Long id) {
        Staff staff = userService.getStaffById(id);
        StaffResponseDto staffResponseDto = StaffResponseDto.builder()
                .userId(staff.getId())
                .name(staff.getName())
                .age(staff.getAge())
                .email(staff.getEmail())
                .phoneNumber(staff.getPhoneNumber())
                .govIdentity(staff.getGovIdentity())
                .address(staff.getAddress())
                .empId(staff.getEmpId())
                .empDepartment(staff.getDepartment())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(staffResponseDto);
    }

    // Get all Staff
    @GetMapping("/staff")
    public ResponseEntity<List<StaffResponseDto>> getAllStaff() {
        List<Staff> hotelStaff = userService.getAllStaff();
        List<StaffResponseDto> staffResponse = hotelStaff.stream()
                .map(staff -> {
                    return StaffResponseDto.builder()
                            .userId(staff.getId())
                            .name(staff.getName())
                            .age(staff.getAge())
                            .email(staff.getEmail())
                            .phoneNumber(staff.getPhoneNumber())
                            .govIdentity(staff.getGovIdentity())
                            .address(staff.getAddress())
                            .empId(staff.getEmpId())
                            .empDepartment(staff.getDepartment())
                            .build();
                }).toList();


        return ResponseEntity.status(HttpStatus.OK)
                .body(staffResponse);
    }

    //  ADMIN ENDPOINTS

    // Create Admin
    @PostMapping("/admin")
    public ResponseEntity<UserResponseDto> createAdmin(CreateAdminRequestDto adminRequestDto) {
        UserIdentity userIdentity = UserIdentity.builder()
                .type(IdentityType.AADHAR_CARD)             //hardCoded to aadhaar card for now
                .identityNumber(adminRequestDto.getIdNumber())
                .build();

        Admin admin = Admin.builder()
                .name(adminRequestDto.getName())
                .age(adminRequestDto.getAge())
                .address(adminRequestDto.getAddress())
                .email(adminRequestDto.getEmail())
                .phoneNumber(adminRequestDto.getPhoneNumber())
                .govIdentity(userIdentity)
                .build();

        Admin savedAdmin = userService.createAdmin(admin);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .userId(savedAdmin.getId())
                .name(savedAdmin.getName())
                .age(savedAdmin.getAge())
                .email(savedAdmin.getEmail())
                .phoneNumber(savedAdmin.getPhoneNumber())
                .address(savedAdmin.getAddress())
                .govIdentity(savedAdmin.getGovIdentity())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

    // Get Admin by ID
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserResponseDto> getAdminById(@PathVariable Long id) {
        Admin admin = userService.getAdminById(id);
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .userId(admin.getId())
                .name(admin.getName())
                .age(admin.getAge())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .address(admin.getAddress())
                .govIdentity(admin.getGovIdentity())
                .build();
        return  ResponseEntity.status(HttpStatus.OK)
                .body(userResponseDto);
    }

    // Get all Admins
    @GetMapping("/admin")
    public ResponseEntity<List<UserResponseDto>> getAllAdmins() {
        List<Admin> admins = userService.getAllAdmins();
        List<UserResponseDto> adminResponse = admins.stream()
                .map(admin -> {
                    return UserResponseDto.builder()
                            .userId(admin.getId())
                            .name(admin.getName())
                            .age(admin.getAge())
                            .email(admin.getEmail())
                            .phoneNumber(admin.getPhoneNumber())
                            .address(admin.getAddress())
                            .govIdentity(admin.getGovIdentity())
                            .build();
                }).toList();
        return  ResponseEntity.status(HttpStatus.OK)
                .body(adminResponse);
    }

    private boolean isValidRequest(CreateGuestRequestDto createGuestRequestDto) {
        if(createGuestRequestDto.getIdentityType() == null || createGuestRequestDto.getIdNumber() == null) {
            return false;
        }
        return true;
    }
}
