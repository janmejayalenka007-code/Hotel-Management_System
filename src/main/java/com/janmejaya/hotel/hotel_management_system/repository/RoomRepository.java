package com.janmejaya.hotel.hotel_management_system.repository;

import com.janmejaya.hotel.hotel_management_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository
        extends JpaRepository<Room, Long> {

    boolean existsByRoomNumberAndHotelId(
            String roomNumber,
            Long hotelId
    );

    boolean existsByRoomNumberAndHotelIdAndIdNot(
            String roomNumber,
            Long hotelId,
            Long id
    );


    List<Room> findByHotelId(Long hotelId);
}
