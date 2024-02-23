package com.hotel.hotelapi.model;

import com.hotel.hotelapi.entity.BranchEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel {
    private int id;
    private BranchEntity branch;
    private float aceage; //Thông số về diện tích
    private int floor; //Lầu Note: (0: Tầng G)
    private int number; //Số phòng
    private String line; //Dãy (A hoặc B)
    private int size; //Số giường
}
