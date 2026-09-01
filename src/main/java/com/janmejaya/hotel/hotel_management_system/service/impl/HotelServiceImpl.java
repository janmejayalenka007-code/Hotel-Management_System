package com.janmejaya.hotel.hotel_management_system.service.impl;

import com.janmejaya.hotel.hotel_management_system.dto.*;
import com.janmejaya.hotel.hotel_management_system.entity.Hotel;
import com.janmejaya.hotel.hotel_management_system.repository.HotelRepository;
import com.janmejaya.hotel.hotel_management_system.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelServiceImpl(
            HotelRepository hotelRepository,
            ModelMapper modelMapper) {

        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageResponseDto addHotel(
            HotelRequestDto dto) {

        if (hotelRepository.existsByHotelName(
                dto.getHotelName())) {

            throw new RuntimeException(
                    "Hotel name already exists");
        }

        if (hotelRepository.existsByContactNumber(
                dto.getContactNumber())) {

            throw new RuntimeException(
                    "Contact number already exists");
        }

        Hotel hotel =
                modelMapper.map(dto, Hotel.class);

        hotelRepository.save(hotel);

        return new MessageResponseDto(
                "Hotel added successfully");
    }

    @Override
    public MessageResponseDto updateHotel(
            Long id,
            HotelRequestDto dto) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Hotel not found"));

        if (hotelRepository.existsByHotelNameAndIdNot(
                dto.getHotelName(),
                id)) {

            throw new RuntimeException(
                    "Hotel name already exists");
        }

        if (hotelRepository.existsByContactNumberAndIdNot(
                dto.getContactNumber(),
                id)) {

            throw new RuntimeException(
                    "Contact number already exists");
        }

        hotel.setHotelName(dto.getHotelName());
        hotel.setCity(dto.getCity());
        hotel.setState(dto.getState());
        hotel.setAddress(dto.getAddress());
        hotel.setDescription(dto.getDescription());
        hotel.setContactNumber(dto.getContactNumber());

        hotelRepository.save(hotel);

        return new MessageResponseDto(
                "Hotel updated successfully");
    }

    @Override
    public MessageResponseDto deleteHotel(
            Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Hotel not found"));

        hotelRepository.delete(hotel);

        return new MessageResponseDto(
                "Hotel deleted successfully");
    }

    @Override
    public HotelResponseDto getHotelById(
            Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Hotel not found"));

        return modelMapper.map(
                hotel,
                HotelResponseDto.class);
    }

    @Override
    public List<HotelResponseDto> getAllHotels() {

        return hotelRepository.findAll()
                .stream()
                .map(hotel ->
                        modelMapper.map(
                                hotel,
                                HotelResponseDto.class))
                .toList();
    }

    @Override
    public List<HotelResponseDto> getHotelsByCity(
            String city) {

        List<Hotel> hotels =
                hotelRepository.findByCity(city);

        if (hotels.isEmpty()) {

            throw new RuntimeException(
                    "No hotels found in " + city);
        }

        return hotels.stream()
                .map(hotel -> modelMapper.map(
                        hotel,
                        HotelResponseDto.class))
                .toList();
    }
}