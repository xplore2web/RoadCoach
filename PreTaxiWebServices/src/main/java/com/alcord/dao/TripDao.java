package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.AvailableDriverDetails;
import com.alcord.model.Trip;


/**
 *
 * @author Razak
 */

public interface TripDao {

	/**
     * This method pass id as input and get the {@link Trip} from DAO layer
     *
     * @param id
     * @return {@link Trip}
     * @throws ProcessFailed the process failed
     */
    public Trip getById(UUID id) throws ProcessFailed;    
	/**
     * This method pass tripId as input and get the {@link Trip} from DAO layer
     *
     * @param tripId
     * @return {@link Trip}
     * @throws ProcessFailed the process failed
     */
    public Trip getByTripId(UUID tripId) throws ProcessFailed;  
    /**
     * This method pass trip as input and get the {@link String} from DAO layer.
     *
     * @param Trip
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Trip trip) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link Trip} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Trip trip) throws ProcessFailed;
    
    /**
     * This method pass trip as input and updates the {@link Trip} from database
     *
     * @param trip
     * @throws ProcessFailed the process failed
     */
    public void update(Trip trip) throws ProcessFailed;
    
    /**
     * This method get passenger latitude,longitude and radius as input and returns the record of all available drivers in passenger area {@link AvailableDriverDetails} from database
     *
     * @param passengerLatitude
     * @param passengerLongitude
     * @param radius
     * @throws ProcessFailed the process failed
     */
   
    public List<AvailableDriverDetails> getAllDriversInPassengerArea(Double passengerLatitude, Double passengerLongitude,Integer radius) throws ProcessFailed;
    
    public List<AvailableDriverDetails> getAllAvailableDriverDetails()throws ProcessFailed;
    
    /**
	 * This get the {@link Trip} from DAO
	 * layer
	 *
	 * @return {@link Trip}
	 * @throws ProcessFailed
	 *             the process failed
	 */
	public List<Trip> getAllTrip() throws ProcessFailed;
}
