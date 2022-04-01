package com.appointr.repository;

import com.appointr.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerReposity extends CrudRepository<Customer, Long> {
}
