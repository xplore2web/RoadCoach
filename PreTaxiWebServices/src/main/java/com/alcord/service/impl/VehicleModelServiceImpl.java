package com.alcord.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.VehicleModelDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleModel;
import com.alcord.service.VehicleModelService;

@Service
@Transactional
public class VehicleModelServiceImpl implements VehicleModelService {

	@Autowired
	private VehicleModelDao vehicleModelDao;

	/**
	 * {@inheritDoc}
	 */
	public VehicleModel getById(UUID id) throws ProcessFailed {
		return vehicleModelDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(VehicleModel vehicleModel) throws ProcessFailed {
		
			return vehicleModelDao.save(vehicleModel);
		}

	/**
	 * {@inheritDoc}
	 */
	public void update(VehicleModel vehicleModel) throws ProcessFailed {
		vehicleModelDao.update(vehicleModel);

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		vehicleModelDao.getById(id);

	}

	@Override
	public List<VehicleModel> getAllVehicleModel() throws ProcessFailed {
		
		return vehicleModelDao.getAllVehicleModel();
	}

}
