package com.lgadetsky.orderservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Сущность заказа")
public class Order {
	@XmlElement
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int id;
	@XmlElement(name = "orderStatusId")
    @Schema(description = "Идентификатор статуса заказа", example = "1")
    private int orderStatusId;
	@XmlElement(name = "customerName")
    @Schema(description = "Имя заказчика", example = "Иван И.И.")
    private String customerName;
	@XmlElement(name = "customerPhone")
    @Schema(description = "Телефон заказчика", example = "+7(952)-634-12-32")
    private String customerPhone;
	@XmlElement(name = "customerComment")
    @Schema(description = "Комментарий заказчика", example = "Перезвоните")
    private String customerComment;
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item", type = OrderItem.class)
    @Schema(description = "Список предметов в заказе")
    private List<OrderItem> orderItems;
}

