package com.alcord.modelmappers;

import java.io.Serializable;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.alcord.utility.ValidationUtility;

public class DriverLocationDetail implements Serializable, Validator {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8941601149170992684L;
	private String driverId;
	private String locationLatitude;
	private String locationLongitude;
	
	public String getLocationLatitude() {
		return locationLatitude;
	}
	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	public String getLocationLongitude() {
		return locationLongitude;
	}
	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	@Override
	public boolean supports(Class<?> type) {
		 return DriverLocationDetail.class.isAssignableFrom(type);
	}
	@Override
	public void validate(Object arg0, Errors errors) {
		if (ValidationUtility.isEmpty(this.driverId)) {
            errors.reject(driverId,"Driver id is missing");
        }
		if (ValidationUtility.isEmpty(this.locationLatitude)) {
            errors.reject(locationLatitude,"Location latitude key missing");
        }
		if (ValidationUtility.isEmpty(this.locationLongitude)) {
            errors.reject(locationLongitude,"Location longitude key missing");
        }
		
	}
}
