package com.lgadetsky.orderservice.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lgadetsky.orderservice.model.dto.PatientDto;

@org.springframework.stereotype.Service
public class PatientService implements Service<PatientDto, Integer>{
	
	private static final String URL = "http://localhost:8091/";
	private static final String PATIENT_ID = "patientId/";
	private static final String PATIENT = "patient/";
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public PatientDto create(PatientDto obj) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(URL, obj, PatientDto.class);
	}

	@Override
	public PatientDto findById(Integer id) {
		//RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		try {
			ResponseEntity<PatientDto> response = restTemplate
					.exchange(URL + PATIENT_ID + id, HttpMethod.GET, entity, PatientDto.class);
			return response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public PatientDto findByName(Map<String, Integer> id) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(URL, PatientDto.class, id);
	}

	@Override
	public PatientDto update(PatientDto obj) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL, obj, PatientDto.class);
		return obj;
	}

	@Override
	public void deleteById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(URL, id);
	}
	
}
