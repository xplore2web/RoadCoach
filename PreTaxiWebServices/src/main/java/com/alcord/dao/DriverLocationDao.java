package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverLocation;

public interface DriverLocationDao {
	/**
     * This method pass id as input and get the {@link DriverLocation} from DAO layer
     *
     * @param Id
     * @return {@link DriverLocation}
     * @throws ProcessFailed the process failed
     */
    public DriverLocation getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass driverLocation as input and get the {@link DriverLocation} from DAO layer.
     *
     * @param DriverLocation
     * @return {@link DriverLocation}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverLocation driverLocation) throws ProcessFailed;
    
    /**
     * This method pass DriverLocation as input and updates the {@link DriverLocation} from database
     *
     * @param driverLocation
     * @throws ProcessFailed the process failed
     */
    public void update(DriverLocation driverLocation) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverLocation} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(DriverLocation driverLocation) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link DriverLocation} from DAO layer
     *
     * @param Id
     * @return {@link DriverLocation}
     * @throws ProcessFailed the process failed
     */
    public DriverLocation getDriverCurrentLoactionBydriverId(UUID driverId) throws ProcessFailed;    


}
