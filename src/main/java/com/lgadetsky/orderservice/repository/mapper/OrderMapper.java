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
    int insert(Order order);
    Order findById(int id);
    int update(Order order);
    int deleteById(int id);
}
