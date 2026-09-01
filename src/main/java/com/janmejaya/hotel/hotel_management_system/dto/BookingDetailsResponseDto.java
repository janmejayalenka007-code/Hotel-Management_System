package com.janmejaya.hotel.hotel_management_system.dto;

import com.janmejaya.hotel.hotel_management_system.enums.BookingStatus;
import com.janmejaya.hotel.hotel_management_system.enums.PaymentStatus;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDetailsResponseDto {

    private Long bookingId;

    private String guestName;

    private String guestEmail;

    private String hotelName;

    private String roomNumber;

    private String roomType;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Double totalAmount;

    private BookingStatus bookingStatus;

    private PaymentStatus paymentStatus;

    private String transactionId;
}
