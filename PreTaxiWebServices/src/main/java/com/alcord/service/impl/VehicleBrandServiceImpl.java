package com.alcord.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.VehicleBrandDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.VehicleBrand;
import com.alcord.service.VehicleBrandService;

@Service
@Transactional
public class VehicleBrandServiceImpl implements VehicleBrandService {
	
	@Autowired
	private VehicleBrandDao vehicleBrandDao;

	/**
	 * {@inheritDoc}
	 */
	public VehicleBrand getById(UUID id) throws ProcessFailed {
		return vehicleBrandDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(VehicleBrand vehicleBrand) throws ProcessFailed {
		return vehicleBrandDao.save(vehicleBrand);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(VehicleBrand vehicleBrand) throws ProcessFailed {
		vehicleBrandDao.update(vehicleBrand);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		vehicleBrandDao.delete(id);
		
	}

	@Override
	public List<VehicleBrand> getAllVehicleBrand() throws ProcessFailed {
		
		return  vehicleBrandDao.getAllVehicleBrand();
	}

}
