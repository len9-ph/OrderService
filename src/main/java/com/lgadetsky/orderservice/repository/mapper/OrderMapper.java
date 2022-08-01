package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Маппер отвечающий за маппинг сущности Order
 * @author Leonid Gadetsky
 * @see Order
 */
@Mapper
public interface OrderMapper {
    void insert(Order order);
    Order findById(int id);
    List<Order> findAll();
    void update(Order order);
    void deleteById(int id);
}
