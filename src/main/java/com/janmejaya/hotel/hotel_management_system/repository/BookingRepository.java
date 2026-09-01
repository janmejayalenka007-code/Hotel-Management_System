package com.janmejaya.hotel.hotel_management_system.repository;

import com.janmejaya.hotel.hotel_management_system.entity.Booking;
import com.janmejaya.hotel.hotel_management_system.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    boolean existsByRoomIdAndBookingStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(
            Long roomId,
            BookingStatus bookingStatus,
            LocalDate checkOutDate,
            LocalDate checkInDate
    );


    List<Booking> findByGuestEmail(
            String guestEmail);
}