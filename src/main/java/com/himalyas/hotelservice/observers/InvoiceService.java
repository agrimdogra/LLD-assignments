package com.himalyas.hotelservice.observers;


import com.himalyas.hotelservice.exceptions.InvoiceNotFoundException;
import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.Invoice;
import com.himalyas.hotelservice.models.Payment;
import com.himalyas.hotelservice.repositories.InvoiceRepository;
import com.himalyas.hotelservice.services.BookingService;
import com.himalyas.hotelservice.stratergies.billing.BillingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService implements BookingObserver {
    BookingService bookingService;
    InvoiceRepository invoiceRepository;
    BillingStrategy billingStrategy;

    @Override
    public void onBookingCreated(Long bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        Integer billingAmount = billingStrategy.calculateBillingAmount(booking);

        Invoice invoiceCreated = Invoice.builder()
                .booking(booking)
                .billingAmount(billingAmount)
                .remainingAmount(billingAmount)
                .isPaymentComplete(Boolean.FALSE)
                .createdAt(LocalDateTime.now())
                .payments(new ArrayList<>())
                .build();

        invoiceRepository.save(invoiceCreated);
    }

    public void updatePayments(Long invoiceId, List<Payment> payments) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));

        int totalPaidNow = payments.stream().mapToInt(Payment::getAmount).sum();
        int newRemaining = invoice.getRemainingAmount() - totalPaidNow;

        // Update remaining and status
        invoice.setRemainingAmount(Math.max(newRemaining, 0));
        invoice.setIsPaymentComplete(invoice.getRemainingAmount() == 0);
        invoice.setModifiedAt(LocalDateTime.now());

        invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException(String.format("Invoice with id {%d} not found", id)));
    }

    public Invoice getInvoiceByBookingId(Long bookingId) {
        return invoiceRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new InvoiceNotFoundException("No invoice found for booking: " + bookingId));
    }

    public void markAsPaid(Long invoiceId) {
        Invoice invoice = getInvoiceById(invoiceId);
        invoice.setIsPaymentComplete(true);
        invoiceRepository.save(invoice);
    }
}
