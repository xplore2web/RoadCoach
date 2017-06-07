package com.alcord.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.TripDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.google.GoogleMapsService;
import com.alcord.model.AvailableDriverDetails;
import com.alcord.model.Trip;
import com.alcord.modelmappers.AvailableDriversDrivingETDetail;
import com.alcord.modelmappers.RideAvailableDriversDetails;
import com.alcord.service.TripService;
import com.alcord.utility.StringUtility;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
@Service
@Transactional
public class TripServiceImpl implements TripService {

	@Autowired
	private TripDao tripDao;
	@Autowired
	private GoogleMapsService googleMapsService;

	/**
	 * {@inheritDoc}
	 */
	public Trip getById(UUID id) throws ProcessFailed {
		return tripDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public UUID save(Trip trip) throws ProcessFailed {
		return tripDao.save(trip);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Trip trip) throws ProcessFailed {
		tripDao.delete(trip);

	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Trip trip) throws ProcessFailed {
		tripDao.update(trip);

	}
	/**
	 * {@inheritDoc}
	 */
	public List<AvailableDriverDetails> getAllDriversInPassengerArea(Double passengerLatitude, Double passengerLongitude, Integer radius)
			throws ProcessFailed {
		return tripDao.getAllDriversInPassengerArea(passengerLatitude, passengerLongitude, radius);
	}

	@Override
	public RideAvailableDriversDetails getDrivingETADetails(LatLng tripSource, LatLng tripDestination,
			List<AvailableDriverDetails> availableDriverDetails) {
		RideAvailableDriversDetails rideAvailableDriversDetails = new RideAvailableDriversDetails();
		List<AvailableDriversDrivingETDetail> availableDriversDrivingETDetails = new ArrayList<AvailableDriversDrivingETDetail>();
		String origins[] = new String[availableDriverDetails.size() + 1];
		String destinations[] = new String[availableDriverDetails.size() + 1];
		origins[0] = StringUtility.convertLatLng(tripSource);
		int i = 1;
		for (AvailableDriverDetails availableDriverDetail: availableDriverDetails) {
			origins[i] = StringUtility.convertLatLng(tripSource);
			destinations[i] = StringUtility.convertLatLng(availableDriverDetail.getLocationLatitude(),
					availableDriverDetail.getLocationLongitude());
			i++;
		}

		try {
			Distance tripDistance = null;
			Duration tripDuration = null;
			Duration tripDurationInTraffic = null;
			DistanceMatrix distanceMatrix = googleMapsService.getDistanceMatrix(origins, destinations, DateTime.now());
			DistanceMatrixRow distanceMatrixRow = distanceMatrix.rows[0];
			for (int k = 0; k < distanceMatrixRow.elements.length; k++) {
				DistanceMatrixElement distanceMatrixElement = distanceMatrixRow.elements[k];
				if (distanceMatrixElement.status == DistanceMatrixElementStatus.OK) {
					tripDistance = distanceMatrixElement.distance;
					tripDuration = distanceMatrixElement.duration;
					tripDurationInTraffic = distanceMatrixElement.durationInTraffic;
				}
			}

			for (int j = 1; j < distanceMatrix.rows.length; j++) {
				distanceMatrixRow = distanceMatrix.rows[j];
				for (int k = 0; k < distanceMatrixRow.elements.length; k++) {
					DistanceMatrixElement distanceMatrixElement = distanceMatrixRow.elements[k];
					if (distanceMatrixElement.status == DistanceMatrixElementStatus.OK) {
						AvailableDriversDrivingETDetail availableDriversDrivingETDetail = new AvailableDriversDrivingETDetail(
								availableDriverDetails.get(j - 1));
						availableDriversDrivingETDetail.setDrivingDistance(distanceMatrixElement.distance);
						availableDriversDrivingETDetail.setEta(distanceMatrixElement.durationInTraffic);
						availableDriversDrivingETDetails.add(availableDriversDrivingETDetail);
						break;
					}
				}
			}
			rideAvailableDriversDetails.setAvailableDriversDrivingETDetails(availableDriversDrivingETDetails);
			rideAvailableDriversDetails.setTripDistance(tripDistance);
			rideAvailableDriversDetails.setTripDuration(tripDuration);
			rideAvailableDriversDetails.setTripDurationInTraffic(tripDurationInTraffic);
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
			throw new ProcessFailed("Unable to get the drivers");
		}
		return rideAvailableDriversDetails;
	}

	@Override
	public Trip getByTripId(UUID tripId) throws ProcessFailed {
		// TODO Auto-generated method stub
		return tripDao.getByTripId(tripId);
	}
//
//	@Override
//	public Trip getByTripId(Trip tripId) throws ProcessFailed {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
