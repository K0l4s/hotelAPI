package com.hotel.hotelapi.entity;

import com.hotel.hotelapi.model.BranchModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="room")
public class RoomEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private BranchEntity branch;

    private float aceage; //Thông số về diện tích
    private int floor; //Lầu Note: (0: Tầng G)
    private int number; //Số phòng
    private String line; //Dãy (A hoặc B)
    private int size; //Số giường
    private boolean isDeleted;


}
