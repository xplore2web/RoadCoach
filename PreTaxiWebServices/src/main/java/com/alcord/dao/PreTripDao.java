package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.PreTrip;

public interface PreTripDao {
	
	/**
     * This method pass id as input and get the {@link PreTrip} from DAO layer
     *
     * @param Id
     * @return {@link PreTrip}
     * @throws ProcessFailed the process failed
     */
    public PreTrip getById(UUID id) throws ProcessFailed;    
    
    /**
     * This method pass preTrip as input and get the {@link PreTrip} from DAO layer.
     *
     * @param PreTrip
     * @return {@link PreTrip}
     * @throws ProcessFailed the process failed
     */
    public UUID save(PreTrip preTrip) throws ProcessFailed;
    
    /**
     * This method pass PreTrip as input and updates the {@link PreTrip} from database
     *
     * @param preTrip
     * @throws ProcessFailed the process failed
     */
    public void update(PreTrip preTrip) throws ProcessFailed;
    
    /**
     * This method pass id as input and deletes the {@link PreTrip} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(PreTrip preTrip) throws ProcessFailed;
    /**
     * This method pass id as input and get the {@link PreTrip} from DAO layer
     *
     * @param Id
     * @return {@link PreTrip}
     * @throws ProcessFailed the process failed
     */
    public List<PreTrip> getAllPreTripByTripId(UUID id) throws ProcessFailed;    

}
