package com.hotel.hotelapi.controller.admin;

import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.IServiceService;
import com.hotel.hotelapi.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/service")
public class AdminServiceController {
    @Autowired
    IServiceService service = new ServiceServiceImpl();

    @GetMapping("/all")
    public Response getAllServices(
            @RequestParam(value = "showDisabled", required = false, defaultValue = "false") boolean showDisabled
    ) {
        List<ServiceModel> serviceModels;
        if (showDisabled) {
            serviceModels = service.findAll();
        }
        else {
            serviceModels = service.findAllActive();
        }
        return new Response(true, "Services found successfully!", serviceModels);
    }

    @GetMapping("/{id}")
    public Response getServiceById(
            @PathVariable int id,
            @RequestParam(value = "showDisabled", required = false, defaultValue = "false") boolean showDisabled) {
        ServiceModel serviceModel;
        if (!showDisabled) {
            serviceModel = service.findByIdActive(id);
        }
        else {
            serviceModel = service.findById(id);
        }

        if (serviceModel != null) {
            return new Response(true, "Service found successfully!", serviceModel);
        }
        return new Response(false, "Service not found!", null);
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
        boolean isDeleted = service.softDelete(id);
        if (isDeleted) {
            return new Response(true, "Service deleted successfully", null);
        }
        return new Response(false, "Service not found", null);
    }
}
