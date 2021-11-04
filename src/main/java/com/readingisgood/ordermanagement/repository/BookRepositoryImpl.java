package com.readingisgood.ordermanagement.repository;

import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Book> list() {
        TypedQuery<Book> query = entityManager.createQuery("from Book", Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(String id) {
        Book book = entityManager.find(Book.class, id);
        return Optional.of(book);
    }

    @Override
    public void create(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }
}
