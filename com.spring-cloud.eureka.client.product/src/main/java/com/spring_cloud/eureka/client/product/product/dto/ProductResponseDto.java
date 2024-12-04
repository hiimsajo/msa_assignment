package com.spring_cloud.eureka.client.product.product.dto;

import com.spring_cloud.eureka.client.product.product.Product;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public static ProductResponseDto of(final Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
