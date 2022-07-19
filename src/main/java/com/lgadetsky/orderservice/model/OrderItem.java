package com.lgadetsky.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderItem implements Serializable {
    private int id;
    private int orderId;
    private String itemName;

    public OrderItem(int orderId, String itemName) {
        this.orderId = orderId;
        this.itemName = itemName;
    }
}
