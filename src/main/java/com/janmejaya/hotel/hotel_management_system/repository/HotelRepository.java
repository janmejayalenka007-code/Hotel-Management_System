package com.janmejaya.hotel.hotel_management_system.repository;

import com.janmejaya.hotel.hotel_management_system.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    boolean existsByHotelName(String hotelName);

    boolean existsByContactNumber(String contactNumber);

    boolean existsByHotelNameAndIdNot(
            String hotelName,
            Long id);

    boolean existsByContactNumberAndIdNot(
            String contactNumber,
            Long id);
    List<Hotel> findByCity(String city);
}