package com.swiftcart.product.repository;

import com.swiftcart.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCase(String category);

    boolean existsByTitle(String title);
}
