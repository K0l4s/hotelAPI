package com.hotel.hotelapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "customerId")
//    private CustomerEntity customer;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(length = 12)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Column(length=6,nullable = false)
    private String code;
    private String loginCode;
    private boolean isActive = false;
}
