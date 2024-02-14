package com.hotel.hotelapi.controller;

import com.hotel.hotelapi.model.CustomerModel;
import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.RoomModel;
import com.hotel.hotelapi.service.CustomerServiceImpl;
import com.hotel.hotelapi.service.ICustomerService;
import com.hotel.hotelapi.service.IRoomService;
import com.hotel.hotelapi.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/{id}")
    public Response getCustomerById(@PathVariable int id){
        Optional<CustomerModel> optionalCustomerModel = Optional.ofNullable(customerService.findById(id));

        return optionalCustomerModel
                .map(customerModel -> new Response(true, "Customer is found successfully!", customerModel))
                .orElse(new Response(false, "Customer is not found", null));
    }

    @GetMapping("/all")
    public Response getAllCustomer(){
        List<CustomerModel> customerModelList = customerService.findAll();
        return new Response(true, "Customers are found successfully!", customerModelList);
    }

    @PostMapping("/create")
    public Response createCustomer(@RequestBody CustomerModel customerModel){
        CustomerModel createCutomer = customerService.create(customerModel);
        return new Response(true, "Customer is created successfully!", createCutomer);
    }

    @PutMapping("/{id}")
    public Response updateCustomer (@PathVariable int id, @RequestBody CustomerModel customerModel){
        CustomerModel updateCustomer = customerService.update(id, customerModel);
        if(updateCustomer!=null){
            return new Response(true, "Customer is updated successfully!", updateCustomer);
        }
        return new Response(false, "Customer is not found!", null);
    }

    @DeleteMapping ("/{id}")
    public Response deleteCustomer(@PathVariable int id){
        customerService.delete(id);
        return new Response(true, "Customer is deleted sucessfully!", null);
    }


}
