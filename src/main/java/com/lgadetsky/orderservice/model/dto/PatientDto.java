package com.lgadetsky.orderservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {
	
	private int id;
	
	private String firstName;
	
	private String midName;
	
	private String lastName;

	private int gender;
	
	private String birthday;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	public boolean isValid() {
		return !(firstName.isBlank() && midName.isBlank() && lastName.isBlank() && birthday.isBlank());
	}
}
