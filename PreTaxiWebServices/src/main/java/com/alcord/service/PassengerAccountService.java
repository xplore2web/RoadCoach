package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.model.Passenger;
import com.alcord.modelmappers.PassengerPreferences;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.modelmappers.EmergencyContactDetail;
import com.alcord.modelmappers.PassengerDetail;
import com.alcord.modelmappers.PassengerPreferenceDetail;

public interface PassengerAccountService {
   
	 /**
     * This method pass UserDetail as input and updates the {@link AccountDetail} from DAO layer.
     *
     * @param AccountDetail
     * @throws ProcessFailed the process failed
     */
    public void updateProfile(PassengerDetail passengerDetail) throws ProcessFailed;
    
    /**
	 * This method retrieves the UserDetail of {@link AccountDetail} from DAO
	 * layer.
	 * @param id
	 * @return {@link AccountDetail}
	 * @throws ProcessFailed
	 * the process failed
	 */
	public AccountDetail getPassengerProfileDetail(UUID id) throws ProcessFailed;
	
	
    
    /**
     * This method pass emergencyContactDetail as input and save the {@link EmergencyContactDetail} from DAO layer.
     *
     * @param emergencyContactDetail
     * @throws ProcessFailed the process failed
     */
    public void addEmergencyContact(EmergencyContactDetail emergencyContactDetail) throws ProcessFailed;
    
    /**
     * This method pass emergencyContactDetail as input and updates the {@link EmergencyContactDetail} from DAO layer.
     *
     * @param emergencyContactDetail
     * @throws ProcessFailed the process failed
     */
    public void updateEmergencyContact(EmergencyContactDetail emergencyContactDetail) throws ProcessFailed;
    
    /**
     * This method pass id as input and delete the {@link EmergencyContactDetail} from DAO layer.
     *
     * @param emergencyContactDetail
     * @throws ProcessFailed the process failed
     */
    public void deleteEmergencyContact(UUID id) throws ProcessFailed;
    
    /**
     * This method pass emergencyContactDetail as input and passengerId the {@link EmergencyContactDetail} from DAO layer.
     *
     * @param emergencyContactDetail
     * @throws ProcessFailed the process failed
     */
    public List<EmergencyContactDetail> getAllEmergencyContactByPassengerId(UUID passengerId) throws ProcessFailed;
    
    /**
     * This method pass account
     *  as input and get the {@link PassengerPreferences} from DAO layer
     *
     * @return {@link PassengerPreferences}
     * @throws ProcessFailed the process failed
     */
	public List<PassengerPreferenceDetail> getAllPassengerPreferenceDetailByPassengerId(UUID passengerId) throws ProcessFailed;
	
	/**
	 * This method pass account as input and get the {@link Passenger} from DAO
	 * layer
	 *
	 * @return {@link Passenger}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public Passenger getPassengerProfile(String passengerId) throws ProcessFailed;

	/**
     * This method pass id as input and get the {@link Passenger} from DAO layer
     *
     * @param id
     * @return {@link Passenger}
     * @throws ProcessFailed the process failed
     */
	public Passenger getByAccountId(UUID id) throws ProcessFailed;

	public PassengerDetail parsePassengerToPassengerDetail(Passenger passenger);
	
	/**
     * This method pass passengerPreferenceDetail
     *  as input and save the {@link PassengerPreferenceDetail} from DAO layer
     *
     * @throws ProcessFailed the process failed
     */
	public void addPreferences(PassengerPreferenceDetail passengerPreferenceDetail) throws ProcessFailed;
	/**
     * This method pass passengerPreferenceDetail
     *  as input and update the {@link PassengerPreferenceDetail} from DAO layer
     *
     * @throws ProcessFailed the process failed
     */
	public void updatePreferences(PassengerPreferenceDetail passengerPreferenceDetail) throws ProcessFailed;
}
