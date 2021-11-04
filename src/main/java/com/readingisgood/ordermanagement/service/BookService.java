package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.BookCreateRequest;
import com.readingisgood.ordermanagement.adapter.BookDto;
import com.readingisgood.ordermanagement.adapter.BookUpdateStockRequest;
import com.readingisgood.ordermanagement.entity.Book;

import java.util.List;

public interface BookService {
    Book create(BookCreateRequest request);
    List<BookDto> list();
    boolean updateStock(BookUpdateStockRequest request);
}
