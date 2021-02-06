package com.mahi.service.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mahi.model.Address;
import com.mahi.model.Atm;
import com.mahi.model.GeoLocation;
import com.mahi.model.Hour;
import com.mahi.model.OpeningHour;
import com.mahi.service.AtmLocatorService;
import com.mahi.service.AtmLocatorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AtmLocatorServiceTest {
	
	private AtmLocatorService atmLocatorService;
	
	@BeforeEach
    public void setUp(@Mock RestTemplate restTemplate) {
		atmLocatorService = new AtmLocatorServiceImpl(restTemplate);
		List<Atm> atmsList = new ArrayList<>();
		Atm atm = buildAtmLocation1();
		Atm atm2 = buildAtmLocation2();
		
		atmsList.add(atm);
		atmsList.add(atm2);
		
		Mockito.when(restTemplate.exchange(Mockito.anyString()
		                , Mockito.eq(HttpMethod.GET)
		                , Mockito.nullable(HttpEntity.class)
		                , Mockito.<ParameterizedTypeReference<List<Atm>>>any())
		   ).thenReturn(new ResponseEntity<>(atmsList, HttpStatus.OK));
    }

	
	@Test
	public void testGetAllAtms() {
		List<Atm> allAtms = atmLocatorService.getAllAtms();
		assertEquals(2, allAtms.size());
	}
	
	@Test
	public void testGetAtmsByCity() {
		List<Atm> allAtms = atmLocatorService.getAtmsByCity("Ysselsteyn");
		assertEquals(1, allAtms.size());
	}
	
	private Atm buildAtmLocation1() {
		Atm atm = new Atm();
		Address address = new Address();
		address.setCity("Utrecht");
		
		GeoLocation geoLocation = new GeoLocation();
		geoLocation.setLat("52.101529");
		geoLocation.setLng("5.098962");
		address.setGeoLocation(geoLocation);
		
		address.setHousenumber("279");
		address.setPostalcode("3551 CE");
		address.setStreet("Amsterdamsestraatweg");
		atm.setAddress(address);
		
		atm.setDistance(0);
		atm.setFunctionality("Geld storten en opnemen");
		
		List<OpeningHour> openingHours = new ArrayList<OpeningHour>();
		OpeningHour openingHour = new OpeningHour();
		openingHour.setDayOfWeek(2);
		
		List<Hour> hours = new ArrayList<Hour>();
		Hour hour = new Hour();
		hour.setHourFrom("09:00");
		hour.setHourTo("18:00");
		hours.add(hour);
		openingHour.setHours(hours);
		
		openingHours.add(openingHour);
		atm.setOpeningHours(openingHours);
		atm.setType("GELDMAAT");
		return atm;
	}
	
	private Atm buildAtmLocation2() {
		Atm atm = new Atm();
		Address address = new Address();
		address.setCity("Ysselsteyn");
		
		GeoLocation geoLocation = new GeoLocation();
		geoLocation.setLat("51.489297");
		geoLocation.setLng("5.897521");
		address.setGeoLocation(geoLocation);
		
		address.setHousenumber("2A");
		address.setPostalcode("5813 BC");
		address.setStreet("Puttenweg");
		atm.setAddress(address);
		
		atm.setDistance(0);
		atm.setFunctionality("Geldautomaat");
		
		List<OpeningHour> openingHours = new ArrayList<OpeningHour>();
		OpeningHour openingHour = new OpeningHour();
		openingHour.setDayOfWeek(2);
		
		List<Hour> hours = new ArrayList<Hour>();
		Hour hour = new Hour();
		hour.setHourFrom("07:00");
		hour.setHourTo("23:00");
		hours.add(hour);
		openingHour.setHours(hours);
		
		openingHours.add(openingHour);
		atm.setOpeningHours(openingHours);
		atm.setType("GELDMAAT");
		return atm;
	}
}
