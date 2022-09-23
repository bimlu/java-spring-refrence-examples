package com.example.multipledatasources.product.repos;

import com.example.multipledatasources.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
