package com.hotel.hotelapi.controller.user;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.BranchServiceImpl;
import com.hotel.hotelapi.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Response getAllActiveBranch(){
        List<BranchModel> branchModel = branchService.findAllActive();
        return new Response(true, "Branch is found successfully!", branchModel);
    }
}
