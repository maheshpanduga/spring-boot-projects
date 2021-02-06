package com.mahi.service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mahi.model.Atm;

@Service
public class AtmLocatorServiceImpl implements AtmLocatorService {
	private RestTemplate restTemplate;
	private static final String serviceUrl = "https://www.ing.nl/api/locator/atms/";
	
	@Autowired
	public AtmLocatorServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Atm> getAllAtms() {
		return getAtmLocations();
	}

	private List<Atm> getAtmLocations() {
		
		String json = restTemplate.exchange(serviceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, new Object[] {}).getBody();
		String[] rows = json.split("\n");
		
		return restTemplate.exchange(serviceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Atm>>() {}, new Object[] {}).getBody();
	}

	@Override
	public List<Atm> getAtmsByCity(String cityName) {
		List<Atm> atmsList = getAtmLocations();
		Predicate<Atm> cityMatched = e -> Objects.nonNull(e.getAddress()) && Objects.nonNull(e.getAddress().getCity()) && e.getAddress().getCity().toLowerCase().contains(cityName.toLowerCase());
		return atmsList.stream().filter(cityMatched).collect(Collectors.toList());
	}

}
