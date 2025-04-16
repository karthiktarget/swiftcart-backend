package com.swiftcart.product.service;

import com.swiftcart.product.dto.ProductDTO;
import com.swiftcart.product.entity.Product;
import com.swiftcart.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.restTemplate = new RestTemplate();
    }

    public void fetchAndSaveProductsFromAPI() {
        String apiUrl = "https://fakestoreapi.com/products";
        ProductDTO[] products = restTemplate.getForObject(apiUrl, ProductDTO[].class);

        if (products != null) {
            Arrays.stream(products).forEach(dto -> {
                // ✅ Prevent duplicates using title
                if (!productRepository.existsByTitle(dto.getTitle())) {
                    Product product = new Product();
                    product.setTitle(dto.getTitle());
                    product.setDescription(dto.getDescription());
                    product.setCategory(dto.getCategory());
                    product.setImage(dto.getImage());
                    product.setPrice(dto.getPrice());
                    productRepository.save(product);
                }
            });
        }
    }
}
