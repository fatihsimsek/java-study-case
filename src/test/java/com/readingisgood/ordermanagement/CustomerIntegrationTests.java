package com.readingisgood.ordermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.Log;
import com.readingisgood.ordermanagement.adapter.LoginRequest;
import com.readingisgood.ordermanagement.adapter.LoginResponse;
import com.readingisgood.ordermanagement.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTests {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLogin_Ok() throws Exception{
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("fatih.simsek@outlook.com");
        loginRequest.setPassword("12345");

        LoginResponse response = new LoginResponse(true, "token");

        given(customerService.login(any(LoginRequest.class))).willReturn(response);

        mockMvc.perform(post("/customer/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andExpect(status().isOk());
    }
}
