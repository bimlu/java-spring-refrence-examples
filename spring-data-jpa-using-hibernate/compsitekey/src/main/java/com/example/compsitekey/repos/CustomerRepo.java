package com.example.compsitekey.repos;

import com.example.compsitekey.entities.Customer;
import com.example.compsitekey.entities.CustomerId;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, CustomerId> {
}
