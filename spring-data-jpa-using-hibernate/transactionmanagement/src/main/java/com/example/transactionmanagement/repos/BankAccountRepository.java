package com.example.transactionmanagement.repos;

import com.example.transactionmanagement.entities.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
}
