package com.spring_cloud.eureka.client.order.order;

import com.spring_cloud.eureka.client.order.client.ProductClient;
import com.spring_cloud.eureka.client.order.client.ProductResponseDto;
import com.spring_cloud.eureka.client.order.order.dto.OrderRequestDto;
import com.spring_cloud.eureka.client.order.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductClient productClient;

    @CacheEvict(cacheNames = "allOrdersCache", allEntries = true)
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto){
        Order order = orderRepository.save(
                Order.builder()
                        .name(orderRequestDto.getName())
                        .build()
        );

        List<OrderProduct> orderProducts = orderRequestDto.getOrderProducts().stream()
                .map(requestProduct -> {
                    ProductResponseDto product = productClient.getProductById(requestProduct.getProductId());

                    OrderProduct orderProduct = OrderProduct.builder()
                            .order(order) // Order와 연관
                            .productId(product.getId())
                            .quantity(requestProduct.getQuantity())
                            .totalPrice(product.getPrice() * requestProduct.getQuantity()) // 총액 계산
                            .build();

                    // OrderProduct 저장
                    return orderProductRepository.save(orderProduct);
                })
                .collect(Collectors.toList());

        order.setOrderProducts(orderProducts);
        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    public Order checkOrder(Long OrderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found."));
    }

    @Cacheable(cacheNames = "allOrdersCache", key = "{ #page, #size}")
    public Page<OrderResponseDto> getOrders(Long orderId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderList = orderRepository.findAllById(orderId, pageable);

        if (orderList.isEmpty()) {
            throw new IllegalArgumentException("Order not found.");
        }
        return orderList.map(order -> new OrderResponseDto(order));
    }
}
