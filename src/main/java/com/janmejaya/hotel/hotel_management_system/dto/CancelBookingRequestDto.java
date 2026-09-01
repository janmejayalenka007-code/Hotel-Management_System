package com.janmejaya.hotel.hotel_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelBookingRequestDto {

    @NotNull(message = "Booking id is required")
    private Long bookingId;

    @NotBlank(message = "Cancellation reason is required")
    private String reason;
}