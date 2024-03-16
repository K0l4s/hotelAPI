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
    public RoomModel findByIdActive(int id) {
        RoomEntity roomEntity = roomRepository.findByIdAndIsDeletedFalse(id).orElse(null);
        return roomEntity != null ? modelMapper.map(roomEntity, RoomModel.class) : null;
    }

    @Override
    public List<RoomModel> findAllActive() {
        List<RoomEntity> roomEntities = roomRepository.findAllByIsDeletedFalse();
        return roomEntities.stream()
                .map(roomEntity -> modelMapper.map(roomEntity, RoomModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> findAllByBranchID(int branchId) {
        List<RoomEntity> roomEntities = roomRepository.findAllByBranchIdAndIsDeletedFalse(branchId);
        return roomEntities.stream()
                .map(roomEntity -> modelMapper.map(roomEntity, RoomModel.class))
                .collect(Collectors.toList());
    }

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
                RoomEntity updatedRoomEntity = roomRepository.save(roomEntity);
                return modelMapper.map(updatedRoomEntity, RoomModel.class);

        } else {
            throw new EntityNotFoundException("Room with ID" + id + "not found");
        }
    }

    @Override
    public boolean softDelete(int id) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(id);
        if(roomEntityOptional.isPresent()){
            RoomEntity roomEntity = roomEntityOptional.get();
            roomEntity.setDeleted(true);
            roomRepository.save(roomEntity);
            return true;
        }
        return false;
    }
}
