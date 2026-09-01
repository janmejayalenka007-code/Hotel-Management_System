package com.janmejaya.hotel.hotel_management_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotelResponseDto {

    private Long id;

    private String hotelName;

    private String city;

    private String state;

    private String address;

    private String description;

    private String contactNumber;

    private LocalDateTime createdAt;
}