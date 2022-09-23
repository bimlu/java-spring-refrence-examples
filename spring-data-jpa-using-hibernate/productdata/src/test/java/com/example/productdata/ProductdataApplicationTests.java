package com.example.productdata;

import com.example.productdata.product.entities.Product;
import com.example.productdata.product.repos.ProductRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ProductdataApplicationTests {

    @Autowired
    ProductRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreate() {
        Product product = new Product();
        product.setId(1);
        product.setName("Iphone");
        product.setDesc("Awesome");
        product.setPrice(1000d);

        repository.save(product);
    }

    @Test
    public void testRead() {
        Product product = repository.findById(1).get();
        Assertions.assertNotNull(product);
        Assertions.assertEquals("Iphone", product.getName());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + product.getDesc());
    }

    @Test
    public void testUpdate() {
        Product product = repository.findById(1).get();
        product.setPrice(1200d);
        repository.save(product);
    }

    @Test
    public void testDelete() {
        if (repository.existsById(1)) {
            repository.deleteById(1);
        }
    }

    @Test
    public void testCount() {
        System.out.println("Total Recods=============>>>>>>>>>>>>" + repository.count());
    }

    @Test
    public void testFindByName() {
        List<Product> products = repository.findByName("Iwatch");
        products.forEach(p -> System.out.println(p.getPrice()));
    }

    @Test
    public void testFindByNameAndDesc() {
        List<Product> products = repository.findByNameAndDesc("TV", "From Samsung Inc");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByPriceGreaterThan() {
        List<Product> products = repository.findByPriceGreaterThan(800d);
        products.forEach(p -> System.out.println(p.getPrice()));
    }

    @Test
    public void testFindByDescContains() {
        List<Product> products = repository.findByDescContains("Apple");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByPriceBetween() {
        List<Product> products = repository.findByPriceBetween(800d, 1200d);
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByDescLike() {
        List<Product> products = repository.findByDescLike("%Apple%");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByIdIn() {
        List<Product> products = repository.findByIdIn(Arrays.asList(2, 3, 4), PageRequest.of(0, 2));
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPaging() {
        Pageable pageable = PageRequest.of(1, 2);

        Page<Product> results = repository.findAll(pageable);

        results.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllSorting() {
		repository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"), new Sort.Order(Sort.Direction.ASC, "price"))).forEach(p -> System.out.println(p.getName() + ", " + p.getPrice()));
//        repository.findAll(Sort.by(Sort.Direction.DESC, "name", "price")).forEach(p -> System.out.println(p.getName() + ", " + p.getPrice()));
    }

	@Test
	public void testFindAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "name");
		repository.findAll(pageable).forEach(p -> System.out.println(p.getName() + ", " + p.getPrice()));
	}

    @Test
    @Transactional
    public void testCaching() {
        Session session = entityManager.unwrap(Session.class);

        Product product = repository.findById(1).get();

        session.evict(product);

        repository.findById(1);
    }

    @Test
    public void testFindAllProducts() {
        repository.findAllProducts().forEach(System.out::println);
    }

    @Test
    public void testFindAllProductsByPrice() {
        repository.findAllProductsByPrice(800.00).forEach(System.out::println);
    }

    @Test
    public void testFindAllProductsCountByPrice() {
        System.out.println(repository.findAllProductsCountByPrice(800.00));
    }

}
