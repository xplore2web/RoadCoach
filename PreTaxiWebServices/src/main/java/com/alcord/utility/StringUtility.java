/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.utility;

import java.util.Date;

import com.alcord.model.Address;
import com.google.maps.model.LatLng;

/**
 *
 * @author Ajit
 */
public class StringUtility {

	public static String safeBoolean(boolean valid) {
        return valid ? "true" : "false";
    }

    public static boolean isEmpty(final String value) {
        return (value == null || (value.trim().length() == 0) || value.equalsIgnoreCase("null") || value.equals(""));
    }
    
	public static String convertLatLng(final LatLng latLng) {
		if (latLng != null) {
			return convertLatLng(latLng.lat, latLng.lng);
		}
		return "";
	}

	public static String convertLatLng(Double lat, Double lng) {
		String latLng = convertLatLng(String.valueOf(lat), String.valueOf(lng));
		return latLng;
	}

	public static String convertLatLng(String locationLatitude, String locationLongitude) {
		String latLng = locationLatitude + "," + locationLongitude;
		return latLng;
	}
		
	public static String randomFileName() {
		String randomFile = "Picture" + Long.toString(new Date().getTime());
		return randomFile;

	}

	public static String completeAddress(Address address) {
		StringBuilder stringBuilder = new StringBuilder();

		if (!StringUtility.isEmpty(address.getAddress1())) {
			stringBuilder.append(address.getAddress1());
		}
		if (!StringUtility.isEmpty(address.getAddress2())) {
			stringBuilder.append(address.getAddress2());
		}
		if (!StringUtility.isEmpty(address.getCity())) {
			stringBuilder.append(address.getCity());
		}
		if (!StringUtility.isEmpty(address.getState())) {
			stringBuilder.append(address.getState());
		}
		if (!StringUtility.isEmpty(address.getCountry())) {
			stringBuilder.append(address.getCountry());
		}

		return stringBuilder.toString();

	}
    
}
