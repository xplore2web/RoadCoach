package com.alcord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.CityDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.City;
import com.alcord.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityDao cityDao;
	
	public List<City> getAllCities() throws ProcessFailed {
		return cityDao.getAllCities();
	}

}
