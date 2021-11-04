package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.BookCreateRequest;
import com.readingisgood.ordermanagement.adapter.BookDto;
import com.readingisgood.ordermanagement.adapter.BookUpdateStockRequest;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(BookCreateRequest request) {
        if(validateBook(request)) {
            Book book = toBook(request);
            bookRepository.create(book);
            return book;
        }
        return null;
    }

    @Override
    public List<BookDto> list() {
        List<Book> books = bookRepository.list();
        return books.stream().map(x -> new BookDto(x.getId(), x.getName(), x.getWriterName(), x.getAmount(), x.getStock()))
                    .collect(Collectors.toList());
    }

    @Override
    public boolean updateStock(BookUpdateStockRequest request) {
        if(validateUpdateStock(request)) {
            Optional<Book> optionalBook = bookRepository.findById(request.getBookId());
            if(optionalBook.isPresent()) {
                Book book = optionalBook.get();
                book.setStock(request.getStock());
                bookRepository.update(book);
                return true;
            }
        }
        return false;
    }

    private boolean validateBook(BookCreateRequest request) {
        if(request == null)
            return false;
        if(ObjectUtils.isEmpty(request.getName()))
            return false;
        if(ObjectUtils.isEmpty(request.getWriterName()))
            return false;
        if(request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            return false;
        if(request.getStock() == null || request.getStock().intValue() <= 0)
            return false;
        return true;
    }

    private boolean validateUpdateStock(BookUpdateStockRequest request) {
        if(request == null)
            return false;
        if(ObjectUtils.isEmpty(request.getBookId()))
            return false;
        if(request.getStock() == null || request.getStock().intValue() <= 0)
            return false;
        return true;
    }

    private Book toBook(BookCreateRequest request) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(request.getName());
        book.setWriterName(request.getWriterName());
        book.setAmount(request.getAmount());
        book.setStock(request.getStock());
        book.setCreateDate(new Date());
        return book;
    }
}
