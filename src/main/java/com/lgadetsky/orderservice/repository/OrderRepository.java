package com.lgadetsky.orderservice.repository;

import com.lgadetsky.orderservice.model.Order;

import java.util.List;

/* TODO
*   Добавить selectALL
*            existById*/

/**
 * Describe all methods for working with db
 */
public interface OrderRepository {

    Order insertOrder(Order order);

    Order selectOrder(int id);

    List selectAllOrders();

    //public boolean orderExistById(long id);

    Order updateOrder(Order order);

    void deleteOrder(long id);
}
