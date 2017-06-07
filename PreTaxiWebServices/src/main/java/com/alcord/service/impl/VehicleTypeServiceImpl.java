package com.alcord.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.VehicleTypeDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleType;
import com.alcord.service.VehicleTypeService;
@Service
@Transactional

public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Autowired
	private VehicleTypeDao vehicleTypeDao;
	
	/**
	 * {@inheritDoc}
	 */
	public VehicleType getById(UUID id) throws ProcessFailed {
		
		return vehicleTypeDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(VehicleType vehicleType) throws ProcessFailed {
		
		return vehicleTypeDao.save(vehicleType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(VehicleType vehicleType) throws ProcessFailed {
		vehicleTypeDao.update(vehicleType);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		vehicleTypeDao.delete(id);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VehicleType> getAllVehicleType() throws ProcessFailed {
		
		return vehicleTypeDao.getAllVehicleType();
	}

}
