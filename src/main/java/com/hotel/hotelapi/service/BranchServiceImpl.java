package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.BranchEntity;
import com.hotel.hotelapi.entity.RoomEntity;
import com.hotel.hotelapi.entity.ServiceEntity;
import com.hotel.hotelapi.model.BranchModel;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.repository.BranchRepository;
import com.hotel.hotelapi.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements IBranchService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public BranchModel findByIdActive(int id) {
        BranchEntity branchEntity = branchRepository.findByIdAndIsDeletedFalse(id).orElse(null);
        return branchEntity != null ? modelMapper.map(branchEntity, BranchModel.class) : null;
    }

    @Override
    public BranchModel findByIdInactive(int id) {
        BranchEntity branchEntity = branchRepository.findByIdAndIsDeletedTrue(id).orElse(null);
        return branchEntity != null ? modelMapper.map(branchEntity, BranchModel.class) : null;
    }

    @Override
    public List<BranchModel> findAllActive() {
        List<BranchEntity> branchEntities = branchRepository.findAllByIsDeletedFalse();
        return branchEntities.stream()
                .map(branchEntity -> modelMapper.map(branchEntity, BranchModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BranchModel> findAllInactive() {
        List<BranchEntity> branchEntities = branchRepository.findAllByIsDeletedTrue();
        return branchEntities.stream()
                .map(branchEntity -> modelMapper.map(branchEntity, BranchModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BranchModel findById(int id) {
        BranchEntity branchEntity = branchRepository.findById(id).orElse(null);
        return branchEntity != null ? modelMapper.map(branchEntity, BranchModel.class) : null;
    }

    @Override
    public List<BranchModel> findAll() {
        List<BranchEntity> branchEntities = branchRepository.findAll();
        return branchEntities.stream()
                .map(branchEntity -> modelMapper.map(branchEntity, BranchModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public BranchModel create(BranchModel branchModel) {
        BranchEntity branchEntity = modelMapper.map(branchModel, BranchEntity.class);
        BranchEntity savedBranchEntity = branchRepository.save(branchEntity);
        return modelMapper.map(savedBranchEntity, BranchModel.class);
    }

    @Override
    public BranchModel update(int id, BranchModel branchDTO) {
        Optional<BranchEntity> branchEntityOptional = branchRepository.findById(id);
        if (branchEntityOptional.isPresent()){
            BranchEntity branchEntity = branchEntityOptional.get();

            //Update branchEntity with branchModel's fields
            branchEntity.setImage(branchDTO.getImage());
            branchEntity.setLocation(branchDTO.getLocation());
            //Save branchEntity
            BranchEntity updatedBranchEntity = branchRepository.save(branchEntity);

            return modelMapper.map(updatedBranchEntity, BranchModel.class);

        }else {
            return null;
        }
    }

    @Override
    public boolean softDelete(int id) {
        Optional<BranchEntity> branchEntityOptional = branchRepository.findByIdAndIsDeletedFalse(id);
        if(branchEntityOptional.isPresent()){
            BranchEntity branchEntity = branchEntityOptional.get();
            branchEntity.setDeleted(true);
            branchRepository.save(branchEntity);

            // Tìm tất cả các RoomEntity thuộc về BranchEntity này và set Deleted=true cho các Room mà Branch thuộc Room đó bị softDeleted
            List<RoomEntity> rooms = roomRepository.findAllByBranchIdAndIsDeletedFalse(id);
            for (RoomEntity room : rooms) {
                room.setDeleted(true);
            }
            // Lưu lại các thay đổi cho tất cả các RoomEntity
            roomRepository.saveAll(rooms);

            return true;
        }
        return false;
    }

    @Override
    public Page<BranchModel> findAllActiveAndSearch(String searchName, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<BranchEntity> activeBranches = null;

        if(!searchName.isEmpty()){
            activeBranches = branchRepository.findByLocationContainingIgnoreCaseAndIsDeletedFalse(searchName, pageable);
        }
        else{
            activeBranches = branchRepository.findAllByIsDeletedFalse(pageable);
        }
        return activeBranches.map(branchEntity -> modelMapper.map(branchEntity, BranchModel.class));
    }
}
