package com.readingisgood.ordermanagement.controller;

import com.readingisgood.ordermanagement.adapter.*;
import com.readingisgood.ordermanagement.entity.Customer;
import com.readingisgood.ordermanagement.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Api("Customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService  = customerService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create")
    @Transactional
    public ResponseEntity create(@RequestBody CustomerCreateRequest request) {
        Customer customer = customerService.create(request);
        if(customer == null){
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Request is not valid"));
        }
        return ResponseEntity.ok(new ApiResponse(true, customer.getId()));
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = customerService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/order/{pageIndex}/{pageSize}")
    @ApiOperation(value = "List Order")
    public ResponseEntity getOrder(@PathVariable("id") String id, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        List<OrderDto> orders = customerService.listOrder(id, pageIndex, pageSize);
        return ResponseEntity.ok(orders);
    }
}
