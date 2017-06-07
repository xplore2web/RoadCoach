package com.alcord.modelmappers;

import java.util.List;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;

public class RideAvailableDriversDetails {

	private List<AvailableDriversDrivingETDetail> availableDriversDrivingETDetails;
	private Duration tripDuration;
	private Distance tripDistance;
	private Duration tripDurationInTraffic;

	public List<AvailableDriversDrivingETDetail> getAvailableDriversDrivingETDetails() {
		return availableDriversDrivingETDetails;
	}

	public void setAvailableDriversDrivingETDetails(
			List<AvailableDriversDrivingETDetail> availableDriversDrivingETDetails) {
		this.availableDriversDrivingETDetails = availableDriversDrivingETDetails;
	}

	public Duration getTripDuration() {
		return tripDuration;
	}

	public void setTripDuration(Duration tripDuration) {
		this.tripDuration = tripDuration;
	}

	public Distance getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(Distance tripDistance) {
		this.tripDistance = tripDistance;
	}

	public Duration getTripDurationInTraffic() {
		return tripDurationInTraffic;
	}

	public void setTripDurationInTraffic(Duration tripDurationInTraffic) {
		this.tripDurationInTraffic = tripDurationInTraffic;
	}

}
