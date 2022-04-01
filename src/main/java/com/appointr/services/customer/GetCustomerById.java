package com.appointr.services.customer;

import com.appointr.dto.customer.CustomerDTO;

import java.util.Optional;

public interface GetCustomerById {
    Optional<CustomerDTO>getCustomer(long customerId);
}
