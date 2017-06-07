package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.modelmappers.TripDetail;

public interface DriverTripService {
	
	/**
     * 
     * @param tripId
     * @throws ProcessFailed 
     */
	public void acceptTrip(TripDetail tripDetail) throws ProcessFailed;
	
	/**
     * 
     * @param tripId
     * @return list of TripDetail
     * @throws ProcessFailed 
     */
	public List<TripDetail> getAllTrip(String startDate, String endDate, UUID driverId) throws ProcessFailed;
	
	

}
