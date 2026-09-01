package com.janmejaya.hotel.hotel_management_system.dto;
import com.janmejaya.hotel.hotel_management_system.entity.RoomType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponseDto {

    private Long id;

    private String roomNumber;

    private RoomType roomType;

    private Double pricePerNight;

    private Integer capacity;

    private String description;

    private Boolean isActive;

    private Boolean isAvailable;

    private Long hotelId;
}