package com.hotel.hotelapi.repository;

import com.hotel.hotelapi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
//    @Query("SELECT c.id AS id, c.fullName AS fullName, c.dob AS dob, a.email AS accountEmail FROM CustomerEntity c JOIN c.account a WHERE c.account.id = a.id")
//    List<Object[]> findAllWithEmail_PhoneNumberById(@Param("id") Integer id);
}
