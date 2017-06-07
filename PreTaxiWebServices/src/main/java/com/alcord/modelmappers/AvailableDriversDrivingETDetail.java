package com.alcord.modelmappers;

import com.alcord.model.AvailableDriverDetails;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;

public class AvailableDriversDrivingETDetail extends AvailableDriverDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7271295406356438048L;
	private Distance drivingDistance;
	private Duration eta;

	public AvailableDriversDrivingETDetail(AvailableDriverDetails availableDriverDetails) {
		setFirstName(availableDriverDetails.getFirstName());
		setLastName(availableDriverDetails.getLastName());
		setLocationLatitude(availableDriverDetails.getLocationLatitude());
		setLocationLongitude(availableDriverDetails.getLocationLongitude());
		setFkeyDriverSlabId(availableDriverDetails.getFkeyDriverSlabId());
		setId(availableDriverDetails.getId());
		setRating(availableDriverDetails.getRating());
		setVehicleModel(availableDriverDetails.getVehicleModel());
		setVehicleType(availableDriverDetails.getVehicleType());
	}

	public Distance getDrivingDistance() {
		return drivingDistance;
	}

	public void setDrivingDistance(Distance drivingDistance) {
		this.drivingDistance = drivingDistance;
	}

	public Duration getEta() {
		return eta;
	}

	public void setEta(Duration eta) {
		this.eta = eta;
	}

}
