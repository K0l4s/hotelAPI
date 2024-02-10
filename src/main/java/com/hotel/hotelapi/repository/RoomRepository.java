package com.hotel.hotelapi.repository;

import com.hotel.hotelapi.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RoomRepository extends JpaRepository<RoomEntity,Integer> {
}
