package com.spring_cloud.eureka.client.order.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProductRequestDto {
    private Long productId;  // 주문할 메뉴의 ID
    private int quantity;  // 주문할 메뉴의 수량

    public OrderProductRequestDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
