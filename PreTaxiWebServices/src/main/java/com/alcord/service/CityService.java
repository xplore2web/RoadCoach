package com.alcord.service;

import java.util.List;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.City;

public interface CityService {
	
	/**
     * 
     * @return List of City
     * @throws ProcessFailed 
     */
	public List<City> getAllCities() throws ProcessFailed;

}
