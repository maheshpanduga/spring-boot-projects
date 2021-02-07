package com.mahi.service;

import java.util.List;

import com.mahi.model.Atm;

public interface AtmLocatorService {
	public List<Atm> getAllAtms() throws Exception;
	public List<Atm> getAtmsByCity(String cityName) throws Exception;
}
