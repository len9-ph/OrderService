package com.lgadetsky.orderservice.service;

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
	private static final String FIRST_NAME = "firstName";
	private static final String MID_NAME = "midName";
	private static final String LAST_NAME = "lastName";
	private static final String BIRTHDAY = "birthday";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public PatientDto create(PatientDto obj) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PatientDto> entity = new HttpEntity<>(obj, headers); 
		
		try {
			ResponseEntity<PatientDto> response = restTemplate.postForEntity(URL
					+ PATIENT, entity, PatientDto.class);
			return response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public PatientDto findById(Integer id) {
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
	
	public PatientDto findByName(String first, String mid, String last, String birthday) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + PATIENT)
				.queryParam(FIRST_NAME, first)
				.queryParam(MID_NAME, mid)
				.queryParam(LAST_NAME, last)
				.queryParam(BIRTHDAY, birthday);
		try {
			ResponseEntity<PatientDto> response = restTemplate
					.exchange(builder.toUriString(), HttpMethod.GET, entity, PatientDto.class);
			return response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public PatientDto update(PatientDto obj) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PatientDto> requestBody = new HttpEntity<>(obj, headers);
		
		try {
			 restTemplate.put(URL + PATIENT, requestBody);
			 return obj;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteById(Integer id) {
	}
	
}
