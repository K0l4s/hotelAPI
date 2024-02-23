package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.RoomModel;

import java.util.List;

public interface IRoomService {
    RoomModel findById(int id);
    List<RoomModel> findAll();
    RoomModel create (RoomModel roomModel);
    RoomModel update(int id, RoomModel RoomDTO);

    void delete(int id);

}
