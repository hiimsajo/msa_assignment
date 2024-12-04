package com.spring_cloud.eureka.client.product.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private String name;
    private Long price;
}
