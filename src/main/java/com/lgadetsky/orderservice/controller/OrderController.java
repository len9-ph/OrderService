package com.lgadetsky.orderservice.controller;

import com.lgadetsky.orderservice.exception.OrderDataBaseEmptyException;
import com.lgadetsky.orderservice.exception.OrderIdAlreadyExistException;
import com.lgadetsky.orderservice.exception.OrderIdNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "OrderController", description = "Главный контроллер приложения")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/{id}")
    @Operation(
            summary = "Создание нового пользователя"
    )
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
