package com.lgadetsky.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.dto.OrderPatient;
import com.lgadetsky.orderservice.service.OrderService;
import com.lgadetsky.orderservice.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "default", description = "Main controller")
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	@Autowired
	private PatientService patientService;

    @PostMapping("/order")
    @Operation(
            summary = "Request for adding a new Order",
            description = "Creates a new order with parameters are contained in the request body"
    )
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "A new order has been successfully created", content = @Content ),
    		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    OrderPatient create(@RequestBody OrderPatient op) {
    	
    	
    	
    	return op;
    }

    @GetMapping("/order/{id}")
    @Operation(
            summary = "Get the Order by id",
            description = "Returns object by 'orderId' or returns null"
    )
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "A successful response", content = @Content),
    		@ApiResponse (responseCode = "404", description = "A resource with requested ID not found", content = @Content),
    		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    OrderPatient readById(@PathVariable int id) {
    	
    	OrderPatient res = new OrderPatient();
    	
    	res.setOrder(orderService.findById(id));
    	
    	
    	res.setPatient(patientService.findById(res.getOrder().getPatientId()));
    	return res;

    }

    @PutMapping("/order/{id}")
    @Operation(
            summary = "Request for editing the Order by id",
            description = "Update order by id with parameters are contained in request body"
    )
    @ApiResponses(value = {
    	@ApiResponse(responseCode = "200", description = "Order has been updated succesfully", content = @Content),
    	@ApiResponse(responseCode = "400", description = "Bar request", content = @Content),
    	@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    Order update(@PathVariable int id, @RequestBody Order order) {
        order.setId(id);
        return orderService.update(order);
    }

    @DeleteMapping("/order/{id}")
    @Operation(
            summary = "Request for removing the Order by id",
            description = "Removes order by id"
    )
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Order has been deleted succesfully", content = @Content),
        	@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    void deleteById(@PathVariable int id) {
        orderService.deleteById(id);
    }

}
