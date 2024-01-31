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
@Table(name="orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number; //Số lượng dịch vụ order;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name="serviceId")
    private ServiceEntity service;
}
