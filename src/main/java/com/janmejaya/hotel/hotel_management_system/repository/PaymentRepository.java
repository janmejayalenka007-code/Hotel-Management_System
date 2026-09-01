package com.janmejaya.hotel.hotel_management_system.repository;

import com.janmejaya.hotel.hotel_management_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
    boolean existsByTransactionId(
            String transactionId);
    Optional<Payment> findByBookingId(
            Long bookingId);
}
