package com.himalyas.hotelservice.controllers;

import com.himalyas.hotelservice.dtos.response.InvoiceResponseDto;
import com.himalyas.hotelservice.models.Invoice;
import com.himalyas.hotelservice.observers.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        InvoiceResponseDto invoiceResponseDto = InvoiceResponseDto.builder()
                .invoiceId(invoice.getId())
                .billingAmount(invoice.getBillingAmount())
                .remainingAmount(invoice.getRemainingAmount())
                .booking(invoice.getBooking())
                .payments(invoice.getPayments())
                .isPaymentComplete(invoice.getIsPaymentComplete())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceResponseDto);
    }

    // Get invoice by booking ID
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceByBookingId(@PathVariable Long bookingId) {
        Invoice invoice = invoiceService.getInvoiceById(bookingId);
        InvoiceResponseDto invoiceResponseDto = InvoiceResponseDto.builder()
                .invoiceId(invoice.getId())
                .billingAmount(invoice.getBillingAmount())
                .remainingAmount(invoice.getRemainingAmount())
                .booking(invoice.getBooking())
                .payments(invoice.getPayments())
                .isPaymentComplete(invoice.getIsPaymentComplete())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceResponseDto);
    }

}
