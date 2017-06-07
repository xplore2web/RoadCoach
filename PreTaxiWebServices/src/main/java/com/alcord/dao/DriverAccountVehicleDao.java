package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.view.DriverAccountVehicle;

public interface DriverAccountVehicleDao {
	
	/**
     * This method pass id as input and get the {@link DriverAccountVehicle} from DAO layer
     *
     * @param id
     * @return {@link DriverAccountVehicle}
     * @throws ProcessFailed the process failed
     */
    public DriverAccountVehicle getDriverAccountVehicleByDriverId(UUID driverId) throws ProcessFailed;

}
