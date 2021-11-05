package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.adapter.StatisticsDto;
import com.readingisgood.ordermanagement.repository.OrderRepository;
import com.readingisgood.ordermanagement.service.StatisticsService;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class StatisticsTests {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private StatisticsService statisticsService;

    @Test
    void listStatistics_Ok() {
        List<StatisticsDto> result = new ArrayList<>();
        given(orderRepository.statisticByCustomerId(any(String.class))).willReturn(result);

        List<StatisticsDto> statisticsDtoList = statisticsService.list("cee4fd77-1675-4693-8255-56494922ba4c");
        Assert.isTrue(result.size() == 0, "Statistic list must be 0");
    }
}
