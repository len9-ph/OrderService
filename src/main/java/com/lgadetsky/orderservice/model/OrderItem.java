package com.lgadetsky.orderservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Schema(description = "Сущность предмета заказа")
public class OrderItem {
	@XmlTransient
    @Schema(description = "Идентификатор предмета", example = "1000")
    private int id;
	@XmlTransient
    @Schema(description = "Идентификатор заказа", example = "1000")
    private int orderId;
    @XmlValue
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
