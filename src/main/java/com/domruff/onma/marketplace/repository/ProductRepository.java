package com.domruff.onma.marketplace.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domruff.onma.marketplace.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
