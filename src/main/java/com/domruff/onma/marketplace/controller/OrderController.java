package com.domruff.onma.marketplace.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.domruff.onma.marketplace.dto.OrderRequest;
import com.domruff.onma.marketplace.dto.OrderResponse;
import com.domruff.onma.marketplace.service.OrderService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Create Order API Call - POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequest orderRequest) {

        orderService.createOrder(orderRequest);
    }

    // Get all Orders API Call - GET
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {

        return orderService.getAllOrders();
    }

    // Get Order by ID API Call - GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    // Update Order API Call - PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {

        orderService.updateOrder(id, orderRequest);
    }

    // Delete Order API Call - DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);
    }

}
