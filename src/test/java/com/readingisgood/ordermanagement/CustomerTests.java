package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.repository.CustomerRepository;
import com.readingisgood.ordermanagement.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CustomerTests {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomer_Ok() {

    }

    @Test
    void createCustomer_NotOk() {

    }
}
