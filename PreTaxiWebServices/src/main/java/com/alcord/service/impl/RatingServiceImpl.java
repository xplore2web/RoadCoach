package com.alcord.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.RatingDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Rating;
import com.alcord.model.Trip;
import com.alcord.modelmappers.RatingDetail;
import com.alcord.service.RatingService;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingDao ratingDao;

	/**
	 * {@inheritDoc}
	 */
	public void submitRating(RatingDetail ratingDetail) throws ProcessFailed {
		Rating rating = new Rating();
		Trip trip = new Trip();
		trip.setId(UUID.fromString(ratingDetail.getId()));
		rating.setFkeyTripId(trip);
		rating.setStars(ratingDetail.getStars());
		rating.setRatingComment(ratingDetail.getComment());
		rating.setCreatedAt(new Date());
		rating.setIsIssues(ratingDetail.getIsIssues());
		ratingDao.save(rating);

	}

	/**
	 * {@inheritDoc}
	 */
	public RatingDetail getRatingById(UUID id) throws ProcessFailed {
		Rating rating = ratingDao.getById(id);
		RatingDetail ratingDetail = parseRatingToRatingDetail(rating);
		return ratingDetail;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RatingDetail> getAllRatingDetailByDriverId(UUID driverId) throws ProcessFailed {
		List<Rating> ratingList = ratingDao.getAllRatingByDriverId(driverId);
		List<RatingDetail> ratingDetailList = new ArrayList<>();
		for (Rating rating : ratingList) {
			RatingDetail ratingDetail = new RatingDetail();
			ratingDetail = parseRatingToRatingDetail(rating);
			ratingDetailList.add(ratingDetail);
		}
		return ratingDetailList;
	}

	private RatingDetail parseRatingToRatingDetail(Rating rating) throws ProcessFailed {
		RatingDetail ratingDetail = new RatingDetail();
		ratingDetail.setId(rating.getId().toString());
		ratingDetail.setComment(rating.getRatingBy());
		ratingDetail.setStars(rating.getStars());
		ratingDetail.setIsIssues(rating.getIsIssues());
		ratingDetail.setTripId(rating.getFkeyTripId().getId().toString());
		ratingDetail.setRatingBy(rating.getRatingBy());

		return ratingDetail;
	}

}
