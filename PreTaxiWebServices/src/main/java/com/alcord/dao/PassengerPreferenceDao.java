package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.PassengerPreference;

public interface PassengerPreferenceDao {
	
	/**
     * This method pass id as input and get the {@link PassengerPreference} from DAO layer
     *
     * @param id
     * @return {@link PassengerPreference}
     * @throws ProcessFailed the process failed
     */
    public PassengerPreference getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass passengerPreference as input and get the {@link PassengerPreference} from DAO layer.
     *
     * @param passengerPreference
     * @return {@link PassengerPreference}
     * @throws ProcessFailed the process failed
     */
    public UUID save(PassengerPreference passengerPreference) throws ProcessFailed;
    
    /**
     * This method pass PassengerPreference as input and updates the {@link PassengerPreference} from database
     *
     * @param passengerPreference
     * @throws ProcessFailed the process failed
     */
    public void update(PassengerPreference passengerPreference) throws ProcessFailed;
    
    /**
     * This method pass passengerPreference as input and deletes the {@link PassengerPreference} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(PassengerPreference passengerPreference) throws ProcessFailed;
	
    /**
     * This method pass id as input and get the {@link PassengerPreference} from DAO layer
     *
     * @param id
     * @return {@link PassengerPreference}
     * @throws ProcessFailed the process failed
     */
    public List<PassengerPreference> getPassengerPreferenceByPassengerId(UUID id) throws ProcessFailed;  

}
