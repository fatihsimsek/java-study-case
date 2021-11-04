package com.readingisgood.ordermanagement.controller;

import com.readingisgood.ordermanagement.adapter.ApiResponse;
import com.readingisgood.ordermanagement.adapter.OrderCreateRequest;
import com.readingisgood.ordermanagement.adapter.OrderDto;
import com.readingisgood.ordermanagement.entity.Order;
import com.readingisgood.ordermanagement.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@Api("Order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create")
    @Transactional
    public ResponseEntity<ApiResponse> create(@RequestBody OrderCreateRequest request) {
        Order order = orderService.create(request);
        if(order == null){
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Request is not valid"));
        }
        return ResponseEntity.ok(new ApiResponse(true, order.getId()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get")
    public ResponseEntity<OrderDto> get(@PathVariable("id") String id) {
        OrderDto order = orderService.get(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/list/{from}/{to}")
    @ApiOperation(value = "List")
    public ResponseEntity list(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")Date from,
                               @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")Date to) {
        List<OrderDto> orderDtoList = orderService.list(from, to);
        return ResponseEntity.ok(orderDtoList);
    }
}
