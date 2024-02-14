package com.hotel.hotelapi.controller;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.Response;
import com.hotel.hotelapi.model.RoomModel;
import com.hotel.hotelapi.service.IRoomService;
import com.hotel.hotelapi.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/room")
@RestController
public class RoomController {
    @Autowired
    IRoomService roomService = new RoomServiceImpl();

    @GetMapping ("/{id}")
    public Response getRoomById(@PathVariable int id){
        Optional<RoomModel> optionalRoomModel = Optional.ofNullable(roomService.findById(id));

        return optionalRoomModel
                .map(roomModel -> new Response(true, "Room is found successfully!", roomModel))
                .orElse(new Response(false, "Room is not found", null));
    }

    @GetMapping("/all")
    public Response getAllRoom(){
        List<RoomModel> roomModelList = roomService.findAll();
        return new Response(true, "Rooms are found successfully!", roomModelList);
    }

    @PostMapping ("/create")
    public Response createRoom(@RequestBody RoomModel roomModel){
        RoomModel createRoom = roomService.create(roomModel);
        return new Response(true, "Room is created successfully!", createRoom);
    }

    @PutMapping("/{id}")
    public Response updateRoom (@PathVariable int id, @RequestBody RoomModel roomModel){
        RoomModel updateRoom = roomService.update(id, roomModel);
        if(updateRoom!=null){
            return new Response(true, "Room is updated successfully!", updateRoom);
        }
        return new Response(false, "Room is not found!", null);
    }

    @DeleteMapping ("/{id}")
    public Response deleteRoom(@PathVariable int id){
        roomService.delete(id);
        return new Response(true, "Room is deleted sucessfully!", null);
    }


}
