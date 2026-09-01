package com.janmejaya.hotel.hotel_management_system.service;

import com.janmejaya.hotel.hotel_management_system.dto.*;

import java.util.List;

public interface BookingService {

    BookingCreateResponseDto createBooking(
            BookingRequestDto dto);

    CancelBookingResponseDto previewCancellation(
            CancelBookingRequestDto dto);

    ConfirmCancelResponseDto confirmCancellation(
            ConfirmCancelRequestDto dto);
    List<MyBookingsResponseDto> getMyBookings(
            String guestEmail);

    BookingDetailsResponseDto getBookingDetails(
            Long bookingId);
}