package com.alcord.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.VehicleColourDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleColour;
import com.alcord.service.VehicleColourService;

@Service
@Transactional
public class VehicleColourServiceImpl implements VehicleColourService {

	@Autowired
	private VehicleColourDao vehicleColourDao;

	/**
	 * {@inheritDoc}
	 */
	public VehicleColour getById(UUID id) throws ProcessFailed {
		return vehicleColourDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(VehicleColour vehicleColour) throws ProcessFailed {
		return vehicleColourDao.save(vehicleColour);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(VehicleColour vehicleColour) throws ProcessFailed {
		vehicleColourDao.update(vehicleColour);

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		vehicleColourDao.delete(id);

	}

	public List<VehicleColour> getAllVehicleColour() throws ProcessFailed {
		return vehicleColourDao.getAllVehicleColour();
	}

}
