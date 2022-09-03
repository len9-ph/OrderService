package com.lgadetsky.orderservice.model.dto;

import com.lgadetsky.orderservice.model.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderPatient {

	private Order order;

	private PatientDto patient;

}
