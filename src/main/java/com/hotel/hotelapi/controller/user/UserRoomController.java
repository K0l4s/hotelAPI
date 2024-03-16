package com.hotel.hotelapi.controller.user;

import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.RoomModel;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.service.IRoomService;
import com.hotel.hotelapi.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/room")
public class UserRoomController {
    @Autowired
    IRoomService roomService = new RoomServiceImpl();

    @GetMapping("/{id}")
    public Response getActiveRoomById(@PathVariable int id){
        RoomModel roomModel = roomService.findByIdActive(id);
        if (roomModel != null) {
            return new Response(true, "Room is found successfully!", roomModel);
        }
        return new Response(false, "Room is not found!", null);
    }

    @GetMapping("/all")
    public Response getAllActiveRooms() {
        List<RoomModel> roomModelList = roomService.findAllActive();
        return new Response(true, "Rooms are found successfully!", roomModelList);
    }

    @GetMapping("/find/{branch}")
    public Response getAllActiveRoomsByBranchId(@PathVariable int branch) {
        List<RoomModel> roomModelList = roomService.findAllByBranchID(branch);
        if(!roomModelList.isEmpty()){
            return new Response(true, "Rooms are found successfully!", roomModelList);
        }
        return new Response(false, "Room is not found!", null);
    }
}
