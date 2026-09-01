package com.janmejaya.hotel.hotel_management_system.controller;

import com.janmejaya.hotel.hotel_management_system.dto.*;
import com.janmejaya.hotel.hotel_management_system.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175"
})
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingCreateResponseDto>
    createBooking(
            @Valid
            @RequestBody
            BookingRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        bookingService.createBooking(dto)
                );
    }


    @PostMapping("/cancel-preview")
    public ResponseEntity<CancelBookingResponseDto>
    previewCancellation(
            @Valid
            @RequestBody
            CancelBookingRequestDto dto) {

        return ResponseEntity.ok(
                bookingService.previewCancellation(dto));
    }


    @PostMapping("/cancel-confirm")
    public ResponseEntity<ConfirmCancelResponseDto>
    confirmCancellation(
            @Valid
            @RequestBody
            ConfirmCancelRequestDto dto) {

        return ResponseEntity.ok(
                bookingService.confirmCancellation(dto)
        );
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<List<MyBookingsResponseDto>>
    getMyBookings(
            @PathVariable String email) {

        return ResponseEntity.ok(
                bookingService.getMyBookings(
                        email));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDetailsResponseDto>
    getBookingDetails(
            @PathVariable Long bookingId) {

        return ResponseEntity.ok(
                bookingService.getBookingDetails(
                        bookingId));
    }
}

