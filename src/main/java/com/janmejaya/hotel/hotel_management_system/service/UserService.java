package com.janmejaya.hotel.hotel_management_system.service;

import com.janmejaya.hotel.hotel_management_system.dto.LoginRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.LoginResponseDto;
import com.janmejaya.hotel.hotel_management_system.dto.RegisterRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.RegisterResponseDto;

public interface UserService {

    RegisterResponseDto registerUser(RegisterRequestDto registerRequestDto);

    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);
}