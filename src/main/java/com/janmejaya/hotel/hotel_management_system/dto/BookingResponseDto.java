package com.janmejaya.hotel.hotel_management_system.dto;

import com.janmejaya.hotel.hotel_management_system.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponseDto {

    private Long id;

    private String guestName;

    private String guestEmail;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Double totalAmount;

    private BookingStatus bookingStatus;

    private Long roomId;

    private LocalDateTime createdAt;
}
