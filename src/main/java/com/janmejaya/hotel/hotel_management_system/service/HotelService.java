package com.janmejaya.hotel.hotel_management_system.service;

import com.janmejaya.hotel.hotel_management_system.dto.HotelRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.HotelResponseDto;
import com.janmejaya.hotel.hotel_management_system.dto.MessageResponseDto;

import java.util.List;

public interface HotelService {

    MessageResponseDto addHotel(
            HotelRequestDto dto);

    MessageResponseDto updateHotel(
            Long id,
            HotelRequestDto dto);

    MessageResponseDto deleteHotel(
            Long id);

    HotelResponseDto getHotelById(
            Long id);

    List<HotelResponseDto> getAllHotels();
    List<HotelResponseDto> getHotelsByCity(
            String city);
}