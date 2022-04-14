package com.appointr.repository;

import com.appointr.repository.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
/**
 * When removing the AutoconfigureTestDatabase -> the tests are not working
 * Application.properties file with test db connection is ready
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findCustomerByEmail_shouldReturnCustomer_whenItExists() {

        entityManager.persist(Customer.builder()
                .email("bostoycontactTEST@gmail.com")
                .firstName("Borislav")
                .lastName("Stoyanov")
                .password("123123")
                .build());

        Customer foundCustomer = customerRepository.findCustomerByEmail("bostoycontactTEST@gmail.com");
        Customer expectedCustomer = Customer.builder()
                .email("bostoycontactTEST@gmail.com")
                .firstName("Borislav")
                .lastName("Stoyanov")
                .build();

        assertEquals(foundCustomer.getEmail(), expectedCustomer.getEmail());
        assertEquals(foundCustomer.getFirstName(), expectedCustomer.getFirstName());
        assertEquals(foundCustomer.getLastName(), expectedCustomer.getLastName());
    }


    @Test
    void existsByEmail_shouldReturnNull_whenCustomerNotFound() {
        Boolean customerExists = customerRepository.existsByEmail("dontusethatemailindb@gmail.com");
        assertFalse(customerExists);
    }
}