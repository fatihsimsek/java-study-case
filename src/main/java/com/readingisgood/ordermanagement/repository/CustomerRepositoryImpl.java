package com.readingisgood.ordermanagement.repository;

import com.readingisgood.ordermanagement.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Customer> findById(String id) {
        Customer customer = entityManager.find(Customer.class, id);
        return Optional.of(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer Where email=:email", Customer.class)
                .setParameter("email", email);

        List<Customer> customers = query.getResultList();
        return customers.size() > 0 ? Optional.of(customers.get(0)) : Optional.empty();
    }

    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }
}
