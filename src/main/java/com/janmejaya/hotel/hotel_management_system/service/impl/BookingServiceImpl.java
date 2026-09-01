package com.janmejaya.hotel.hotel_management_system.service.impl;

import com.janmejaya.hotel.hotel_management_system.dto.*;
import com.janmejaya.hotel.hotel_management_system.entity.Booking;
import com.janmejaya.hotel.hotel_management_system.entity.Payment;
import com.janmejaya.hotel.hotel_management_system.entity.Room;
import com.janmejaya.hotel.hotel_management_system.enums.BookingStatus;
import com.janmejaya.hotel.hotel_management_system.enums.PaymentStatus;
import com.janmejaya.hotel.hotel_management_system.repository.BookingRepository;
import com.janmejaya.hotel.hotel_management_system.repository.PaymentRepository;
import com.janmejaya.hotel.hotel_management_system.repository.RoomRepository;
import com.janmejaya.hotel.hotel_management_system.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final PaymentRepository paymentRepository;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            RoomRepository roomRepository,
            PaymentRepository paymentRepository) {

        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.paymentRepository = paymentRepository;
    }

    // methods...


    @Override
    public BookingCreateResponseDto createBooking(
            BookingRequestDto dto) {

        Room room = roomRepository.findById(
                        dto.getRoomId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found. Please select a valid room."));

        boolean alreadyBooked =
                bookingRepository
                        .existsByRoomIdAndBookingStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(
                                dto.getRoomId(),
                                BookingStatus.CONFIRMED,
                                dto.getCheckOutDate(),
                                dto.getCheckInDate()
                        );

        if (alreadyBooked) {

            throw new RuntimeException(
                    "Room is already booked for the selected dates.");
        }

        long numberOfDays =
                ChronoUnit.DAYS.between(
                        dto.getCheckInDate(),
                        dto.getCheckOutDate());

        double totalAmount =
                numberOfDays * room.getPricePerNight();

        return new BookingCreateResponseDto(
                true,
                totalAmount,
                "Room is available"
        );
    }

    @Override
    public CancelBookingResponseDto previewCancellation(
            CancelBookingRequestDto dto) {

        Booking booking =
                bookingRepository.findById(
                                dto.getBookingId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"));

        long hoursRemaining =
                ChronoUnit.HOURS.between(
                        LocalDateTime.now(),
                        booking.getCheckInDate().atStartOfDay());

        double totalAmount =
                booking.getTotalAmount();

        double deductionAmount;
        double refundAmount;

        if (hoursRemaining >= 48) {

            deductionAmount = 0;
            refundAmount = totalAmount;

        } else if (hoursRemaining >= 24) {

            deductionAmount = totalAmount * 0.20;
            refundAmount = totalAmount - deductionAmount;

        } else {

            deductionAmount = totalAmount * 0.50;
            refundAmount = totalAmount - deductionAmount;
        }

        return new CancelBookingResponseDto(
                booking.getId(),
                totalAmount,
                deductionAmount,
                refundAmount,
                dto.getReason(),
                "₹" + refundAmount +
                        " will be refunded. Are you sure you want to cancel this booking?"
        );
    }
    @Override
    public ConfirmCancelResponseDto confirmCancellation(
            ConfirmCancelRequestDto dto) {

        Booking booking =
                bookingRepository.findById(
                                dto.getBookingId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"));

        if (booking.getBookingStatus()
                == BookingStatus.CANCELLED) {

            throw new RuntimeException(
                    "Booking is already cancelled");
        }

        Payment payment =
                paymentRepository.findByBookingId(
                                booking.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"));

        long hoursRemaining =
                ChronoUnit.HOURS.between(
                        LocalDateTime.now(),
                        booking.getCheckInDate()
                                .atStartOfDay());

        double totalAmount =
                booking.getTotalAmount();

        double refundAmount;

        if (hoursRemaining >= 48) {

            refundAmount = totalAmount;

        } else if (hoursRemaining >= 24) {

            refundAmount =
                    totalAmount -
                            (totalAmount * 0.20);

        } else {

            refundAmount =
                    totalAmount -
                            (totalAmount * 0.50);
        }

        booking.setBookingStatus(
                BookingStatus.CANCELLED);

        booking.setCancellationReason(
                dto.getReason());

        payment.setPaymentStatus(
                PaymentStatus.REFUNDED);

        bookingRepository.save(
                booking);

        paymentRepository.save(
                payment);

        ConfirmCancelResponseDto response =
                new ConfirmCancelResponseDto();

        response.setBookingId(
                booking.getId());

        response.setRefundAmount(
                refundAmount);

        response.setMessage(
                "Booking cancelled successfully.");

        return response;
    }


    @Override
    public List<MyBookingsResponseDto> getMyBookings(
            String guestEmail) {

        return bookingRepository
                .findByGuestEmail(guestEmail)
                .stream()
                .map(booking -> {

                    MyBookingsResponseDto dto =
                            new MyBookingsResponseDto();

                    dto.setBookingId(
                            booking.getId());

                    dto.setHotelName(
                            booking.getRoom()
                                    .getHotel()
                                    .getHotelName());

                    dto.setRoomNumber(
                            booking.getRoom()
                                    .getRoomNumber());

                    dto.setCheckInDate(
                            booking.getCheckInDate());

                    dto.setCheckOutDate(
                            booking.getCheckOutDate());

                    dto.setTotalAmount(
                            booking.getTotalAmount());

                    dto.setBookingStatus(
                            booking.getBookingStatus());

                    return dto;
                })
                .toList();
    }


    @Override
    public BookingDetailsResponseDto getBookingDetails(
            Long bookingId) {

        Booking booking =
                bookingRepository.findById(
                                bookingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"));

        Payment payment =
                paymentRepository.findByBookingId(
                                bookingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"));

        BookingDetailsResponseDto dto =
                new BookingDetailsResponseDto();

        dto.setBookingId(
                booking.getId());

        dto.setGuestName(
                booking.getGuestName());

        dto.setGuestEmail(
                booking.getGuestEmail());

        dto.setHotelName(
                booking.getRoom()
                        .getHotel()
                        .getHotelName());

        dto.setRoomNumber(
                booking.getRoom()
                        .getRoomNumber());

        dto.setRoomType(
                booking.getRoom()
                        .getRoomType()
                        .name());

        dto.setCheckInDate(
                booking.getCheckInDate());

        dto.setCheckOutDate(
                booking.getCheckOutDate());

        dto.setTotalAmount(
                booking.getTotalAmount());

        dto.setBookingStatus(
                booking.getBookingStatus());

        dto.setPaymentStatus(
                payment.getPaymentStatus());

        dto.setTransactionId(
                payment.getTransactionId());

        return dto;
    }
}