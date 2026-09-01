package com.janmejaya.hotel.hotel_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmCancelRequestDto {

    private Long bookingId;

    private String reason;
}
