package com.lgadetsky.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.lgadetsky.orderservice.exception.RestTemplateErrorHandler;


/**
 * Configuration of Swagger and RestTemplate
 * @author Leonid Gadetsky
 */
@Configuration
public class Config {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Order Micro Service")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("leonidgadetsky@yandex.ru")
                                                .name("Leonid Gadetsky")
                                )
                );
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
    	return builder
    			.errorHandler(new RestTemplateErrorHandler())
    			.build();
    }
}
