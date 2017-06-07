package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.DriverStatusHistory;

public interface DriverStatusHistoryDao {
	
	/**
     * This method pass id as input and get the {@link DriverStatusHistory} from DAO layer
     *
     * @param Id
     * @return {@link DriverStatusHistory}
     * @throws ProcessFailed the process failed
     */
    public DriverStatusHistory getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass driverStatus as input and get the {@link DriverStatusHistory} from DAO layer.
     *
     * @param DriverStatusHistory
     * @return {@link DriverStatusHistory}
     * @throws ProcessFailed the process failed
     */
    public UUID save(DriverStatusHistory driverStatus) throws ProcessFailed;
    
    /**
     * This method pass DriverStatusHistory as input and updates the {@link DriverStatusHistory} from database
     *
     * @param driverStatus
     * @throws ProcessFailed the process failed
     */
    public void update(DriverStatusHistory driverStatus) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link DriverStatusHistory} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(DriverStatusHistory driverStatus) throws ProcessFailed;

}
