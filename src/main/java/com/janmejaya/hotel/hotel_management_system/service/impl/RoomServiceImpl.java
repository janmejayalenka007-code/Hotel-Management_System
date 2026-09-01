package com.janmejaya.hotel.hotel_management_system.service.impl;

import com.janmejaya.hotel.hotel_management_system.dto.*;
import com.janmejaya.hotel.hotel_management_system.entity.Hotel;
import com.janmejaya.hotel.hotel_management_system.entity.Room;
import com.janmejaya.hotel.hotel_management_system.repository.HotelRepository;
import com.janmejaya.hotel.hotel_management_system.repository.RoomRepository;
import com.janmejaya.hotel.hotel_management_system.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public RoomServiceImpl(
            RoomRepository roomRepository,
            HotelRepository hotelRepository,
            ModelMapper modelMapper) {

        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoomCreateResponseDto addRoom(
            RoomRequestDto dto) {

        Hotel hotel = hotelRepository.findById(
                        dto.getHotelId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Hotel not found"));

        if (roomRepository
                .existsByRoomNumberAndHotelId(
                        dto.getRoomNumber(),
                        dto.getHotelId())) {

            throw new RuntimeException(
                    "Room number already exists in this hotel");
        }

        Room room = new Room();

        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setCapacity(dto.getCapacity());
        room.setDescription(dto.getDescription());
        room.setHotel(hotel);

        Room savedRoom =
                roomRepository.save(room);

        return new RoomCreateResponseDto(
                "Room added successfully",
                savedRoom.getId(),
                hotel.getId()
        );
    }

    @Override
    public MessageResponseDto updateRoom(
            Long roomId,
            RoomUpdateRequestDto dto) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found"));

        if (roomRepository
                .existsByRoomNumberAndHotelIdAndIdNot(
                        dto.getRoomNumber(),
                        room.getHotel().getId(),
                        roomId)) {

            throw new RuntimeException(
                    "Room number already exists in this hotel");
        }

        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setCapacity(dto.getCapacity());
        room.setDescription(dto.getDescription());

        roomRepository.save(room);

        return new MessageResponseDto(
                "Room updated successfully");
    }

    @Override
    public MessageResponseDto activateRoom(
            Long roomId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found"));

        room.setIsActive(true);

        roomRepository.save(room);

        return new MessageResponseDto(
                "Room activated successfully");
    }

    @Override
    public MessageResponseDto deactivateRoom(
            Long roomId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found"));

        room.setIsActive(false);

        roomRepository.save(room);

        return new MessageResponseDto(
                "Room deactivated successfully");
    }

    @Override
    public RoomResponseDto getRoomById(
            Long roomId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found"));

        RoomResponseDto response =
                modelMapper.map(
                        room,
                        RoomResponseDto.class);

        response.setHotelId(
                room.getHotel().getId());

        return response;
    }

    @Override
    public List<RoomResponseDto> getAllRoomsByHotelId(
            Long hotelId) {

        List<Room> rooms =
                roomRepository.findByHotelId(
                        hotelId);

        return rooms.stream()
                .map(room -> {

                    RoomResponseDto dto =
                            modelMapper.map(
                                    room,
                                    RoomResponseDto.class);

                    dto.setHotelId(
                            room.getHotel().getId());

                    return dto;
                })
                .toList();
    }
}