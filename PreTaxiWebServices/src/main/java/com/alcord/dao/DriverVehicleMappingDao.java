package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverVehicleMapping;

/**
*
* @author Razak
*/

public interface DriverVehicleMappingDao {
	
	/**
     * This method pass id as input and get the {@link DriverVehicleMapping} from DAO layer
     *
     * @param Id
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public DriverVehicleMapping getById(UUID Id) throws ProcessFailed;
    
    /**
     * This method pass driverVehicleMapping as input and get the {@link DriverVehicleMapping} from DAO layer.
     *
     * @param DriverVehicleMapping
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverVehicleMapping driverVehicleMapping) throws ProcessFailed;
    
    /**
     * This method pass driverVehicleMapping as input and updates the {@link DriverVehicleMapping} from database
     *
     * @param driverVehicleMapping
     * @throws ProcessFailed the process failed
     */
    public void update(DriverVehicleMapping driverVehicleMapping) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverVehicleMapping} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link DriverVehicleMapping} from DAO layer
     *
     * @param Id
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public List<DriverVehicleMapping> getAllDriverVehicleByDriverIdAndVehicleStatus(UUID Id,Boolean approved) throws ProcessFailed;
    /**
     * This method pass id as input and get the {@link DriverVehicleMapping} from DAO layer
     *
     * @param Id
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public DriverVehicleMapping getCurrentVehicleByDriverId(UUID driverId) throws ProcessFailed;
    /**
     * This method pass driverId as input and get the {@link DriverVehicleMapping} from DAO layer
     *
     * @param driverId
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public List<DriverVehicleMapping> getAllVehiclesByDriverId(UUID driverId) throws ProcessFailed;
    /**
     * This method pass id as input and get the {@link DriverVehicleMapping} from DAO layer
     *
     * @param Id
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public List<DriverVehicleMapping> getAllDriverVehicle() throws ProcessFailed;
    /**
     * This method pass id as input and get the {@link DriverVehicleMapping} from DAO layer
     *
     * @param vehicleId
     * @return {@link DriverVehicleMapping}
     * @throws ProcessFailed the process failed
     */
    public DriverVehicleMapping getDriverVehicleMappingByVehicleId(UUID vehicleId) throws ProcessFailed;
    /**
     * 
     * @param id
     * @return {@link list of DriverVehicleMapping}
     * @throws ProcessFailed
     */
    public List<DriverVehicleMapping> getAllDriverVehicleByDriverId(UUID id)throws ProcessFailed;

}

