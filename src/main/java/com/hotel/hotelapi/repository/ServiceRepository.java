package com.hotel.hotelapi.repository;

import com.hotel.hotelapi.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    List<ServiceEntity> findAllByIsDeletedFalse();
    Optional<ServiceEntity> findByIdAndIsDeletedFalse(int id);

}
