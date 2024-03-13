package com.hotel.hotelapi.controller.user;

import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.IServiceService;
import com.hotel.hotelapi.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/service")
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
    public Response getAllActiveServices(
            @RequestParam(value = "searchName", defaultValue = "", required = false) String searchName,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        Page<ServiceModel> serviceModels = service.findAllActiveAndSearch(searchName, pageNo, pageSize, sortBy, sortDir);
        return !serviceModels.isEmpty() ? new Response(true, "Services found successfully!", serviceModels.getContent())
                : new Response(false, "Services not found!", null);
    }
}
