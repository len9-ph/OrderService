//package com.lgadetsky.orderservice.service;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestTemplate;
//
//import com.lgadetsky.orderservice.model.dto.PatientDto;
//
//@org.springframework.stereotype.Service
//public class PatientService implements Service<PatientDto, Map<String, String> >{
//	
//	private final String URI = "";
//	private final String FIRST = "firstName";
//	private final String MID = "middleName";
//	private final String LAST = "lastName";
//	private final String BIRTHDAY = "birthday";
//	
//	@Autowired
//	RestTemplate restTemplate;
//	
//	@Override
//	public PatientDto create(PatientDto obj) {
//		return restTemplate.postForObject(URI, obj, PatientDto.class);
//	}
//
//	@Override
//	public PatientDto findById(Map<String, String> id) {
//		return restTemplate.getForObject(URI, PatientDto.class, id);
//	}
//
//	@Override
//	public PatientDto update(PatientDto obj) {
//		// TODO Auto-generated method stub
//		restTemplate.put(URI, obj, PatientDto.class);
//		return obj;
//	}
//
//	@Override
//	public void deleteById(Map<String, String> id) {
//		// TODO Auto-generated method stub
//		restTemplate.delete(URI, id);
//	}
//	
//}
