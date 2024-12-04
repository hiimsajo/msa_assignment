package com.spring_cloud.eureka.client.product.product;

import com.spring_cloud.eureka.client.product.product.dto.CreateProductDto;
import com.spring_cloud.eureka.client.product.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @CacheEvict(cacheNames = "allProductsCache", allEntries = true)
    public ProductResponseDto createProduct(CreateProductDto createProductDto){
        Product product = productRepository.save(new Product());
        return new ProductResponseDto(product);
    }

    @Cacheable(cacheNames = "allProductsCache", key = "{ #page, #size}")
    public Page<ProductResponseDto> getProducts(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productList = productRepository.findAllById(productId, pageable);

        if (productList.isEmpty()) {
            throw new IllegalArgumentException("Product not found.");
        }
        return productList.map(product -> new ProductResponseDto(product));
    }

    public Product checkProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));
    }
}
