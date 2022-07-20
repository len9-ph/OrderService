package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(Order order);
    Order findById(int id);
    List<Order> findAll();
    void update(Order order);
    void deleteById(int id);

}
