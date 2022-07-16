package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    //Вставить список предметов
    void insertOrderItems(List<OrderItem> orderItems);
    //Найти предметы по номеру заказа
    List<OrderItem> findByOrderId(long id);
    //Обновить список предметов
    void update(List<OrderItem> orderItems);
    //Удалить предметы по id
    void delete(List<Integer> idItems);
}
