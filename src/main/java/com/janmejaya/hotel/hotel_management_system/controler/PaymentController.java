package com.janmejaya.hotel.hotel_management_system.controller;

import com.janmejaya.hotel.hotel_management_system.dto.PaymentRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.PaymentResponseDto;
import com.janmejaya.hotel.hotel_management_system.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "https://spectacular-beijinho-17818b.netlify.app"
})
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto>
    makePayment(
            @Valid @RequestBody
            PaymentRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        paymentService
                                .makePayment(dto)
                );
    }
}