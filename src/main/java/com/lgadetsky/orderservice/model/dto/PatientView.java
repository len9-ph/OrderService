package com.lgadetsky.orderservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientView {
	private int id;
	private String name;
	private int gender;
	private String birthday;
	private String phone;
	private String email;
	private String address;
}
