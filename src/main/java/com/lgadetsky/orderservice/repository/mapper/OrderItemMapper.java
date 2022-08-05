package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Маппер отвечающий за маппинг сущности OrderItem
 * @author Leonid Gadetsky
 * @see OrderItem
 */
@Mapper
public interface OrderItemMapper {
    void insertOrderItems(List<OrderItem> items);
    List<OrderItem> findItemsByOrderId(int id);
    void update(List<OrderItem> items);
    void deleteByOrderId(int id);
}
