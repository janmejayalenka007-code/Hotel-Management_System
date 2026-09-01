package com.janmejaya.hotel.hotel_management_system.service.impl;

import com.janmejaya.hotel.hotel_management_system.dto.LoginRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.LoginResponseDto;
import com.janmejaya.hotel.hotel_management_system.dto.RegisterRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.RegisterResponseDto;
import com.janmejaya.hotel.hotel_management_system.entity.User;
import com.janmejaya.hotel.hotel_management_system.repository.UserRepository;
import com.janmejaya.hotel.hotel_management_system.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto requestDto) {

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = modelMapper.map(requestDto, User.class);

        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new RegisterResponseDto(
                "Registration Successful"
        );
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email or Password"));

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("Invalid Email or Password");
        }

        return new LoginResponseDto("Login Successful");
    }
}
