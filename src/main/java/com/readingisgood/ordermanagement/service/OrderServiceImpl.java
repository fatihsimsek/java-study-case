package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.OrderCreateRequest;
import com.readingisgood.ordermanagement.adapter.OrderDto;
import com.readingisgood.ordermanagement.entity.Book;
import com.readingisgood.ordermanagement.entity.Customer;
import com.readingisgood.ordermanagement.entity.Order;
import com.readingisgood.ordermanagement.entity.OrderStatus;
import com.readingisgood.ordermanagement.repository.BookRepository;
import com.readingisgood.ordermanagement.repository.CustomerRepository;
import com.readingisgood.ordermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            BookRepository bookRepository,
                            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order create(OrderCreateRequest request) {
        if(validateOrder(request)) {
            Optional<Book> optionalBook = bookRepository.findById(request.getBookId());
            if(optionalBook.isPresent() && optionalBook.get().getStock() >= request.getCount()) {
                Order order = toOrder(request, optionalBook.get());
                orderRepository.create(order);

                Book book = optionalBook.get();
                book.setStock(book.getStock()- request.getCount());
                bookRepository.update(book);

                return order;
            }
        }
        return null;
    }

    @Override
    public OrderDto get(String id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return new OrderDto(order.getId(), order.getBookId(), order.getCustomerId(), order.getCount(), order.getAmount(), order.getCreateDate());
        }
        return null;
    }

    @Override
    public List<OrderDto> list(Date from, Date to) {
        List<Order> orders = orderRepository.listByDate(from, to);
        return orders.stream().map(x -> new OrderDto(x.getId(), x.getBookId(), x.getCustomerId(), x.getCount(), x.getAmount(), x.getCreateDate()))
                .collect(Collectors.toList());
    }

    private boolean validateOrder(OrderCreateRequest request) {
        if(request == null)
            return false;
        if(ObjectUtils.isEmpty(request.getBookId()))
            return false;
        if(ObjectUtils.isEmpty(request.getCustomerId()))
            return false;
        if(request.getCount() == null || request.getCount().intValue() <= 0)
            return false;

        Optional<Customer> optionalCustomer = customerRepository.findById(request.getCustomerId());
        return optionalCustomer.isPresent();
    }

    private Order toOrder(OrderCreateRequest request, Book book) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setBookId(request.getBookId());
        order.setCustomerId(request.getCustomerId());
        order.setCount(request.getCount());
        order.setAmount(book.getAmount().multiply(new BigDecimal(request.getCount())));
        order.setStatus(OrderStatus.CREATED.name());
        order.setCreateDate(new Date());
        return order;
    }
}
