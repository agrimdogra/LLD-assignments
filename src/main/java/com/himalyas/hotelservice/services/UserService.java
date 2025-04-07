package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.dtos.request.CreateAdminRequestDto;
import com.himalyas.hotelservice.dtos.request.CreateGuestRequestDto;
import com.himalyas.hotelservice.dtos.request.CreateStaffRequestDto;
import com.himalyas.hotelservice.exceptions.StaffNotSavedException;
import com.himalyas.hotelservice.exceptions.UserNotFoundException;
import com.himalyas.hotelservice.models.Admin;
import com.himalyas.hotelservice.models.Guest;
import com.himalyas.hotelservice.models.Staff;
import com.himalyas.hotelservice.models.User;
import com.himalyas.hotelservice.repositories.AdminRepository;
import com.himalyas.hotelservice.repositories.GuestRepository;
import com.himalyas.hotelservice.repositories.StaffRepository;
import com.himalyas.hotelservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private GuestRepository guestRepository;
    private StaffRepository staffRepository;

    // Get a User by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    // Get all Users (Guests, Staff, Admins)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a Guest
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    // Get a Guest by ID
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Guest not found with ID: " + id));
    }

    // Get all Guests
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    // Create a Staff
    public Staff createStaff(Staff staff) {
        Optional<Staff> staffSaved = Optional.of(staffRepository.save(staff));
        if(staffSaved.isEmpty()) {
            throw new StaffNotSavedException("Unable to save the staff");
        }
        else{
            return staffSaved.get();
        }
    }

    // Get a Staff by ID
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Staff not found with ID: " + id));
    }

    // Get all Staff
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Create an Admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Get an Admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Admin not found with ID: " + id));
    }

    // Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
