package com.janmejaya.hotel.hotel_management_system.service.impl;

import com.janmejaya.hotel.hotel_management_system.dto.PaymentRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.PaymentResponseDto;
import com.janmejaya.hotel.hotel_management_system.entity.Booking;
import com.janmejaya.hotel.hotel_management_system.entity.Payment;
import com.janmejaya.hotel.hotel_management_system.entity.Room;
import com.janmejaya.hotel.hotel_management_system.enums.BookingStatus;
import com.janmejaya.hotel.hotel_management_system.enums.PaymentStatus;
import com.janmejaya.hotel.hotel_management_system.repository.BookingRepository;
import com.janmejaya.hotel.hotel_management_system.repository.PaymentRepository;
import com.janmejaya.hotel.hotel_management_system.repository.RoomRepository;
import com.janmejaya.hotel.hotel_management_system.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository,
            RoomRepository roomRepository) {

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public PaymentResponseDto makePayment(
            PaymentRequestDto dto) {

        Room room = roomRepository.findById(
                        dto.getRoomId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found"));


        if (paymentRepository.existsByTransactionId(
                dto.getTransactionId())) {

            throw new RuntimeException(
                    "Transaction already processed.");
        }

        Booking booking = new Booking();

        booking.setGuestName(
                dto.getGuestName());

        booking.setGuestEmail(
                dto.getGuestEmail());

        booking.setCheckInDate(
                dto.getCheckInDate());

        booking.setCheckOutDate(
                dto.getCheckOutDate());

        booking.setTotalAmount(
                dto.getAmount());

        booking.setBookingStatus(
                BookingStatus.CONFIRMED);

        booking.setRoom(room);

        Booking savedBooking =
                bookingRepository.save(
                        booking);

        Payment payment = new Payment();

        payment.setAmount(
                dto.getAmount());

        payment.setPaymentMethod(
                dto.getPaymentMethod());

        payment.setTransactionId(
                dto.getTransactionId());

        payment.setPaymentStatus(
                PaymentStatus.SUCCESS);

        payment.setBooking(
                savedBooking);

        Payment savedPayment =
                paymentRepository.save(
                        payment);

        return new PaymentResponseDto(
                "Room booked successfully. Thank you.",
                savedBooking.getId(),
                savedPayment.getId(),
                savedPayment.getTransactionId()
        );
    }
}