package com.example.customerapi;

import com.example.customerapi.entities.Address;
import com.example.customerapi.entities.Customer;
import com.example.customerapi.repos.AddressRepository;
import com.example.customerapi.repos.CustomerRepository;
import com.example.customerapi.repos.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class AccessingDataJpaApplicationTests {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetOrder() {
		System.out.println(orderRepository.findById(1L).get());;
	}

	@Transactional
	@Test
	void testGetCustomer() {
		Customer customer = customerRepository.findById(1L).get();
		System.out.println(customer);
		customer.getAddresses().forEach(System.out::println);

	}

	@Test
	void testGetAddress() {
		Address address = addressRepository.findById(1L).get();
		System.out.println(address);
	}
	
	@Test
	void testCreateCustomer() {

		Customer customer = new Customer();
		customer.setName("kamal");
		
		Address address1 = new Address();
		address1.setAddressLine("park road");
		address1.setCity("delhi");
		address1.setCountry("India");
		address1.setZipcode("123456");
		address1.setCustomer(customer);

		Address address2 = new Address();
		address2.setAddressLine("new park road");
		address2.setCity("mumbai");
		address2.setCountry("India");
		address2.setZipcode("100006");
		address2.setCustomer(customer);

		customer.setAddresses(Arrays.asList(address1, address2));
		customerRepository.save(customer);
	}

}
