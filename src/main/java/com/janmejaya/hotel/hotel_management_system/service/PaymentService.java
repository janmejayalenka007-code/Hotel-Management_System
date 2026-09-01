package com.janmejaya.hotel.hotel_management_system.service;

import com.janmejaya.hotel.hotel_management_system.dto.PaymentRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto makePayment(
            PaymentRequestDto dto);
}
