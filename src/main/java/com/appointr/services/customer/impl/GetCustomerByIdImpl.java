package com.appointr.services.customer.impl;

import com.appointr.dto.customer.CustomerDTO;
import com.appointr.dto.customer.CustomerDTOConverter;
import com.appointr.repository.CustomerRepository;
import com.appointr.services.customer.GetCustomerById;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCustomerByIdImpl implements GetCustomerById {
    private CustomerRepository customerRepository;

    public GetCustomerByIdImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerDTO> getCustomer(long customerId) {
        return customerRepository.findById(customerId).map(CustomerDTOConverter::convertToDTO);
    }
}
