package com.readingisgood.ordermanagement.repository;

import com.readingisgood.ordermanagement.adapter.OrderDto;
import com.readingisgood.ordermanagement.adapter.StatisticsDto;
import com.readingisgood.ordermanagement.entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(String id);
    List<Order> listByCustomerId(String customerId, int pageIndex, int pageSize);
    void create(Order order);
    List<Order> listByDate(Date from, Date to);
    List<StatisticsDto> statisticByCustomerId(String customerId);
}
