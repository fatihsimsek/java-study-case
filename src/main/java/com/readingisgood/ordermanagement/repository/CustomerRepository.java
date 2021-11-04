package com.readingisgood.ordermanagement.repository;

import com.readingisgood.ordermanagement.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(String id);

    Optional<Customer> findByEmail(String id);

    void create(Customer customer);
}
