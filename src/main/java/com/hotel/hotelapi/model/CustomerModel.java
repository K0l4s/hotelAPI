package com.hotel.hotelapi.model;

import com.hotel.hotelapi.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
//Phần Auth Kiên đang làm, t chỉ tạo cái Model để tham khảo thử thui, nếu thấy có gì cần chỉnh sửa thì chỉnh giúp t

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private int id;
    private String fullName;
    private LocalDate dob;
    private AccountEntity account;
}
