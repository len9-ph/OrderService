package com.lgadetsky.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgadetsky.orderservice.exception.RestTemplateException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.dto.OrderPatient;
import com.lgadetsky.orderservice.model.dto.PatientDto;
import com.lgadetsky.orderservice.service.OrderService;
import com.lgadetsky.orderservice.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Main controller for order service
 * Implements methods to work with services
 *  
 * @author Leonid Gadetsky
 * @see OrderService
 * @see PatientService
 */
@RestController
@RequestMapping("order")
@Tag(name = "default", description = "Main controller")
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	Order create(@RequestBody Order order) {
		return orderService.create(order);
	}
	
	@GetMapping("/{id}")
	Order readById(@PathVariable Integer id) {
		return orderService.findById(id);
	}
	
	@PutMapping("/{id}")
	Order update(@PathVariable Integer id, @RequestBody Order order) {
		order.setId(id);
		return orderService.update(order);
	}	
	
    @PostMapping("/api")
    @Operation(
            summary = "Request for adding a new order with patient",
            description = "Creates a new order with parameters are contained in the request body"
    )
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "A new order and patient has been successfully created", content = @Content ),
    		@ApiResponse(responseCode = "400", description = "You submitted an invalid request", content = @Content),
    		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    OrderPatient create(@RequestBody OrderPatient op) {
    	PatientDto patient = op.getPatient();
    	Order order = op.getOrder();
    	String first = patient.getFirstName();
    	String middle = patient.getMiddleName();
    	String last = patient.getLastName();
    	String birth = patient.getBirthday();
    	try {
    		// Case when patient exist -> create order and set patientId in order
    		PatientDto dbPatient = patientService.findByName(first, middle, last, birth);
    		order.setPatientId(dbPatient.getId());
    		Order newOrder = orderService.create(order);
    		
    		op.setOrder(newOrder);
    		op.setPatient(dbPatient);
    		return op;
    	} catch(RestTemplateException ex) {
    		// Case when findByName send exception that means patient doesnt exist -> 
    		// -> create new patient with new order and set patientId in order 
    		PatientDto newPatient = patientService.create(patient);
    		System.out.println("patient : " + newPatient.toString());
    		order.setPatientId(newPatient.getId());
    		Order newOrder = orderService.create(order);
    		
    		op.setOrder(newOrder);
    		op.setPatient(newPatient);
    		return op;
    	}
    }

    @GetMapping("/api/{id}")
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
    
    @Operation(
    		summary = "Get the Patient by id",
    		description = "Returns object by id or returns null")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "A successful response", content = @Content),
    		@ApiResponse (responseCode = "404", description = "A resource with requested ID not found", content = @Content),
    		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    @GetMapping("/patient/{id}")
    PatientDto readPatientById(@PathVariable int id) {
    	return patientService.findById(id);
    }

    @PutMapping("/api/{id}")
    @Operation(
            summary = "Request for editing the Order by id",
            description = "Update order by id with parameters are contained in request body"
    )
    @ApiResponses(value = {
    	@ApiResponse(responseCode = "200", description = "Order has been updated succesfully", content = @Content),
    	@ApiResponse(responseCode = "400", description = "You submitted an invalid request", content = @Content),
    	@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })
    OrderPatient update(@PathVariable int id, @RequestBody OrderPatient op) {
        Order order = op.getOrder(); 
    	order.setId(id);
    	
        PatientDto patient = op.getPatient();
        patientService.update(patient);
        orderService.update(order);
        return op;
    }

    @DeleteMapping("/api/{id}")
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
