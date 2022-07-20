package com.lgadetsky.orderservice.controller;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order){
        return orderService.create(order);
    }

    @GetMapping("/orders")
    public List<Order> readAll(){
        return orderService.readAll();
    }

    @GetMapping("/orders/{id}")
    public Order readById(@PathVariable int id) {
        return orderService.readById(id);
    }

    @PutMapping("/orders/{id}")
    public Order update(@RequestBody Order order, @PathVariable int id) {
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/orders/{id}")
    void delete(@PathVariable long id) {
        orderService.delete(id);
    }


}
