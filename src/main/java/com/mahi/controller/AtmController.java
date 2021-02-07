package com.mahi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahi.model.Atm;
import com.mahi.service.AtmLocatorService;

@RestController
@RequestMapping("/atm")
public class AtmController {

	private AtmLocatorService atmLocatorService;
	
	@Autowired
	public AtmController(AtmLocatorService atmLocatorService) {
		this.atmLocatorService = atmLocatorService;
	}

	@GetMapping("/")
	public List<Atm> getAllAtms() throws Exception {
		
		return atmLocatorService.getAllAtms();
	}
	
	@GetMapping("/city/{cityName}")
	public List<Atm> getAtmsByCity(@PathVariable String cityName) throws Exception {
		
		return atmLocatorService.getAtmsByCity(cityName);
	}
}
