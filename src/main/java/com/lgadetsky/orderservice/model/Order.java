package com.lgadetsky.orderservice.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность заказа")
public class Order {
	
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int id;
	
    @Schema(description = "Идентификатор статуса заказа", example = "1")
    private int orderStatusId;
	
    @Schema(description = "Имя заказчика", example = "Иван И.И.")
    private String customerName;
	
    @Schema(description = "Телефон заказчика", example = "+7(952)-634-12-32")
    private String customerPhone;
	
    @Schema(description = "Комментарий заказчика", example = "Перезвоните")
    private String customerComment;
    
    @Schema(description = "Список предметов заказа")
    List<OrderItem> orderItems;
	
}

