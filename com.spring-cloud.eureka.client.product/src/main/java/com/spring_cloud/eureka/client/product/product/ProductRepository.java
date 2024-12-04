package com.spring_cloud.eureka.client.product.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllById(Long productId, Pageable pageable);
    Optional<Product> findById(Long productId);

}
