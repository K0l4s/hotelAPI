package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.BranchEntity;
import com.hotel.hotelapi.entity.RoomEntity;
import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.RoomModel;
import com.hotel.hotelapi.repository.BranchRepository;
import com.hotel.hotelapi.repository.RoomRepository;
import com.hotel.hotelapi.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public RoomModel findById(int id) {
        RoomEntity roomEntity = roomRepository.findById(id).orElse(null);
        return roomEntity != null ? modelMapper.map(roomEntity, RoomModel.class) : null;
    }

    @Override
    public List<RoomModel> findAll() {
        List<RoomEntity> roomEntities = roomRepository.findAll();
        return roomEntities.stream()
                .map(roomEntity -> modelMapper.map(roomEntity, RoomModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomModel create(RoomModel roomModel) {
        RoomEntity roomEntity = modelMapper.map(roomModel, RoomEntity.class);
        RoomEntity savedRoomEntity = roomRepository.save(roomEntity);
        return modelMapper.map(savedRoomEntity, RoomModel.class);
    }

    @Override
    public RoomModel update(int id, RoomModel RoomDTO) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(id);
        if (roomEntityOptional.isPresent()) {
            RoomEntity roomEntity = roomEntityOptional.get();

            //Update branchEntity with branchModel's fields
            roomEntity.setAceage(RoomDTO.getAceage());
            roomEntity.setLine(RoomDTO.getLine());
            roomEntity.setBranch(RoomDTO.getBranch());

// BỎ         BranchEntity branchEntity = roomEntity.getBranch();
//            branchEntity.setId(RoomDTO.getBranch().getId());
//
//            roomEntity.setBranch(branchEntity);


//            int branchid = RoomDTO.getBranch().getId();
//            BranchEntity branchEntity = branchRepository.findById(branchid)
//                    .orElseThrow(() -> new EntityNotFoundException("Branch with ID " + branchid + "is not found"));
//
//            //Map BranchEntity to BranchModel and set it in the RoomModel
//            BranchModel branchModel = modelMapper.map(branchEntity, BranchModel.class);
//            RoomDTO.setBranch(branchModel);

//            //Save RoomEntity and map to RoomModel
                RoomEntity updatedRoomEntity = roomRepository.save(roomEntity);
                return modelMapper.map(updatedRoomEntity, RoomModel.class);

        } else {
            throw new EntityNotFoundException("Room with ID" + id + "not found");
        }
    }

    @Override
    public void delete(int id) {
        roomRepository.deleteById(id);
    }
}
