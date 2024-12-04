package com.spring_cloud.eureka.client.order.order.dto;

import com.spring_cloud.eureka.client.order.order.OrderProduct;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductResponseDto {
    private Long orderProductId;     // 주문 항목 ID
    private Long productId;          // 메뉴 ID
    private int quantity;         // 수량
    private int totalPrice;       // 총액 (수량 * 가격)

    public static OrderProductResponseDto from(OrderProduct orderProduct) {
        return OrderProductResponseDto.builder()
                .orderProductId(orderProduct.getId())
                .productId(orderProduct.getProductId())
                .quantity(orderProduct.getQuantity())
                .totalPrice(orderProduct.getTotalPrice())
                .build();
    }
}
