package com.hotel.hotelapi.controller.user;

import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.IServiceService;
import com.hotel.hotelapi.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class UserServiceController {
    @Autowired
    IServiceService service = new ServiceServiceImpl();
    @GetMapping("/{id}")
    public Response getActiveServiceById(@PathVariable int id) {
        ServiceModel serviceModel = service.findByIdActive(id);
        if (serviceModel != null) {
            return new Response(true, "Service found successfully!", serviceModel);
        }
        return new Response(false, "Service not found!", null);
    }

    @GetMapping("/all")
    public Response getAllActiveServices() {
        List<ServiceModel> serviceModels = service.findAllActive();
        return new Response(true, "Services found successfully!", serviceModels);
    }
}
