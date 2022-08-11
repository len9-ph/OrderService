package com.lgadetsky.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность предмета заказа")
public class OrderItem {
    @Schema(description = "Идентификатор предмета", example = "1000")
    private int id;
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int orderId;
    @Schema(description = "Название предмета", example = "Order item #1")
    private String itemName;
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null)
    		return false;
    	
    	if(obj.getClass() != this.getClass())
    		return false;
    	
    	final OrderItem item = (OrderItem) obj;
    	
    	if (this.id != item.id)
    		return false;
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	int hash = 0;
    	return hash;
    }
}
