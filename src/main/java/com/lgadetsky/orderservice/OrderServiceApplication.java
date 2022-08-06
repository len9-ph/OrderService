package com.lgadetsky.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/* TODO
 * 1) Make right version of repository pattern
 * 2) add dto for order class and used it 
 * 3) repair update function*/

@SpringBootApplication
@ServletComponentScan
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
