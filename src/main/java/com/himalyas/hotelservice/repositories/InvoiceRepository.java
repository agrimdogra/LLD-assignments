package com.himalyas.hotelservice.repositories;

import com.himalyas.hotelservice.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByBookingId (Long bookingId);
}
