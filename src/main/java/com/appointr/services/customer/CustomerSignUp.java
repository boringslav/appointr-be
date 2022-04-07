package com.appointr.services.customer;

import com.appointr.dto.customer.CustomerSignUpRequestDTO;
import com.appointr.dto.customer.CustomerSignUpResponseDTO;

import java.util.Optional;

public interface CustomerSignUp {
    CustomerSignUpResponseDTO signUp(CustomerSignUpRequestDTO requestDTO);
}
