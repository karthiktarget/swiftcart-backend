package com.swiftcart.product.repository;

import com.swiftcart.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIgnoreCase(String category);
    Page<Product> findByCategoryIgnoreCase(String category, Pageable pageable);

    boolean existsByTitle(String title);
}
