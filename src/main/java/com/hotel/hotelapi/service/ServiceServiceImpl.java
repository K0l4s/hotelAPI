package com.hotel.hotelapi.service;

import com.hotel.hotelapi.entity.ServiceEntity;
import com.hotel.hotelapi.model.ServiceModel;
import com.hotel.hotelapi.repository.ServiceRepository;
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
public class ServiceServiceImpl implements IServiceService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ServiceModel findByIdActive(int id) {
        Optional<ServiceEntity> serviceEntityOptional = serviceRepository.findByIdAndIsDeletedFalse(id);
        if (serviceEntityOptional.isPresent()) {
            ServiceEntity serviceEntity = serviceEntityOptional.get();
            return modelMapper.map(serviceEntity, ServiceModel.class);
        } else {
            return null;
        }
    }

    @Override
    public ServiceModel findById(int id) {
        ServiceEntity serviceEntity = serviceRepository.findById(id).orElse(null);
        return serviceEntity != null ? modelMapper.map(serviceEntity, ServiceModel.class) : null;
    }

    @Override
    public List<ServiceModel> findAll() {
        List<ServiceEntity> serviceEntities = serviceRepository.findAll();
        return serviceEntities.stream()
                .map(serviceEntity -> modelMapper.map(serviceEntity, ServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ServiceModel> findAllActiveAndSearch(String searchName, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page <ServiceEntity> activeServices = null;

        if (!searchName.isEmpty()) {
            activeServices = serviceRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(searchName, pageable);
        }
        else {
            activeServices = serviceRepository.findAllByIsDeletedFalse(pageable);
        }
        return activeServices.map(serviceEntity -> modelMapper.map(serviceEntity, ServiceModel.class));
    }

    @Override
    public List<ServiceModel> findAllActive() {
        List<ServiceEntity> serviceEntities = serviceRepository.findAllByIsDeletedFalse();
        return serviceEntities.stream()
                .map(serviceEntity -> modelMapper.map(serviceEntity, ServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceModel create(ServiceModel serviceModel) {
        ServiceEntity serviceEntity = modelMapper.map(serviceModel, ServiceEntity.class);
        ServiceEntity savedServiceEntity = serviceRepository.save(serviceEntity);
        return modelMapper.map(savedServiceEntity, ServiceModel.class);
    }

    @Override
    public ServiceModel update(int id, ServiceModel serviceModel) {
        Optional<ServiceEntity> serviceEntityOptional = serviceRepository.findByIdAndIsDeletedFalse(id);
        if (serviceEntityOptional.isPresent()) {
            ServiceEntity serviceEntity = serviceEntityOptional.get();

            // Update serviceEntity with serviceModel fields
            serviceEntity.setName(serviceModel.getName());
            serviceEntity.setPrice(serviceModel.getPrice());
            serviceEntity.setSalePercent(serviceModel.getSalePercent());
            serviceEntity.setDescription(serviceModel.getDescription());
            serviceEntity.setImage(serviceModel.getImage());

            // Save serviceEntity
            ServiceEntity updatedServiceEntity = serviceRepository.save(serviceEntity);

            return modelMapper.map(updatedServiceEntity, ServiceModel.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean softDelete(int id) {
        Optional<ServiceEntity> serviceEntityOptional = serviceRepository.findByIdAndIsDeletedFalse(id);
        if (serviceEntityOptional.isPresent()) {
            ServiceEntity serviceEntity = serviceEntityOptional.get();
            serviceEntity.setDeleted(true);
            serviceRepository.save(serviceEntity);
            return true;
        }
        return false;
    }
}
