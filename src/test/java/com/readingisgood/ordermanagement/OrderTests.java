package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.adapter.OrderCreateRequest;
import com.readingisgood.ordermanagement.adapter.OrderDto;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.entity.Customer;
import com.readingisgood.ordermanagement.entity.Order;
import com.readingisgood.ordermanagement.repository.BookRepository;
import com.readingisgood.ordermanagement.repository.CustomerRepository;
import com.readingisgood.ordermanagement.repository.OrderRepository;
import com.readingisgood.ordermanagement.service.OrderService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class OrderTests {
    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder_Ok() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setBookId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
        request.setCustomerId("cee4fd77-1675-4693-8255-56494922ba4c");
        request.setCount(2);

        Customer customer = new Customer();
        customer.setId("cee4fd77-1675-4693-8255-56494922ba4c");

        Book book = new Book();
        book.setId("e4a1492f-0230-4dff-a7db-874b41ab9c24");
        book.setStock(10);
        book.setAmount(new BigDecimal(100));

        given(customerRepository.findById(any(String.class))).willReturn(Optional.of(customer));
        given(bookRepository.findById(any(String.class))).willReturn(Optional.of(book));

        Order order = orderService.create(request);
        Assert.isTrue(order != null, "Order create result must not be null");
    }

    @Test
    void createOrder_NotOk() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setBookId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
        request.setCustomerId("cee4fd77-1675-4693-8255-56494922ba4c");
        request.setCount(-1);

        Order order = orderService.create(request);
        Assert.isTrue(order == null, "Order create result must be null");
    }

    @Test
    void getOrder_Ok() {
        Order order = new Order();
        order.setId("03dbc965-99e1-4904-8ba0-90ccfe612ddd");
        order.setBookId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
        order.setCustomerId("cee4fd77-1675-4693-8255-56494922ba4c");
        order.setAmount(new BigDecimal(100));
        order.setCount(2);
        order.setCreateDate(new Date());

        given(orderRepository.findById(any(String.class))).willReturn(Optional.of(order));

        OrderDto orderDto = orderService.get("03dbc965-99e1-4904-8ba0-90ccfe612ddd");
        Assert.isTrue(orderDto != null, "OrderDto get result must not be null");
    }

    @Test
    void getOrder_NotOk() {
        given(orderRepository.findById(any(String.class))).willReturn(Optional.empty());

        OrderDto orderDto = orderService.get("03dbc965-99e1-4904-8ba0-90ccfe612ddd");
        Assert.isTrue(orderDto == null, "OrderDto get result must be null");
    }

    @Test
    void listOrder_Ok() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId("03dbc965-99e1-4904-8ba0-90ccfe612ddd");
        order.setBookId("6909ea31-2f3d-470c-96be-e21a5b7b0ffb");
        order.setCustomerId("cee4fd77-1675-4693-8255-56494922ba4c");
        order.setAmount(new BigDecimal(100));
        order.setCount(2);
        order.setCreateDate(new Date());
        orders.add(order);

        given(orderRepository.listByDate(any(Date.class), any(Date.class))).willReturn(orders);
        List<OrderDto> orderDtos = orderService.list(new Date(), new Date());

        Assert.isTrue(orderDtos.size() == 1, "Order list size must be 1");
    }
}
