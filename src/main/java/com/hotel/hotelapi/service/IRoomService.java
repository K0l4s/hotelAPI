package com.hotel.hotelapi.service;

import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.RoomModel;

import java.util.List;

public interface IRoomService {


    RoomModel findByIdActive(int id);
    List<RoomModel> findAllActive();
    List<RoomModel> findAllByBranchID(int id);
    RoomModel findById(int id);
    List<RoomModel> findAll();
    RoomModel create (RoomModel roomModel);
    RoomModel update(int id, RoomModel RoomDTO);

    boolean softDelete(int id);

}
