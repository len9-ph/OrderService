package com.lgadetsky.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lgadetsky.orderservice.exception.OrderDataBaseEmptyException;
import com.lgadetsky.orderservice.exception.OrderIdAlreadyExistException;
import com.lgadetsky.orderservice.exception.OrderIdNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "default", description = "Main controller")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/{id}")
    @Operation(
            summary = "Request for adding a new Order",
            description = "Creates a new order with parameters are contained in the request body"
    )
    @ApiResponses()
    Order create(@RequestBody Order order) {
        if(orderService.findById(order.getId()) == null){
            return orderService.create(order);
        }else {
            throw new OrderIdAlreadyExistException();
        }
    }

    @GetMapping("/order/{id}")
    @Operation(
            summary = "Получить пользователя по идентификатору"
    )
    Order readById(@PathVariable int id) {
        Order order = orderService.findById(id);
        System.out.print("read");
        if (order == null) {
            throw new OrderIdNotFoundException();
        }
        return order;
    }
    @GetMapping("/order")
    @Operation(
            summary = "Получить всех пользователей"
    )
    List<Order> readAll() {
        if(!orderService.findAll().isEmpty())
            return orderService.findAll();
        else {
            throw new OrderDataBaseEmptyException();
        }

    }

    @PutMapping("/order/{id}")
    @Operation(
            summary = "Обновление пользователя"
    )
    Order update(@PathVariable int id, @RequestBody Order order) {
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/order/{id}")
    @Operation(
            summary = "Удаление пользователя по идентификатору"
    )
    void deleteById(@PathVariable int id) {
        orderService.deleteById(id);
    }

}
