package com.appointr.repository;

import com.appointr.repository.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
    Customer findCustomerByEmail(@NotBlank @Email String email);
    Boolean existsByEmail(@NotBlank @Email String email);
}
