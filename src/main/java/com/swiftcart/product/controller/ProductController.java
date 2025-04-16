package com.swiftcart.product.controller;

import com.swiftcart.product.entity.Product;
import com.swiftcart.product.service.ProductService;
import com.swiftcart.product.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // 🔁 Fetch & Save Fake Store API products (no duplicates)
    @PostMapping("/fetch")
    public String fetchProducts() {
        productService.fetchAndSaveProductsFromAPI();
        return "Products fetched and saved!";
    }

    // 📦 Get all products (useful for admin/test)
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🔍 Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🧠 Category-based products with pagination (20 per page)
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategoryWithPagination(
            @RequestParam String type,
            @RequestParam(defaultValue = "0") int page) {

        int pageSize = 20; // fixed size per page
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productsPage = productRepository.findByCategoryIgnoreCase(type, pageable);

        return ResponseEntity.ok(productsPage.getContent());
    }

}
