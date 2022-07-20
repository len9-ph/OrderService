package com.lgadetsky.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {
    private int id;
    private int orderId;
    private String itemName;
}
