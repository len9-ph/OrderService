package com.lgadetsky.orderservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Сущность предмета заказа")
public class OrderItem {
    @Schema(description = "Идентификатор предмета", example = "1000")
    private int id;
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int orderId;
    @XmlElement(name = "item")
    @Schema(description = "Название предмета", example = "Order item #1")
    private String itemName;
}
