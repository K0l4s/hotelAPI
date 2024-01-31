package com.hotel.hotelapi.repository;

import com.hotel.hotelapi.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {
}
