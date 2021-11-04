package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.OrderCreateRequest;
import com.readingisgood.ordermanagement.adapter.OrderDto;
import com.readingisgood.ordermanagement.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order create(OrderCreateRequest request);
    OrderDto get(String orderId);
    List<OrderDto> list(Date from, Date to);
}
