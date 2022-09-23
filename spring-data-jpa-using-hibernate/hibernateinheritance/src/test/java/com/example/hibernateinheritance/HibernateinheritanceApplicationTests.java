package com.example.hibernateinheritance;

import com.example.hibernateinheritance.entities.Check;
import com.example.hibernateinheritance.entities.CreditCard;
import com.example.hibernateinheritance.repos.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateinheritanceApplicationTests {

	@Autowired
	PaymentRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createCreditCardPayment() {

		CreditCard cc = new CreditCard();
		cc.setId(123);
		cc.setAmount(100.00d);
		cc.setCardnumber("1234567890");
		repository.save(cc);
	}

	@Test
	public void createCheckPayment() {

		Check ch = new Check();
		ch.setId(245);
		ch.setAmount(230.00d);
		ch.setChecknumber("5675675679");
		repository.save(ch);
	}
}
