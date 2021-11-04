package com.readingisgood.ordermanagement.controller;

import com.readingisgood.ordermanagement.adapter.StatisticsDto;
import com.readingisgood.ordermanagement.entity.Order;
import com.readingisgood.ordermanagement.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@Api("Statistics")
public class StatisticsController {
    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "List")
    public ResponseEntity list(@PathVariable("customerId") String customerId) {
        List<StatisticsDto> response = statisticsService.list(customerId);
        return ResponseEntity.ok(response);
    }
}
