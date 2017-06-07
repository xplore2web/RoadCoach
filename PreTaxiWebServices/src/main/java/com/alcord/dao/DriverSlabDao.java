package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverSlab;

public interface DriverSlabDao {
	
	/**
     * This method pass id as input and get the {@link DriverSlab} from DAO layer
     *
     * @param Id
     * @return {@link DriverSlab}
     * @throws ProcessFailed the process failed
     */
    public DriverSlab getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass driverSlab as input and get the {@link DriverSlab} from DAO layer.
     *
     * @param DriverSlab
     * @return {@link DriverSlab}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverSlab driverSlab) throws ProcessFailed;
    
    /**
     * This method pass DriverSlab as input and updates the {@link DriverSlab} from database
     *
     * @param driverSlab
     * @throws ProcessFailed the process failed
     */
    public void update(DriverSlab driverSlab) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverSlab} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(DriverSlab driverSlab) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link DriverSlab} from DAO layer
     *
     * @param Id
     * @return {@link DriverSlab}
     * @throws ProcessFailed the process failed
     */
    public DriverSlab getDriverCurrentSlabBydriverId(UUID driverId) throws ProcessFailed;    


}
