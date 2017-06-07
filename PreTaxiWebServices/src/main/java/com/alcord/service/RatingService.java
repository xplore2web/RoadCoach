package com.alcord.service;

import java.util.List;
import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.Rating;
import com.alcord.modelmappers.RatingDetail;

public interface RatingService {
	
	/**
     * 
     * @param startDate
     * @param endDate
     * @throws ProcessFailed 
     */
	public void submitRating(RatingDetail ratingDetail) throws ProcessFailed;
	
	/**
     * 
     * @param account
     * @return list of RatingDetail
     * @throws ProcessFailed 
     */
	public RatingDetail getRatingById(UUID id) throws ProcessFailed;
	
	/**
     * This method pass driverId as input and get the {@link Rating} from DAO layer
     *
     * @param driverId
     * @return {@link RatingDetail}
     * @throws ProcessFailed the process failed
     */
    public List<RatingDetail> getAllRatingDetailByDriverId(UUID driverId) throws ProcessFailed;    

}
