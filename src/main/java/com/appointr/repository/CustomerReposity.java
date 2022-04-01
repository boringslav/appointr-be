package com.appointr.repository;

import com.appointr.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerReposity extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
}
