package com.hotel.hotelapi.repository;

import com.hotel.hotelapi.entity.BranchEntity;
import com.hotel.hotelapi.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  RoomRepository extends JpaRepository<RoomEntity,Integer> {
    //thêm điều kiện: chỉ hiển thị ra các room nếu như branch không bị softDeleted
    List<RoomEntity> findAllByIsDeletedFalse();
    Optional<RoomEntity> findByIdAndIsDeletedFalse(Integer integer);

    @Query("SELECT r FROM RoomEntity r WHERE r.isDeleted = FALSE AND r.branch.id = :branchId AND r.branch.isDeleted = FALSE")
    List<RoomEntity> findAllByBranchIdAndIsDeletedFalse(@Param("branchId") Integer branchId);
}
