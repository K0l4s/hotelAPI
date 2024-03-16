package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.ServiceModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBranchService {
    BranchModel findByIdActive(int id);
    BranchModel findByIdInactive(int id);
    List<BranchModel> findAllActive();
    List<BranchModel> findAllInactive();
    //BranchModel findByLocation(String location);
    BranchModel findById(int id);
    List<BranchModel> findAll();
    BranchModel create (BranchModel branchModel);
    BranchModel update (int id, BranchModel branchDTO);
    boolean softDelete(int id);
    Page<BranchModel> findAllActiveAndSearch(String searchName, int pageNo, int pageSize, String sortBy, String sortDir);
}
