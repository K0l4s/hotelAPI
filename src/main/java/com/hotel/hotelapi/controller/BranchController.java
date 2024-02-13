package com.hotel.hotelapi.controller;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.service.BranchServiceImpl;
import com.hotel.hotelapi.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
    @Autowired
    IBranchService branch = new BranchServiceImpl();

    @GetMapping ("/{id}")
    public Response getBranchById(@PathVariable int id){
        Optional<BranchModel> optionalBranchModel = Optional.ofNullable(branch.findById(id));

        return optionalBranchModel
                .map(branchModel -> new Response(true, "Branch found successfully!", branchModel))
                .orElse(new Response(false, "Branch not found!", null));
    }

    @GetMapping("/all")
    public Response getAllBranches(){
        List<BranchModel> branchModelList = branch.findAll();
        return new Response(true, "Branches are found successfully!", branchModelList);
    }

    @PostMapping("/create")
    public Response createBranch(@RequestBody BranchModel branchModel){
        BranchModel createBranch = branch.create(branchModel);
        return new Response(true, "Branch is created successfully!", createBranch);
    }

    @PutMapping("/{id}")
    public Response updateBranch(@PathVariable int id, @RequestBody BranchModel branchModel){
        BranchModel updateBranch = branch.update(id, branchModel);
        if(updateBranch!=null){
            return new Response(true, "Branch is updated successfully!", updateBranch);
        }
        return new Response(false, "Branch is not found", null);
    }

    @DeleteMapping("/{id}")
    public Response deletedBranch(@PathVariable int id){
        branch.delete(id);
        return new Response(true, "Branch is deleted sucessfully!", null);
    }

}
