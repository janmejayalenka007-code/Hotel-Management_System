package com.janmejaya.hotel.hotel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreateResponseDto {

    private String message;

    private Long roomId;

    private Long hotelId;
}