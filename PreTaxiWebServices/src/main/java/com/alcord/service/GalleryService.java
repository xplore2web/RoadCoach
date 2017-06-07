/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alcord.modelmappers.DocumentDataDetail;

/**
 *
 * @author AR
 */
public interface GalleryService {

	String updateDriverPicture(String driverId, DocumentDataDetail documentDataDetail, MultipartFile file);

	String updateVehiclePicture(String vehicleId, String order, DocumentDataDetail documentDataDetail,
			MultipartFile file);

	String updatePassengerProfilePicture(String passengerId, DocumentDataDetail documentDataDetail, MultipartFile file);

	String getDriverProfilePic(String driverId) throws ParseException;

	List<String> getVehiclePictures(String vehicleId) throws ParseException;

	String getPassengerProfilePic(String passengerId) throws ParseException;

	String getDefaultDriverProfilePic();
    
	String getDefaultPassengerProfilePic();
    
}
