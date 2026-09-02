package com.janmejaya.hotel.hotel_management_system.dto;

import com.janmejaya.hotel.hotel_management_system.enums.RoomType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Room type is required")
    private RoomType roomType;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double pricePerNight;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than 0")
    private Integer capacity;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Hotel id is required")
    private Long hotelId;
}
