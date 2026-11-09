package com.janmejaya.hotel.hotel_management_system.controler;

import com.janmejaya.hotel.hotel_management_system.dto.*;
import com.janmejaya.hotel.hotel_management_system.service.RoomService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(
            RoomService roomService) {

        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomCreateResponseDto>
    addRoom(
            @Valid
            @RequestBody
            RoomRequestDto dto) {

        return ResponseEntity.ok(
                roomService.addRoom(dto));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<MessageResponseDto>
    updateRoom(
            @PathVariable
            Long roomId,

            @Valid
            @RequestBody
            RoomUpdateRequestDto dto) {

        return ResponseEntity.ok(
                roomService.updateRoom(
                        roomId,
                        dto));
    }

    @PatchMapping("/{roomId}/activate")
    public ResponseEntity<MessageResponseDto>
    activateRoom(
            @PathVariable
            Long roomId) {

        return ResponseEntity.ok(
                roomService.activateRoom(
                        roomId));
    }

    @PatchMapping("/{roomId}/deactivate")
    public ResponseEntity<MessageResponseDto>
    deactivateRoom(
            @PathVariable
            Long roomId) {

        return ResponseEntity.ok(
                roomService.deactivateRoom(
                        roomId));
    }
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoomById(
            @PathVariable Long roomId) {

        return ResponseEntity.ok(
                roomService.getRoomById(
                        roomId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponseDto>>
    getAllRoomsByHotelId(
            @PathVariable Long hotelId) {

        return ResponseEntity.ok(
                roomService
                        .getAllRoomsByHotelId(
                                hotelId));
    }
}
