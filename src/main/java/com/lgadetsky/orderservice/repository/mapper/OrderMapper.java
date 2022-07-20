package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insert(Order order);
    Order findById(int id);
    void update(Order order);
    void deleteById(int id);

}
