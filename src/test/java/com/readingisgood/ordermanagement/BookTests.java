package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.adapter.BookUpdateStockRequest;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.repository.BookRepository;
import com.readingisgood.ordermanagement.service.BookService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

}
