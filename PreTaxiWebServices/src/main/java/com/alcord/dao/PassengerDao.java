package com.alcord.dao;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Passenger;

public interface PassengerDao {
	
	/**
     * This method pass id as input and get the {@link Passenger} from DAO layer
     *
     * @param id
     * @return {@link Passenger}
     * @throws ProcessFailed the process failed
     */
    public Passenger getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass passenger as input and get the {@link Passenger} from DAO layer.
     *
     * @param passenger
     * @return {@link Passenger}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Passenger passenger) throws ProcessFailed;
    
    /**
     * This method pass Passenger as input and updates the {@link Passenger} from database
     *
     * @param passenger
     * @throws ProcessFailed the process failed
     */
    public void update(Passenger passenger) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Passenger} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(UUID id) throws ProcessFailed;
	
    /**
     * This method pass id as input and get the {@link Passenger} from DAO layer
     *
     * @param id
     * @return {@link Passenger}
     * @throws ProcessFailed the process failed
     */
    public Passenger getByAccountId(UUID id) throws ProcessFailed;   
    
}
