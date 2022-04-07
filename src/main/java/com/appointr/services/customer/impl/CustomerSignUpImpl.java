package com.appointr.services.customer.impl;

import com.appointr.dto.customer.CustomerSignUpRequestDTO;
import com.appointr.dto.customer.CustomerSignUpResponseDTO;
import com.appointr.exception.CustomerAlreadyExistsException;
import com.appointr.repository.CustomerRepository;
import com.appointr.repository.entity.Customer;
import com.appointr.services.customer.CustomerSignUp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerSignUpImpl implements CustomerSignUp {

    private final CustomerRepository customerRepository;


    @Transactional
    @Override
    public CustomerSignUpResponseDTO signUp(CustomerSignUpRequestDTO request) {

        if(customerRepository.existsByEmail(request.getEmail())){
            throw new CustomerAlreadyExistsException();
        }

        Customer newCustomer = Customer.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .build();

        Customer savedCustomer = save(newCustomer);

        return CustomerSignUpResponseDTO.builder()
                .email(savedCustomer.getEmail())
                .firstName(savedCustomer.getFirstName())
                .lastName(savedCustomer.getLastName())
                .id(savedCustomer.getId())
                .build();
    }


    private Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
