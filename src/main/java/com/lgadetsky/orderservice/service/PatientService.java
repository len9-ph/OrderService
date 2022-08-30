package com.lgadetsky.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lgadetsky.orderservice.config.Config;
import com.lgadetsky.orderservice.model.dto.PatientDto;

/**
 * Service class that work with Patient Service
 * Service use {@link RestTemplate} to send requests
 * RestTemplate config is in {@link Config}
 * 
 * @author Leonid Gadetsky
 * @see RestTemplate
 * @see Config
 */
@org.springframework.stereotype.Service
public class PatientService {
	private static final String URL = "http://localhost:8091/";
	private static final String PATIENT_ID = "patientId/";
	private static final String PATIENT = "patient/";
	private static final String BR = "/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public PatientDto create(PatientDto obj) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PatientDto> entity = new HttpEntity<>(obj, headers); 
		
		ResponseEntity<PatientDto> response = restTemplate.postForEntity(URL
				+ PATIENT, entity, PatientDto.class);
		return response.getBody();
	}

	public PatientDto findById(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<PatientDto> response = restTemplate
				.exchange(URL + PATIENT_ID + id, HttpMethod.GET, entity, PatientDto.class);
		return response.getBody();
	}
	
	public PatientDto findByName(String first, String mid, String last, String birthday) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<PatientDto> response = restTemplate
				.exchange(URL + PATIENT + first + BR +  mid + BR + last + BR + birthday, HttpMethod.GET, entity, PatientDto.class);
		return response.getBody();
	}

	public PatientDto update(PatientDto obj) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PatientDto> requestBody = new HttpEntity<>(obj, headers);
		
		 restTemplate.put(URL + PATIENT + obj.getId(), requestBody);
		 return obj;
	}
}
