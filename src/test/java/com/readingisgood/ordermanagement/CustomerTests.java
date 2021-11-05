package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.adapter.CustomerCreateRequest;
import com.readingisgood.ordermanagement.adapter.OrderDto;
import com.readingisgood.ordermanagement.entity.Customer;
import com.readingisgood.ordermanagement.entity.Order;
import com.readingisgood.ordermanagement.repository.CustomerRepository;
import com.readingisgood.ordermanagement.repository.OrderRepository;
import com.readingisgood.ordermanagement.service.CustomerService;
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
public class CustomerTests {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomer_Ok() {
        CustomerCreateRequest request = new CustomerCreateRequest();
        request.setEmail("fatih.simsek@outlook.com");
        request.setFullname("Fatih Şimşek");
        request.setPassword("12345");
        request.setRePassword("12345");

        given(customerRepository.findByEmail(any(String.class))).willReturn(Optional.empty());

        Customer customer = customerService.create(request);
        Assert.isTrue(customer != null, "Customer create result must not be null");
    }

    @Test
    void createCustomer_NotOk() {
        CustomerCreateRequest request = new CustomerCreateRequest();
        request.setEmail("fatih.simsek@outlook.com");
        request.setPassword("12345");

        Customer customer = customerService.create(request);
        Assert.isTrue(customer == null, "Customer create result must be null");
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

        given(orderRepository.listByCustomerId(any(String.class), any(Integer.class), any(Integer.class))).willReturn(orders);
        List<OrderDto> orderDtos = customerService.listOrder("cee4fd77-1675-4693-8255-56494922ba4c", 0, 10);

        Assert.isTrue(orderDtos.size() == 1, "Order list size must be 1");
    }
}
