package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.CustomerModel;

import java.util.List;

public interface ICustomerService {
    CustomerModel findById (int id);
    List<CustomerModel> findAll();
    CustomerModel create(CustomerModel customerModel);
    CustomerModel update(int id, CustomerModel CustomerDTO);
    void delete(int id);

}
