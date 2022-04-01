package com.appointr.services.customer.impl;

import com.appointr.dto.customer.CustomerDTO;
import com.appointr.dto.customer.CustomerDTOConverter;
import com.appointr.repository.CustomerReposity;
import com.appointr.services.customer.GetCustomerById;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCustomerByIdImpl implements GetCustomerById {
    private CustomerReposity customerRepository;

    public GetCustomerByIdImpl(CustomerReposity customerReposity){
        this.customerRepository = customerReposity;
    }

    @Override
    public Optional<CustomerDTO> getCustomer(long customerId) {
        return customerRepository.findById(customerId).map(CustomerDTOConverter::convertToDTO);
    }
}
