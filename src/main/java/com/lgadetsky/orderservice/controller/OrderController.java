package com.lgadetsky.orderservice.controller;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/{id}")
    Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @GetMapping("/order/{id}")
    Order readById(@PathVariable int id) {
        Order order = orderService.findById(id);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return order;
    }

    @PutMapping("/order/{id}")
    Order update(@PathVariable int id, @RequestBody Order order) {
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/order/{id}")
    void deleteById(@PathVariable int id) {
        orderService.deleteById(id);
    }

}
