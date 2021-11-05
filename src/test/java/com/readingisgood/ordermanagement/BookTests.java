package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.adapter.BookCreateRequest;
import com.readingisgood.ordermanagement.adapter.BookDto;
import com.readingisgood.ordermanagement.adapter.BookUpdateStockRequest;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.repository.BookRepository;
import com.readingisgood.ordermanagement.service.BookService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class BookTests {

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@Test
	void createBook_Ok() {
		BookCreateRequest request = new BookCreateRequest();
		request.setName("Book1");
		request.setWriterName("Writer1");
		request.setAmount(new BigDecimal(100));
		request.setStock(10);

		Book book = bookService.create(request);
		Assert.isTrue(book != null, "Book create result must not be null");
	}

	@Test
	void createBook_NotOk() {
		BookCreateRequest request = new BookCreateRequest();
		request.setName("Book1");
		request.setStock(10);

		Book book = bookService.create(request);
		Assert.isTrue(book == null, "Book create result must be null");
	}

	@Test
	void listBook_Ok() {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
		book.setName("Book1");
		book.setWriterName("Writer1");
		book.setAmount(new BigDecimal(100));
		book.setStock(10);
		books.add(book);

		given(bookRepository.list()).willReturn(books);

		List<BookDto> bookDtos = bookService.list();
		Assert.isTrue(bookDtos.size() == 1, "Book list size must be 1");
	}

	@Test
	void updateStock_Ok() {
		BookUpdateStockRequest request = new BookUpdateStockRequest();
		request.setBookId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
		request.setStock(12);

		Book book = new Book();
		book.setStock(12);
		book.setId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");

		given(bookRepository.findById(any(String.class))).willReturn(Optional.of(book));

		boolean result = bookService.updateStock(request);
		Assert.isTrue(result, "Update stock result must be true");
	}

	@Test
	void updateStock_NotOk() {
		BookUpdateStockRequest request = new BookUpdateStockRequest();
		request.setBookId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");

		Book book = new Book();
		book.setStock(12);
		book.setId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");

		given(bookRepository.findById(any(String.class))).willReturn(Optional.of(book));

		boolean result = bookService.updateStock(request);
		Assert.isTrue(!result, "Update stock is not valid");
	}

}
