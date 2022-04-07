package com.appointr.services.customer.impl;

import com.appointr.dto.customer.CustomerDTOConverter;
import com.appointr.dto.customer.GetAllCustomersResponseDTO;
import com.appointr.repository.entity.Customer;
import com.appointr.repository.CustomerRepository;
import com.appointr.services.customer.GetCustomers;
import com.google.common.collect.Streams;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class GetCustomersImpl implements GetCustomers {
    private final CustomerRepository customerRepository;

    public GetCustomersImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public GetAllCustomersResponseDTO getAllCustomers() {
        Iterable<Customer> results = this.customerRepository.findAll();

        final GetAllCustomersResponseDTO response = new GetAllCustomersResponseDTO();
        response.setCustomers(Streams.stream(results)
                .map(CustomerDTOConverter::convertToDTO)
                .collect(Collectors.toList()));

        return response;
    }
}
