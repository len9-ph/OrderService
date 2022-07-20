package com.lgadetsky.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сущность предмета заказа")
public class OrderItem {
    @Schema(description = "Идентификатор предмета", example = "1000")
    private int id;
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int orderId;
    @Schema(description = "Название предмета", example = "Order item #1")
    private String itemName;
}
