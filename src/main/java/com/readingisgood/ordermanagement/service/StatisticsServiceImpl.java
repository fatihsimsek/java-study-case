package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.StatisticsDto;
import com.readingisgood.ordermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private OrderRepository orderRepository;

    @Autowired
    public StatisticsServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<StatisticsDto> list(String customerId) {
        return orderRepository.statisticByCustomerId(customerId);
    }
}
