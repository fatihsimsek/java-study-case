package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.*;
import com.readingisgood.ordermanagement.entity.Customer;

import java.util.List;

public interface CustomerService {
    LoginResponse login(LoginRequest request);
    Customer create(CustomerCreateRequest request);
    List<OrderDto> listOrder(String customerId, int pageIndex, int pageSize);
}
