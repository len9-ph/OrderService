package com.lgadetsky.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "A new order has been successfully created"),
    		@ApiResponse(responseCode = "500", description = "Server error")
    })
    ResponseEntity<?> create(@RequestBody Order order) {
    	orderService.create(order);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    @Operation(
            summary = "Get the Order by id",
            description = "Returns object by 'orderId' or returns null"
    )
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "A successful response"),
    		@ApiResponse(responseCode = "404", description = "A resource with requested ID not found"),
    		@ApiResponse(responseCode = "500", description = "Server error")
    })
    ResponseEntity<?> readById(@PathVariable int id) {
    	if (orderService.findById(id) != null)
    		return new ResponseEntity<Order>(orderService.findById(id), HttpStatus.OK);
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/order/{id}")
    @Operation(
            summary = "Request for editing the Order by id",
            description = "Update order by id with parameters are contained in request body"
    )
    @ApiResponses(value = {
    	@ApiResponse(responseCode = "200", description = "Order has been updated succesfully"),
    	@ApiResponse(responseCode = "500", description = "Server error")
    })
    ResponseEntity<?> update(@PathVariable int id, @RequestBody Order order) {
        order.setId(id);
        orderService.update(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    @Operation(
            summary = "Request for removing the Order by id",
            description = "Removes order by id"
    )
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Order has been deleted succesfully"),
        	@ApiResponse(responseCode = "500", description = "Server error")
    })
    ResponseEntity<?> deleteById(@PathVariable int id) {
        orderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
