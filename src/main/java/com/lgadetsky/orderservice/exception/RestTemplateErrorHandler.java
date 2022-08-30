package com.lgadetsky.orderservice.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * Class that extends {@link DefaultResponseErrorHandler}
 * Class handle errors that seding from another service
 * 
 * TODO unmarshall response and get api from it
 * 
 * @author Leonid Gadetsky
 * @see DefaultResponseErrorHandler
 */
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler{
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
				String errorMessage = reader.lines().collect(Collectors.joining(""));
	 			
				throw new RestTemplateException(DownstreamApi.PATIENT_API, response.getStatusCode(), errorMessage);
			}
		}
	}
}
