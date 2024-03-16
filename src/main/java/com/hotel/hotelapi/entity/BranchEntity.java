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
@Table(name="branch")
public class BranchEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;
//    private double acreage; //Đây là thông số về diện tích
    private String image;
    private boolean isDeleted;// If branch A stops operating (isDeleted = true)
    //then the system isn't going to display branch A.
    //Simultaneously all rooms belong to branchA are also not displayed on UI
}
