package com.appointr.controllers;


import com.appointr.dto.customer.CustomerDTO;
import com.appointr.dto.customer.CustomerSignUpRequestDTO;
import com.appointr.dto.customer.CustomerSignUpResponseDTO;
import com.appointr.dto.customer.GetAllCustomersResponseDTO;
import com.appointr.services.customer.CustomerSignUp;
import com.appointr.services.customer.GetCustomerById;
import com.appointr.services.customer.GetCustomers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final GetCustomers getCustomers;
    private final GetCustomerById getCustomerById;
    private final CustomerSignUp customerSignUp;

    public CustomerController(GetCustomers getCustomers, GetCustomerById getCustomerById, CustomerSignUp customerSignUp) {
        this.getCustomers = getCustomers;
        this.getCustomerById = getCustomerById;
        this.customerSignUp = customerSignUp;
    }

    @GetMapping
    public ResponseEntity<GetAllCustomersResponseDTO> getAllCustomers() {
        return ResponseEntity.ok(getCustomers.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(value = "id") final long id) {
        final Optional<CustomerDTO> customer = getCustomerById.getCustomer(id);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer.get());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CustomerSignUpResponseDTO> signUp(
            @RequestBody @Valid CustomerSignUpRequestDTO signUpRequestObject
    ) {
        CustomerSignUpResponseDTO response = customerSignUp.signUp(signUpRequestObject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}