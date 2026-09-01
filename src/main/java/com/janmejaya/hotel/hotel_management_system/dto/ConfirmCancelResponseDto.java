package com.janmejaya.hotel.hotel_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmCancelResponseDto {

    private Long bookingId;

    private Double refundAmount;

    private String message;
}
