package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.ServiceModel;

import java.util.List;

public interface IBranchService {
    BranchModel findById(int id);

    BranchModel findByLocation(String location);

    List<BranchModel> findAll();

    BranchModel create (BranchModel branchModel);

    BranchModel update (int id, BranchModel branchDTO);

    void delete(int id);
}
