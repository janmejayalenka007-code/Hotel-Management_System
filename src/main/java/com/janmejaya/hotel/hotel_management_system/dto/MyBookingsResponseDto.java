package com.janmejaya.hotel.hotel_management_system.dto;

import com.janmejaya.hotel.hotel_management_system.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MyBookingsResponseDto {

    private Long bookingId;

    private String hotelName;

    private String roomNumber;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Double totalAmount;

    private BookingStatus bookingStatus;
}
