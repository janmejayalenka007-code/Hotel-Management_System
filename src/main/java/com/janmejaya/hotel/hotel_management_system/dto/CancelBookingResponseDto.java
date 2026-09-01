package com.janmejaya.hotel.hotel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CancelBookingResponseDto {

    private Long bookingId;

    private Double totalAmount;

    private Double deductionAmount;

    private Double refundAmount;

    private String reason;

    private String message;
}