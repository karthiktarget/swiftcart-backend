
package com.swiftcart.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 255)
    private String category;

    @Column(length = 1000)
    private String image;
    private Double price;
}
