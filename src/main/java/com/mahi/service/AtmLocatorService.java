package com.mahi.service;

import java.util.List;

import com.mahi.model.Atm;

public interface AtmLocatorService {
	public List<Atm> getAllAtms();
	public List<Atm> getAtmsByCity(String cityName);
}
