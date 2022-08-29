package com.lgadetsky.orderservice.model;

import java.util.LinkedList;
import java.util.List;

import com.lgadetsky.orderservice.model.dto.ItemDTO;
import com.lgadetsky.orderservice.model.dto.OrderDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность заказа")
public class Order {
	
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int id;
	
    @Schema(description = "Идентификатор статуса заказа", example = "1")
    private int orderStatusId;
    
    @Schema(description = "Идентификатор пациента", example = "1000")
    private int patientId;
	
    @Schema(description = "Имя заказчика", example = "Иван И.И.")
    private String customerName;
	
    @Schema(description = "Телефон заказчика", example = "+7(952)-634-12-32")
    private String customerPhone;
	
    @Schema(description = "Комментарий заказчика", example = "Перезвоните")
    private String customerComment;
    
    @Schema(description = "Список предметов заказа")
    List<OrderItem> orderItems;
	
    public List<ItemDTO> toItemsDto(){
		List<ItemDTO> items = new LinkedList<>();
		for (OrderItem i : orderItems)
			items.add(ItemDTO.of(i));
		return items;
	}
    
    public static Order of(OrderDTO o) {
    	return new OrderBuilder()
    			.id(o.getId())
    			.orderStatusId(o.getOrderStatusId())
    			.customerName(o.getCustomerName())
    			.customerComment(o.getCustomerComment())
    			.customerPhone(o.getCustomerPhone())
    			.orderItems(o.toOrderItems())
    			.build();
    }
}

