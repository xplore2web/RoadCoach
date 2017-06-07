package com.alcord.service;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Trip;
import com.alcord.modelmappers.TripDetail;

public interface PassengerTripService {
	
	/**
     * 
     * @param tripDetail
     * @throws ProcessFailed 
     */
	void bookTrip(TripDetail tripDetail) throws ProcessFailed;
	
	/**
     * This method pass id as input and deletes the {@link Trip} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void alertEmergencyContact(UUID accountId) throws ProcessFailed;
	
	

}
