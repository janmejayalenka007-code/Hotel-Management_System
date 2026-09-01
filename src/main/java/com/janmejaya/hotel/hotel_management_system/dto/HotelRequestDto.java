package com.janmejaya.hotel.hotel_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class HotelRequestDto {

    @NotBlank(message = "Hotel name is required")
    @Size(min = 3, max = 100)
    private String hotelName;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255)
    private String address;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "Contact number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Contact number must be 10 digits"
    )
    private String contactNumber;
}