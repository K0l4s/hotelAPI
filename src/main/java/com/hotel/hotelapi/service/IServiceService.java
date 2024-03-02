package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.ServiceModel;
import java.util.List;

public interface IServiceService {
    ServiceModel findByIdActive(int id);
    List<ServiceModel> findAll();
    List<ServiceModel> findAllActive();
    ServiceModel create(ServiceModel serviceModel);
    ServiceModel update(int id, ServiceModel serviceDTO);
    boolean softDelete(int id);
}
