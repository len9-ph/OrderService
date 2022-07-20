package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderItemMapper {
    void insertOrderItems(List<OrderItem> items);
    List<OrderItem> findItemsByOrderId(int id);
    void update(List<OrderItem> items);
    void delete(List<Integer> itemIds);
}
