package com.spring_cloud.eureka.client.order.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
    private String name;
    private List<OrderProductRequestDto> orderProducts;

    public OrderRequestDto(String name, List<OrderProductRequestDto> OrderProducts) {
        this.name = name;
        this.orderProducts = orderProducts;
    }

}
