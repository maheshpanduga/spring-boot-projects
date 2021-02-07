package com.mahi.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahi.model.Atm;

@Service
public class AtmLocatorServiceImpl implements AtmLocatorService {
	private RestTemplate restTemplate;
	private static final String SERVICE_URL = "https://www.ing.nl/api/locator/atms/";
	
	@Autowired
	public AtmLocatorServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Atm> getAllAtms() throws Exception {
		return getAtmLocations();
	}

	// Did a work around to get the list of locations as the given service not returning the proper json data
	private List<Atm> getAtmLocations() throws Exception {
		RequestCallback requestCallback = null;
		ResponseExtractor<List<Atm>> responseExtractor = (response) -> {
	    	String json = IOUtils.toString(response.getBody(), StandardCharsets.UTF_8.name());
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	String[] rows = json.split("\n");
	        return objectMapper.readValue(rows[1], new TypeReference<List<Atm>>(){});
		};
		return restTemplate.execute(SERVICE_URL, HttpMethod.GET, requestCallback, responseExtractor);
	}

	@Override
	public List<Atm> getAtmsByCity(String cityName) throws Exception {
		List<Atm> atmsList = getAtmLocations();
		Predicate<Atm> cityMatched = e -> Objects.nonNull(e.getAddress()) && Objects.nonNull(e.getAddress().getCity()) && e.getAddress().getCity().toLowerCase().contains(cityName.toLowerCase());
		return atmsList.stream().filter(cityMatched).collect(Collectors.toList());
	}
}
