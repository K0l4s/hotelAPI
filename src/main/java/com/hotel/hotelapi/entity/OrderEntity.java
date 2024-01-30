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
@Table(name="order")
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number; //Số lượng dịch vụ order;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name="serviceid")
    private ServiceEntity service;
}
