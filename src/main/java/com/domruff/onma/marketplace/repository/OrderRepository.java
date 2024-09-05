package com.domruff.onma.marketplace.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domruff.onma.marketplace.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
}
