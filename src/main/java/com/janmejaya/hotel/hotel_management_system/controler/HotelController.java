package com.janmejaya.hotel.hotel_management_system.controler;

import com.janmejaya.hotel.hotel_management_system.dto.*;
import com.janmejaya.hotel.hotel_management_system.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "https://spectacular-beijinho-17818b.netlify.app"
})
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(
            HotelService hotelService) {

        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto>
    addHotel(
            @Valid @RequestBody
            HotelRequestDto dto) {

        return new ResponseEntity<>(
                hotelService.addHotel(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDto>
    updateHotel(
            @PathVariable Long id,
            @Valid @RequestBody
            HotelRequestDto dto) {

        return ResponseEntity.ok(
                hotelService.updateHotel(
                        id,
                        dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto>
    deleteHotel(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                hotelService.deleteHotel(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDto>
    getHotelById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                hotelService.getHotelById(id));
    }

    @GetMapping
    public ResponseEntity<List<HotelResponseDto>>
    getAllHotels() {

        return ResponseEntity.ok(
                hotelService.getAllHotels());
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<List<HotelResponseDto>>
    getHotelsByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                hotelService.getHotelsByCity(city));
    }

}