package com.readingisgood.ordermanagement.security;

import com.readingisgood.ordermanagement.entity.Customer;
import com.readingisgood.ordermanagement.exception.ResourceNotFoundException;
import com.readingisgood.ordermanagement.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtTokenProvider jwtTokenProvider;

    private CustomerRepository customerRepository;

    @Autowired
    public JwtAuthenticationFilter(CustomerRepository customerRepository, JwtTokenProvider jwtTokenProvider) {
        this.customerRepository = customerRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtTokenProvider.isValid(jwt)) {

                String customerId = jwtTokenProvider.getUserIdFromJWT(jwt);
                Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

                UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(UserPrincipal.create(customer), null, null);
                SecurityContextHolder.getContext().setAuthentication(principal);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "InSufficient Privileges");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
