package com.hotel.hotelapi.service;
import com.hotel.hotelapi.entity.CustomerEntity;
import com.hotel.hotelapi.model.CustomerModel;
import com.hotel.hotelapi.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public CustomerModel findById(int id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        return customerEntity != null ? modelMapper.map(customerEntity, CustomerModel.class) : null;
    }
    @Override
    public List<CustomerModel> findAll() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream()
                .map(customerEntity -> modelMapper.map(customerEntity, CustomerModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerModel create(CustomerModel customerModel) {
        CustomerEntity customerEntity = modelMapper.map(customerModel, CustomerEntity.class);
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        return modelMapper.map(savedCustomerEntity, CustomerModel.class);
    }

    @Override
    public CustomerModel update(int id, CustomerModel CustomerDTO) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isPresent()) {
            CustomerEntity customerEntity = customerEntityOptional.get();

            customerEntity.setDob(CustomerDTO.getDob());
            customerEntity.setFullName(CustomerDTO.getFullName());
            customerEntity.setAccount(CustomerDTO.getAccount());

            CustomerEntity updatedCustomerEntity = customerRepository.save(customerEntity);
            return modelMapper.map(updatedCustomerEntity, CustomerModel.class);

        } else {
            throw new EntityNotFoundException("Customer with ID" + id + "not found");
        }
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }
}
