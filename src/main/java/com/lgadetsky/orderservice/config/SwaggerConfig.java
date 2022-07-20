package com.lgadetsky.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
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
}
