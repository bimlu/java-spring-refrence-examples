package com.example.compsitekey;

import com.example.compsitekey.entities.Customer;
import com.example.compsitekey.entities.CustomerId;
import com.example.compsitekey.repos.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompsitekeyApplicationTests {

	@Autowired
	CustomerRepo repo;

	@Test
	void contextLoads() {
	}

	@Test
	void testSaveCustomer() {

		Customer customer = new Customer();

		CustomerId customerId = new CustomerId();
		customerId.setId(123);
		customerId.setEmail("test@test.com");

		customer.setId(customerId);
		customer.setName("Test");

		repo.save(customer);
	}
}
