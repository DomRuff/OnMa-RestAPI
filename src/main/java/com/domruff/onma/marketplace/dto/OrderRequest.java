package com.domruff.onma.marketplace.dto;

import java.time.LocalDateTime;

import com.domruff.onma.marketplace.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private LocalDateTime orderDate;
    private OrderStatus status;
    private long customerId;
    private long productId;
    private int quantity;
    private double totalAmount;
}
