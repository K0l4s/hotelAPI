package com.hotel.hotelapi.controller.admin;

import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.RoomModel;
import com.hotel.hotelapi.service.IRoomService;
import com.hotel.hotelapi.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/room")
public class AdminRoomController {
    @Autowired
    IRoomService roomService = new RoomServiceImpl();
    @GetMapping("/all")
    public Response getAllRooms(){
        List<RoomModel> roomModelList = roomService.findAll();
        return new Response(true, "Rooms are found successfully!", roomModelList);
    }
    @PostMapping("/create")
    public Response createRoom(@RequestBody RoomModel roomModel){
        RoomModel createRoom = roomService.create(roomModel);
        return new Response(true, "Room is created successfully", createRoom);
    }

    @PutMapping("/{id}")
    public Response updateRoom(@PathVariable int id, @RequestBody RoomModel roomModel){
        RoomModel updateRoom = roomService.update(id, roomModel);
        if(updateRoom != null){
            return new Response(true, "Room is updated successfully", updateRoom);
        }
        return new Response(false, "Room is not found", null);
    }
    @DeleteMapping("/{id}")
    public Response deleteRoom(@PathVariable int id){
        boolean isDeleted = roomService.softDelete(id);
        if(isDeleted){
            return new Response(true, "Room is deleted successfully!", null);
        }
        return new Response(false, "Room is not found", null);
    }

}
