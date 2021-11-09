package com.readingisgood.ordermanagement.service;

import com.readingisgood.ordermanagement.adapter.*;
import com.readingisgood.ordermanagement.entity.Customer;
import com.readingisgood.ordermanagement.entity.Order;
import com.readingisgood.ordermanagement.repository.CustomerRepository;
import com.readingisgood.ordermanagement.repository.OrderRepository;
import com.readingisgood.ordermanagement.security.JwtTokenProvider;
import com.readingisgood.ordermanagement.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               OrderRepository orderRepository,
                               AuthenticationManager authenticationManager,
                               JwtTokenProvider tokenProvider,
                               PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        if(!authentication.isAuthenticated()) {
            return new LoginResponse(false, "Email or password is not correct");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return new LoginResponse(this.tokenProvider.generateToken(userPrincipal));
    }

    @Override
    public Customer create(CustomerCreateRequest request) {
        if(validateCustomer(request)) {
            Customer customer = toCustomer(request);
            customerRepository.create(customer);
            return customer;
        }
        return null;
    }

    @Override
    public List<OrderDto> listOrder(String customerId, int pageIndex, int pageSize) {
        List<Order> orders = orderRepository.listByCustomerId(customerId, pageIndex, pageSize);
        return orders.stream().map(x -> new OrderDto(x.getId(), x.getBookId(), x.getCustomerId(), x.getCount(), x.getAmount(), x.getCreateDate()))
                .collect(Collectors.toList());
    }

    private boolean validateCustomer(CustomerCreateRequest request) {
        if(request == null)
            return false;
        if(ObjectUtils.isEmpty(request.getPassword()))
            return false;
        if(ObjectUtils.isEmpty(request.getFullname()))
            return false;
        if(!request.getPassword().equals(request.getRePassword()))
            return false;
        if(ObjectUtils.isEmpty(request.getEmail()) || !isValidEmail(request.getEmail()))
            return false;

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(request.getEmail());
        return optionalCustomer.isEmpty();
    }

    private Customer toCustomer(CustomerCreateRequest request) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFullname(request.getFullname());
        customer.setEmail(request.getEmail());
        customer.setPassword(this.passwordEncoder.encode(request.getPassword()));
        customer.setCreateDate(new Date());
        return customer;
    }

    private boolean isValidEmail(String emailAddress) {
       return VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress).find();
    }
}
