package com.janmejaya.hotel.hotel_management_system.controller;

import com.janmejaya.hotel.hotel_management_system.dto.LoginRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.LoginResponseDto;
import com.janmejaya.hotel.hotel_management_system.dto.RegisterRequestDto;
import com.janmejaya.hotel.hotel_management_system.dto.RegisterResponseDto;
import com.janmejaya.hotel.hotel_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController

@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "https://spectacular-beijinho-17818b.netlify.app"
})
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(
            @Valid @RequestBody RegisterRequestDto requestDto) {

        RegisterResponseDto response =
                userService.registerUser(requestDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(
            @Valid @RequestBody LoginRequestDto loginRequestDto) {

        return ResponseEntity.ok(
                userService.loginUser(loginRequestDto)
        );
    }
}