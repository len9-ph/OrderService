package com.lgadetsky.orderservice.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lgadetsky.orderservice.exception.OrderIdNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.OrderItem;
import com.lgadetsky.orderservice.repository.mapper.OrderItemMapper;
import com.lgadetsky.orderservice.repository.mapper.OrderMapper;


/**
 * @author Leonid Gadetsky
 * Класс реализуюший методы для работы с базой данных
 */
@org.springframework.stereotype.Repository
public class OrderRepository implements Repository{

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    
    public OrderRepository(OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    /**
     * Метод реализуюший добавление нового заказа в базу данных
     * @param order - заказ который необходимо добавить в базу данных
     */
    @Override
    @Transactional
    public Order create(Order order) {
        orderMapper.insert(order);

        List<OrderItem> orderItems = order.getOrderItems();
        if(orderItems != null && !orderItems.isEmpty()) {
            orderItems.forEach(orderItem -> orderItem.setOrderId(order.getId()));
            orderItemMapper.insertOrderItems(orderItems);
        }
        return order;
    }

    /**
     * Метод реализуюший выбор заказа из бд по идентификатору
     * @param id - идентификатор заказа
     */
    @Override
    public Order selectOrderById(int id) {
        return orderMapper.findById(id);
    }

    /**
     * Метод реализующий выбор всех элементов из базы данных
     */
    @Override
    public List<Order> selectAll() {
        return orderMapper.findAll();
    }

    /**
     * Метод реализуюший операцию обновления
     * @param order - заказ который необходимо обновить
     */
    @Override
    @Transactional
    public Order update(int id, Order order) {
        if(orderMapper.findById(id) != null){
            //Order oldOlder = orderMapper.findById(order.getId());

            //orderItemMapper.update(order.getOrderItems());
            orderMapper.update(order, id);
            orderItemMapper.update(order.getOrderItems(), id);

        } else {
            throw new OrderIdNotFoundException();
        }
        return order;
    }

    /**
     * Метод реализуюший операцию удаления
     * @param id - идентификатор заказа
     */
    @Override
    @Transactional
    public void deleteOrderById(int id) {
//    	
//    	List<OrderItem> items = orderMapper.findById(id).getOrderItems();
//    	List<Integer> ids = new LinkedList<Integer>();
//    	
//    	for (OrderItem item : items) {
//    		ids.add(item.getId());
//    	}
//    	
//    	orderItemMapper.delete(ids);
    	orderItemMapper.deleteByOrderId(id);
        orderMapper.deleteById(id);
    }
}
