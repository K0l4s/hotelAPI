package com.hotel.hotelapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="book")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="roomid")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name="customerid")
    private CustomerEntity customer;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;
    private String note;
}
