package com.domruff.onma.marketplace.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.domruff.onma.marketplace.dto.OrderRequest;
import com.domruff.onma.marketplace.dto.OrderResponse;
import com.domruff.onma.marketplace.model.Order;
import com.domruff.onma.marketplace.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    // Create Order
    public void createOrder(OrderRequest orderRequest) {

        // Map request to model
        Order order = Order.builder()
                .orderDate(orderRequest.getOrderDate())
                .status(orderRequest.getStatus())
                .customerId(orderRequest.getCustomerId())
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .totalAmount(orderRequest.getTotalAmount())
                .build();

        // Save order to database
        orderRepository.save(order);
    }

    // Get all Orders
    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapOrderToOrderResponse).toList();
    }

    // Get Order by ID
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return mapOrderToOrderResponse(order);
    }

    // Update Order
    public void updateOrder(Long id, OrderRequest orderRequest) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderDate(orderRequest.getOrderDate());
        order.setStatus(orderRequest.getStatus());
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalAmount(orderRequest.getTotalAmount());

        orderRepository.save(order);
        log.info("Order {} updated successfully", id);
    }

    // Delete Order
    public void deleteOrder(Long id) {

        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            log.info("Order {} deleted successfully", id);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    // Helper method to map Order to OrderResponse
    // Allows for Models to be hidden from the Controller
    public OrderResponse mapOrderToOrderResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalAmount(order.getTotalAmount())
                .build();
    }

}
