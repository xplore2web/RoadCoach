package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.Driver;

public interface DriverDao {
 
	/**
     * This method pass id as input and get the {@link Driver} from DAO layer
     *
     * @param id
     * @return {@link Driver}
     * @throws ProcessFailed the process failed
     */
    public Driver getById(UUID id) throws ProcessFailed;
    
    /**
     * This method pass driver as input and get the {@link Driver} from DAO layer.
     *
     * @param driver
     * @return {@link Driver}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Driver driver) throws ProcessFailed;
    
    /**
     * This method pass driver as input and updates the {@link Driver} from database
     *
     * @param driver
     * @throws ProcessFailed the process failed
     */
    public void update(Driver driver) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Driver} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
    
	/**
     * This method pass id as input and get the {@link Driver} from DAO layer
     *
     * @param id
     * @return {@link Driver}
     * @throws ProcessFailed the process failed
     */
    public Driver getByAccountId(UUID id) throws ProcessFailed;
    
    /**
     * 
     * @param badgeNumber
     * @return {@link Driver}
     * @throws ProcessFailed
     */
    public List<Driver> getByBadgeNumber(String badgeNumber, UUID stateId)throws ProcessFailed;
    /**
     * 
     * @param name
     * @param cityId
     * @return
     * @throws ProcessFailed
     */
    public List<Driver> getByName(String name, UUID stateId) throws ProcessFailed;    
}
