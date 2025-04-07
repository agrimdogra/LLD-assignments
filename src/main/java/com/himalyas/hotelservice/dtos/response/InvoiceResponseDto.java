package com.himalyas.hotelservice.dtos.response;

import com.himalyas.hotelservice.models.Booking;
import com.himalyas.hotelservice.models.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceResponseDto {
    private Long invoiceId;
    private Booking booking;
    private Integer billingAmount;
    private Integer remainingAmount;
    private List<Payment> payments;
    private Boolean isPaymentComplete;

}
