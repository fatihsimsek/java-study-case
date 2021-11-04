package com.readingisgood.ordermanagement.repository;

import com.readingisgood.ordermanagement.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> list();
    Optional<Book> findById(String id);
    void create(Book book);
    void update(Book book);
}
