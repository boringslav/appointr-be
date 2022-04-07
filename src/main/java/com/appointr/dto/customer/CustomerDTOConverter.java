package com.appointr.dto.customer;

import com.appointr.model.Customer;

public class CustomerDTOConverter {
    private CustomerDTOConverter() {
    }

    public static CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .password(customer.getPassword())
                .build();
    }
}
