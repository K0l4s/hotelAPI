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

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @Column(nullable = false)
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String code;
}
