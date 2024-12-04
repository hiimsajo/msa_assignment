package com.spring_cloud.eureka.client.order.order.dto;

import com.spring_cloud.eureka.client.order.order.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class OrderResponseDto {

    private Long orderId;
    private String name;
    private List<OrderProductResponseDto> orderProducts;

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .name(order.getName())
                .orderProducts(order.getOrderProducts().stream()
                        .map(OrderProductResponseDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

}
