package com.example.transactionmanagement;

import com.example.transactionmanagement.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionmanagementApplicationTests {

	@Autowired
	BankAccountService service;

	@Test
	void contextLoads() {
	}

	@Test
	public void testTransfer() {
		service.transfer(500);
	}
}
