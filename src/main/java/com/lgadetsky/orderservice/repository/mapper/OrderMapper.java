package com.lgadetsky.orderservice.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lgadetsky.orderservice.model.Order;

/**
 * Маппер отвечающий за маппинг сущности Order
 * @author Leonid Gadetsky
 * @see Order
 */
@Mapper
public interface OrderMapper {
    void insert(Order order);
    Order findById(int id);
    void update(Order order);
    void deleteById(int id);
}
