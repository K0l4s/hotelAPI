package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.ServiceModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IServiceService {
    ServiceModel findByIdActive(int id);
    ServiceModel findById(int id);
    List<ServiceModel> findAll();
    List<ServiceModel> findAllActive();
    ServiceModel create(ServiceModel serviceModel);
    ServiceModel update(int id, ServiceModel serviceDTO);
    boolean softDelete(int id);
    Page<ServiceModel> findAllActiveAndSearch(String searchName, int pageNo, int pageSize, String sortBy, String sortDir);
}
