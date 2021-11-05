package com.readingisgood.ordermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.ordermanagement.adapter.BookCreateRequest;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    public void testCreate_NotOk() throws Exception{
        BookCreateRequest request = new BookCreateRequest();
        request.setName("Book1");
        request.setWriterName("Writer1");
        request.setAmount(new BigDecimal(100));
        request.setStock(10);

        Book book = new Book();
        book.setId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
        book.setName("Book1");
        book.setWriterName("Writer1");
        book.setAmount(new BigDecimal(100));
        book.setStock(10);

        given(bookService.create(any(BookCreateRequest.class))).willReturn(book);

        this.mockMvc.perform(post("/boook/create")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().is4xxClientError());
    }
}
