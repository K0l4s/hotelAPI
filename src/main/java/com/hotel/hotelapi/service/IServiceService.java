package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.ServiceModel;
import java.util.List;

public interface IServiceService {
    ServiceModel findById(int id);
    List<ServiceModel> findAll();
    ServiceModel create(ServiceModel serviceModel);
    ServiceModel update(int id, ServiceModel serviceDTO);
    void delete(int id);
}
