package com.hotel.hotelapi.controller;

import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.IServiceService;
import com.hotel.hotelapi.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    IServiceService service = new ServiceServiceImpl();
    @GetMapping("/{id}")
    public Response getServiceById(@PathVariable int id) {
        ServiceModel serviceModel = service.findById(id);
        if (serviceModel != null) {
            return new Response(true, "Service found successfully!", serviceModel);
        }
        return new Response(false, "Service not found!", null);
    }

    @GetMapping("/all")
    public Response getAllServices() {
        List<ServiceModel> serviceModels = service.findAll();
        return new Response(true, "Services found successfully!", serviceModels);
    }

    @PostMapping("/create")
    public Response createService(@RequestBody ServiceModel serviceModel) {
        ServiceModel createdService = service.create(serviceModel);
        return new Response(true, "Service created successfully", createdService);
    }

    @PutMapping("/{id}")
    public Response updateService(@PathVariable int id, @RequestBody ServiceModel serviceModel) {
        ServiceModel updatedService = service.update(id, serviceModel);
        if (updatedService != null) {
            return new Response(true, "Service updated successfully", updatedService);
        }
        return new Response(false, "Service not found", null);
    }

    @DeleteMapping("/{id}")
    public Response deleteService(@PathVariable int id) {
        service.delete(id);
        return new Response(true, "Service deleted successfully", null);
    }
}
