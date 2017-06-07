package com.alcord.google;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.alcord.exception.ProcessFailed;
import com.alcord.utility.StringUtility;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

/**
 * This class is a client for interacting with Amazon S3 bucket resources.
 *
 * @author AR
 */
@Component
public class GoogleMapsService {

	@Autowired
	GoogleProperties googleProperties;
	
	GeoApiContext context;

	public GoogleMapsService() {

	}

	public GoogleMapsService(GoogleProperties googleProperties) {
		this.googleProperties = googleProperties;
    }

	public DistanceMatrix getDistanceMatrix(String[] origins, String[] destinations, DateTime departureTime)
			throws ApiException, InterruptedException, IOException {
		if (origins == null || destinations == null || origins.length != destinations.length) {
			throw new ProcessFailed("Origin and Destination length is not the same");
		}
		return DistanceMatrixApi.getDistanceMatrix(getContext(), origins, destinations).mode(TravelMode.DRIVING)
				.trafficModel(TrafficModel.OPTIMISTIC).units(Unit.METRIC).departureTime(departureTime).await();
	}

	@Cacheable(value = "addressToLatLng", key = "#address")
	public GeocodingResult[] addressToLatLng(String address) throws ApiException, InterruptedException, IOException {
		if (StringUtility.isEmpty(address)) {
			throw new ProcessFailed("Address cannot be empty");
		}
		System.out.println("Hitting server to get the result");
		return GeocodingApi.geocode(getContext(), address).await();
	}

	@Cacheable("latlngToAddress")
	public GeocodingResult[] latLonToAddress(LatLng latlng) throws ApiException, InterruptedException, IOException {
		if (latlng == null) {
			throw new ProcessFailed("Lat Long cannot be empty");
		}
		return GeocodingApi.reverseGeocode(getContext(), latlng).await();
	}

	private GeoApiContext getContext() {
		if (context == null) {
			context = new GeoApiContext().setApiKey(googleProperties.getMaps().getDistanceMatrixKey());
		}
		return context;
	}

	public GoogleProperties getGoogleProperties() {
		return googleProperties;
	}

}
