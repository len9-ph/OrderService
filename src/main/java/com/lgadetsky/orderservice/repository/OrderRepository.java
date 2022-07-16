package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;

/* TODO
*   Добавить selectALL
*            existById*/

/**
 * Describe all methods for working with db
 */
public interface OrderRepository {
    public Order insertOrder(Order order);
    public Order selectOrder(long id);
    public void updateOrder(Order order);
    public void deleteOrder(long id);
}
