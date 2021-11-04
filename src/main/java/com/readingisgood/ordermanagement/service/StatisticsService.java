package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.StatisticsDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsDto> list(String customerId);
}
