package com.himalyas.hotelservice.services;

import com.himalyas.hotelservice.dtos.request.CreateBookingRequestDto;
import com.himalyas.hotelservice.dtos.response.RoomInventoryResponseDto;
import com.himalyas.hotelservice.exceptions.BookingNotFoundException;
import com.himalyas.hotelservice.exceptions.PaymentPendingException;
import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.BookingStatus;
import com.himalyas.hotelservice.models.Guest;
import com.himalyas.hotelservice.models.Room;
import com.himalyas.hotelservice.models.RoomStatus;
import com.himalyas.hotelservice.models.RoomType;
import com.himalyas.hotelservice.models.Staff;
import com.himalyas.hotelservice.observers.BookingObserver;
import com.himalyas.hotelservice.observers.InvoiceService;
import com.himalyas.hotelservice.repositories.BookingRepository;
import com.himalyas.hotelservice.stratergies.room_allocation.RoomAllocationStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private RoomService roomService;
    private UserService userService;
    private RoomInventoryService roomInventoryService;
    private RoomAllocationStrategy roomAllocationStrategy;
    private BookingRepository bookingRepository;
    private InvoiceService invoiceService;
    private final List<BookingObserver> observers = new ArrayList<>();

    public BookingService(RoomService roomService, UserService userService, RoomInventoryService roomInventoryService,
                          RoomAllocationStrategy roomAllocationStrategy, BookingRepository bookingRepository, InvoiceService invoiceService) {
        this.roomService = roomService;
        this.userService = userService;
        this.roomInventoryService = roomInventoryService;
        this.roomAllocationStrategy = roomAllocationStrategy;
        this.bookingRepository = bookingRepository;
        this.invoiceService = invoiceService;

        // Register observer(s)
        registerObserver(invoiceService);
    }

    public void registerObserver(BookingObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Long bookingId) {
        for (BookingObserver observer : observers) {
            observer.onBookingCreated(bookingId);
        }
    }

    @Transactional
    public Booking createBooking (CreateBookingRequestDto createBookingRequestDto) {
        Guest guest = findGuestForBooking(createBookingRequestDto.getGuestId());
        Staff staff = findStaffForBooking(createBookingRequestDto.getStaffId());
        Booking booking = Booking.builder()
                .guest(guest)
                .bookedBy(staff)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .bookingStatus(BookingStatus.PENDING)
                .checkInDate(createBookingRequestDto.getCheckInDate())
                .checkOutDate(createBookingRequestDto.getCheckOutDate())
                .numberOfGuests(createBookingRequestDto.getNumberOfGuests())
                .build();
        Booking savedBooking = bookingRepository.save(booking);
        Long bookingId = savedBooking.getId();

        List<Room> allocatedRooms = roomAllocationStrategy.allocateRoom
                (createBookingRequestDto.getRoomType(), createBookingRequestDto.getNumberOfRooms(), bookingId);
        allocatedRooms.forEach(
                room->roomService.saveRoom(room)
        );

        RoomInventoryResponseDto roomInventoryResponseDto = roomInventoryService.decreaseAvailability
                (createBookingRequestDto.getRoomType(), createBookingRequestDto.getNumberOfRooms(), createBookingRequestDto.getHotelId());

        booking.setRooms(allocatedRooms);
        notifyObservers(savedBooking.getId());
        return bookingRepository.save(booking);

    }

    private Staff findStaffForBooking(Long staffId) {
        if (staffId == null) {
            return null;
        }
        return userService.getStaffById(staffId);
    }

    private Guest findGuestForBooking(Long guestId) {
        return userService.getGuestById(guestId);
    }

    @Transactional
    public Booking completeBooking (Long bookingId) {
        Optional<Booking> booking1 = bookingRepository.findById(bookingId);
        if(booking1.isEmpty()) {
            throw new BookingNotFoundException(String.format("Booking with id {%d} not found",bookingId));
        }
        Booking booking = booking1.get();
        if (booking.getBookingStatus() == BookingStatus.COMPLETED) {
            throw new IllegalStateException("Booking already completed.");
        }

        if (!invoiceService.getInvoiceById(bookingId).getIsPaymentComplete()) {
            throw new PaymentPendingException("Payment not completed for booking ID: " + bookingId);
        }

        // booking status change
        booking.setBookingStatus(BookingStatus.COMPLETED);
        booking.setModifiedAt(LocalDateTime.now());

        // release rooms
        List<Room> rooms = booking.getRooms();
        if (rooms == null || rooms.isEmpty()) {
            throw new IllegalStateException("No rooms associated with booking.");
        }
        for (Room room : booking.getRooms()) {
            roomInventoryService.increaseAvailability(room.getRoomType(), 1, room.getHotel().getId());
            room.setRoomStatus(RoomStatus.AVAILABLE);
            room.setBooking(null);
        }

        List<Room> roomsChanged =  roomService.saveAllRooms(rooms);
        return bookingRepository.save(booking);
    }

    public Booking cancellBooking (Long bookingId) {
        Optional<Booking> booking1 = bookingRepository.findById(bookingId);
        if(booking1.isEmpty()) {
            throw new BookingNotFoundException(String.format("Booking with id {%d} not found",bookingId));
        }
        Booking booking = booking1.get();
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking already completed.");
        }

        // booking status change
        booking.setBookingStatus(BookingStatus.COMPLETED);
        booking.setModifiedAt(LocalDateTime.now());

        // release rooms
        List<Room> rooms = booking.getRooms();
        if (rooms == null || rooms.isEmpty()) {
            throw new IllegalStateException("No rooms associated with booking.");
        }
        for (Room room : booking.getRooms()) {
            roomInventoryService.increaseAvailability(room.getRoomType(), 1, room.getHotel().getId());
            room.setRoomStatus(RoomStatus.AVAILABLE);
            room.setBooking(null);
        }

        List<Room> roomsChanged =  roomService.saveAllRooms(rooms);
        return bookingRepository.save(booking);
    }



    public Booking getBooking (Long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()) {
            throw new BookingNotFoundException(String.format("Booking with id {%d} not found",bookingId));
        }
        else {
            return optionalBooking.get();
        }
    }

    public List<Long> findBookedRoomIds (Long hotelId, RoomType roomType, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return  bookingRepository.findBookedRoomIds(hotelId,roomType,checkInDate,checkOutDate);
    }
}
