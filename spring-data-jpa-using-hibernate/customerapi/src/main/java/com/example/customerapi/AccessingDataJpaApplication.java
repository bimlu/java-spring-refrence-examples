package com.example.customerapi;

import com.example.customerapi.repos.AddressRepository;
import com.example.customerapi.repos.CustomerRepository;
import com.example.customerapi.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessingDataJpaApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepository;


	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println("*** Inserting Data ***");
//
//		Customer customer = new Customer();
//		customer.setEmail("robin@test.com");
//		customer.setFirstName("Robin");
//		customer.setLastName("Rawat");
//		customerRepository.save(customer);
//
//		customer = new Customer();
//		customer.setEmail("saurabh@test.com");
//		customer.setFirstName("Saurabh");
//		customer.setLastName("Maithani");
//		customerRepository.save(customer);
//
//		orderRepository.save(new Order());
//		orderRepository.save(new Order());
//
//		Address address = new Address();
//		address.setAddressLine1("Likuu Colony");
//		address.setAddressLine2("IT Park Road");
//		address.setZipcode("123456");
//		address.setCity("Dehradun");
//		address.setState("Uttrakhand");
//		address.setCountry("India");
//		addressRepository.save(address);
//
//		address = new Address();
//		address.setAddressLine1("Refresh Colony");
//		address.setAddressLine2("Gandhi Road");
//		address.setZipcode("123456");
//		address.setCity("Delhi");
//		address.setState("Uttrakhand");
//		address.setCountry("India");
//		addressRepository.save(address);
//
//		System.out.println("*** Completed ***");
	}
}
