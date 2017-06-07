package com.alcord.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.DriverVehicleMappingDao;
import com.alcord.dao.VehicleDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.model.Vehicle;
import com.alcord.service.VehicleService;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private DriverVehicleMappingDao driverVehicleMappingDao;

	/**
	 * {@inheritDoc}
	 */
	public Vehicle getById(UUID id) throws ProcessFailed {
		return vehicleDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Vehicle vehicle) throws ProcessFailed {
		return vehicleDao.save(vehicle);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Vehicle vehicle) throws ProcessFailed {
		vehicleDao.update(vehicle);

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(UUID id) throws ProcessFailed {
		vehicleDao.getById(id);

	}

	/**
	 * {@inheritDoc}
	 */
	public List<Vehicle> getAllVehicle() throws ProcessFailed {
		
		return vehicleDao.getAllVehicle();
	}

	/**
	 * {@inheritDoc}
	 */
	public void changeVehicle(UUID driverId, UUID vehilceId) throws ProcessFailed {
		
		DriverVehicleMapping driverVehicleMapping = driverVehicleMappingDao.getCurrentVehicleByDriverId(driverId);
		if(driverVehicleMapping != null){
			if(!vehilceId.toString().equalsIgnoreCase(driverVehicleMapping.getFkeyVehicleId().getId().toString())){
				DriverVehicleMapping driverVehicleMappingObject = driverVehicleMappingDao.getDriverVehicleMappingByVehicleId(vehilceId);
				driverVehicleMappingObject.setIsCurrentVehicle(true);
				driverVehicleMappingObject.setUpdatedAt(new Date());
				driverVehicleMappingDao.update(driverVehicleMappingObject);
				
			}
		}
		
		
		
		
	}

}
