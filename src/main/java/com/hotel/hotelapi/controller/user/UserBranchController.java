package com.hotel.hotelapi.controller.user;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.BranchServiceImpl;
import com.hotel.hotelapi.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/branch")
public class UserBranchController {
    @Autowired
    IBranchService branchService = new BranchServiceImpl();

    @GetMapping("/{id}")
    public Response getActiveBranchById(@PathVariable int id){
        BranchModel branchModel = branchService.findByIdActive(id);
        if(branchModel != null){
            return new Response(true, "Branch is found successfully!", branchModel);
        }
        return new Response(false, "Branch is not found!", null);
    }

    @GetMapping("/all")
    public Response getAllActiveBranch(
            @RequestParam(value = "searchName", defaultValue = "", required = false) String searchName,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        Page<BranchModel> branchModels = branchService.findAllActiveAndSearch(searchName, pageNo, pageSize, sortBy, sortDir);
        return !branchModels.isEmpty() ? new Response(true, "Services found successfully!", branchModels.getContent())
                : new Response(false, "Services not found!", null);
//        List<BranchModel> branchModel = branchService.findAllActive();
//        return new Response(true, "Branch is found successfully!", branchModel);
    }
}
