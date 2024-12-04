package com.spring_cloud.eureka.client.order.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndIsDeletedFalse(Long orderId);
    Page<Order> findAllByIsDeletedFalse(Long orderId, Pageable pageable);
}
