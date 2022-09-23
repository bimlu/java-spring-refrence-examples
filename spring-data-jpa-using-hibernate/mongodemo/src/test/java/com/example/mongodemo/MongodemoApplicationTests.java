package com.example.mongodemo;

import com.example.mongodemo.model.Product;
import com.example.mongodemo.repos.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MongodemoApplicationTests {

	@Autowired
	ProductRepository repo;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSave() {

		Product product = new Product();

		product.setName("Mac Book Pro");
		product.setPrice(2000F);

		Product savedProduct = repo.save(product);
		assertNotNull(savedProduct);
	}

	@Test
	public void testFindAll() {

		List<Product> products = repo.findAll();
		assertEquals(1, products.size());
	}

	@Test
	public void testDelete() {

		repo.deleteById("6327bc090814ae34b55ce396");
		Optional<Product> product = repo.findById("6327bc090814ae34b55ce396");
		assertEquals(Optional.empty(), product);
	}
}
