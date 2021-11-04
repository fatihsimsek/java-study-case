package com.readingisgood.ordermanagement.controller;

import com.readingisgood.ordermanagement.adapter.*;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Api("Book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create")
    @Transactional
    public ResponseEntity<ApiResponse> create(@RequestBody BookCreateRequest request) {
        Book book = bookService.create(request);
        if(book == null){
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Request is not valid"));
        }
        return ResponseEntity.ok(new ApiResponse(true, book.getId()));
    }

    @GetMapping("/list")
    @ApiOperation(value = "List")
    public ResponseEntity list() {
        List<BookDto> books = bookService.list();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/updateStock")
    @ApiOperation(value = "Update Stock")
    @Transactional
    public ResponseEntity<ApiResponse> updateStock(@RequestBody BookUpdateStockRequest request) {
        if(!bookService.updateStock(request)) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Request is not valid"));
        }
        return ResponseEntity.ok(new ApiResponse(true));
    }
}
