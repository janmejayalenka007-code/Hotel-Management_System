package com.janmejaya.hotel.hotel_management_system.service;

import com.janmejaya.hotel.hotel_management_system.dto.*;

import java.util.List;

public interface RoomService {

    RoomCreateResponseDto addRoom(
            RoomRequestDto dto);
    MessageResponseDto updateRoom(
            Long roomId,
            RoomUpdateRequestDto dto);

    MessageResponseDto activateRoom(
            Long roomId);

    MessageResponseDto deactivateRoom(
            Long roomId);
    RoomResponseDto getRoomById(Long roomId);

    List<RoomResponseDto> getAllRoomsByHotelId(Long hotelId);
}
