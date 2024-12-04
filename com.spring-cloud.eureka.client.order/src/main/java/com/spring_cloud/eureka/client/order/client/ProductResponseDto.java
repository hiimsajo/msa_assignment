package com.spring_cloud.eureka.client.order.client;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
}
