package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.PassengerEmergencyContact;

public interface PassengerEmergencyContactDao {

	/**
     * This method pass id as input and get the {@link PassengerEmergencyContact} from DAO layer
     *
     * @param Id
     * @return {@link PassengerEmergencyContact}
     * @throws ProcessFailed the process failed
     */
    public PassengerEmergencyContact getById(UUID Id) throws ProcessFailed;    
    
    /**
     * This method pass PassengerEmergencyContact as input and get the {@link PassengerEmergencyContact} from DAO layer.
     *
     * @param PassengerEmergencyContact
     * @return {@link PassengerEmergencyContact}
     * @throws ProcessFailed the process failed
     */
    public UUID save(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed;
    
    /**
     * This method pass PassengerEmergencyContact as input and updates the {@link PassengerEmergencyContact} from database
     *
     * @param PassengerEmergencyContact
     * @throws ProcessFailed the process failed
     */
    public void update(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link PassengerEmergencyContact} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(PassengerEmergencyContact passengerEmergencyContact) throws ProcessFailed;
    
    /**
     * This method pass account id as input and get the {@link PassengerEmergencyContact} from DAO layer
     *
     * @param Id
     * @return {@link PassengerEmergencyContact}
     * @throws ProcessFailed the process failed
     */
    public List<PassengerEmergencyContact> getAllPassengerEmergencyContactByPassengerId(UUID passengerId) throws ProcessFailed; 

}

