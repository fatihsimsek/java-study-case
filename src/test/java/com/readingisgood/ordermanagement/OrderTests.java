package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.repository.OrderRepository;
import com.readingisgood.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OrderTests {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder_Ok() {

    }

    @Test
    void createOrder_NotOk() {

    }
}
