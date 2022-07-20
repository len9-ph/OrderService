package com.lgadetsky.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Order implements Serializable {
    private int id;
    private int orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;
    private List<OrderItem> orderItems;

    public Order(int orderStatusId, String customerName, String customerPhone, String customerComment, List<OrderItem> orderItems) {
        this.orderStatusId = orderStatusId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerComment = customerComment;
        this.orderItems = orderItems;
    }
}
