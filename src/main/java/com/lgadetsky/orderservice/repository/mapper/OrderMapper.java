package com.lgadetsky.orderservice.repository.mapper;

import com.lgadetsky.orderservice.model.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderMapper {
    //Вставить заказ
    void insertOrder(Order order);
    //Найти пользователя по id
    Order findOrderById(int id);
    //Найти всех пользователей
    List<Order> findAllOrders();
    //Обновить пользователя
    void update(Order order);
    //Удалить пользователя по id
    void delete(long id);
}
