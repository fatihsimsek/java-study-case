package com.readingisgood.ordermanagement;

import com.readingisgood.ordermanagement.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderManagementApplicationTests {

	@Autowired
	private CustomerController customerController;

	@Test
	void contextLoads() {
		assertThat(customerController).isNotNull();
	}

}
