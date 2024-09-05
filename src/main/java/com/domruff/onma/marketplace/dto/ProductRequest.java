package com.domruff.onma.marketplace.dto;

import com.domruff.onma.marketplace.model.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private long id;
    private String name;
    private String description;
    private ProductCategory category;
    private double price;
    private int quantity;
    private long sellerId;

}
