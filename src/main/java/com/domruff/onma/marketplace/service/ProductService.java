package com.domruff.onma.marketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domruff.onma.marketplace.dto.ProductRequest;
import com.domruff.onma.marketplace.dto.ProductResponse;
import com.domruff.onma.marketplace.model.Product;
import com.domruff.onma.marketplace.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    // Create Product
    public void createProduct(ProductRequest productRequest) {

        // Map request to model
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .category(productRequest.getCategory())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .sellerId(productRequest.getSellerId())
                .build();

        // Save product to database
        productRepository.save(product);
    }

    // Get all Products
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapProductToProductResponse).toList();
    }

    // Get Product by ID
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        return mapProductToProductResponse(product);
    }

    // Update Product
    public void updateProduct(Long id, ProductRequest productRequest) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setSellerId(productRequest.getSellerId());

        productRepository.save(product);
        log.info("Product {} updated successfully", id);

    }

    // Delete Product
    public void deleteProduct(Long id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product {} deleted successfully", id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    // Helper method to map Order to OrderResponse
    // Allows for Models to be hidden from the Controller
    public ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .sellerId(product.getSellerId())
                .build();
    }

}
