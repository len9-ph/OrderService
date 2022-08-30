package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper responsible for mapping the OrderItem entity
 * @author Leonid Gadetsky
 * @see OrderItem
 */
@Mapper
public interface OrderItemMapper {
    void insertOrderItems(List<OrderItem> items);
    List<OrderItem> findItemsByOrderId(int id);
    void update(OrderItem item);
    void deleteById(int id);
    void deleteByOrderId(int id);
}
