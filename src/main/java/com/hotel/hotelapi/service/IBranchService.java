package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.ServiceModel;

import java.util.List;

public interface IBranchService {
    BranchModel findByIdActive(int id);
    List<BranchModel> findAllActive();
    //BranchModel findByLocation(String location);
    BranchModel findById(int id);
    List<BranchModel> findAll();
    BranchModel create (BranchModel branchModel);
    BranchModel update (int id, BranchModel branchDTO);
    boolean softDelete(int id);
}
