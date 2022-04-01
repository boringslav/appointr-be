package com.appointr.services.customer.impl;

import com.appointr.dto.customer.CustomerDTOConverter;
import com.appointr.dto.customer.GetAllCustomersResponseDTO;
import com.appointr.model.Customer;
import com.appointr.repository.CustomerReposity;
import com.appointr.services.customer.GetCustomers;
import com.google.common.collect.Streams;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class GetCustomersImpl implements GetCustomers {
    private final CustomerReposity customerReposity;

    public GetCustomersImpl(CustomerReposity customerReposity) {
        this.customerReposity = customerReposity;
    }

    @Override
    public GetAllCustomersResponseDTO getAllCustomers() {
        Iterable<Customer> results = this.customerReposity.findAll();

        final GetAllCustomersResponseDTO response = new GetAllCustomersResponseDTO();
        response.setCustomers(Streams.stream(results)
                .map(CustomerDTOConverter::convertToDTO)
                .collect(Collectors.toList()));

        return response;
    }
}
