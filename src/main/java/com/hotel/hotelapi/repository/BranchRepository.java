package com.hotel.hotelapi.repository;

import com.hotel.hotelapi.entity.BranchEntity;
import com.hotel.hotelapi.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {
    List<BranchEntity> findAllByIsDeletedFalse();
    Optional<BranchEntity> findByIdAndIsDeletedFalse(int id);
}
