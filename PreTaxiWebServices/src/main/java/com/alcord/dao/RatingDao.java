package com.alcord.dao;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Rating;


/**
 *
 * @author Razak
 */

public interface RatingDao {

	/**
     * This method pass id as input and get the {@link Rating} from DAO layer
     *
     * @param id
     * @return {@link Rating}
     * @throws ProcessFailed the process failed
     */
    public Rating getById(UUID id) throws ProcessFailed;    
    /**
     * This method pass rating as input and get the {@link String} from DAO layer.
     *
     * @param Rating
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public UUID save(Rating rating) throws ProcessFailed;
    
    /**
     * This method pass rating as input and deletes the {@link Rating} from DAO layer.
     *
     * @param rating
     * @throws ProcessFailed the process failed
     */
    public void delete(Rating rating) throws ProcessFailed;
    
    /**
     * This method pass id as input and get the {@link Rating} from DAO layer
     *
     * @param id
     * @return {@link Rating}
     * @throws ProcessFailed the process failed
     */
    public List<Rating> getAllRatingByDriverId(UUID driverId) throws ProcessFailed;    
    
   
}
