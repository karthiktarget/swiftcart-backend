
package com.swiftcart.product.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String title;
    private String description;
    private String category;
    private String image;
    private Double price;
}
