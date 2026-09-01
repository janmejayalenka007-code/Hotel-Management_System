package com.janmejaya.hotel.hotel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {

    private String message;

    private Long bookingId;

    private Long paymentId;

    private String transactionId;
}
