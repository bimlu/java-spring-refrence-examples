package com.example.transactionmanagement.service;

import com.example.transactionmanagement.entities.BankAccount;
import com.example.transactionmanagement.repos.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    BankAccountRepository repository;

    @Override
    @Transactional
    public void transfer(int amount) {

        BankAccount obamasAccount = repository.findById(1).get();
        obamasAccount.setBal(obamasAccount.getBal() - amount);
        repository.save(obamasAccount);

        if (true) {
            throw new RuntimeException("Hello");
        }

        BankAccount trumpsAccount = repository.findById(2).get();
        trumpsAccount.setBal(trumpsAccount.getBal() + amount);
        repository.save(trumpsAccount);

    }
}
