/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alcord.amazon.AmazonS3Service;
import com.alcord.exception.ProcessFailed;
import com.alcord.modelmappers.DocumentDataDetail;
import com.alcord.service.GalleryService;
import com.alcord.utility.AWSS3Utility;
import com.alcord.utility.Constant;
import com.alcord.utility.StringUtility;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
@Transactional
public class GalleryServiceImpl extends BaseServiceImpl implements GalleryService {

	private static final String SUFFIX = "/";
	private static final String DRIVER_PROFILE_PICS = "DriverProfilePic";
	private static final String PASSENGER_PROFILE_PICS = "PassengerProfilePic";
	private static final String VEHICLE_DISPLAY_PICS = "VehicleDisplay";

	@Autowired
	AmazonS3Service amazonS3Service;

	@Autowired
	private MessageSource messageSource;

	@Override
	public String updateDriverPicture(String driverId, DocumentDataDetail documentDataDetail, MultipartFile file) {
		String fileName = getDriverProfilePicBucketPrefix(driverId) + StringUtility.randomFileName();
		return putDocument(file, driverId, documentDataDetail.getValidity(), fileName);
	}

	@Override
	public String updateVehiclePicture(String vehicleId, String order, DocumentDataDetail documentDataDetail,
			MultipartFile file) {
		String fileName = getVehicleOrderPicBucketPrefix(vehicleId, order) + StringUtility.randomFileName();
		return putDocument(file, vehicleId, documentDataDetail.getValidity(), fileName);
	}

	@Override
	public String updatePassengerProfilePicture(String passengerId, DocumentDataDetail documentDataDetail,
			MultipartFile file) {
		String fileName = getPassengerProfilePicBucketPrefix(passengerId) + StringUtility.randomFileName();
		return putDocument(file, passengerId, documentDataDetail.getValidity(), fileName);
	}

	@Override
	public String getDriverProfilePic(String driverId) throws ParseException {
		final ListObjectsRequest listObjectRequest = new ListObjectsRequest()
				.withBucketName(amazonS3Service.getDefaultBucket())
				.withPrefix(getDriverProfilePicBucketPrefix(driverId));
		ObjectListing objectListing = amazonS3Service.listObjects(listObjectRequest);
		String link = extractLatestPicture(objectListing);
		return link;
	}

	@Override
	public List<String> getVehiclePictures(String vehicleId) throws ParseException {
		List<String> links = new ArrayList<>();
		for (int i = 0; i < Constant.MAX_PIC_LIMIT; i++) {
			final ListObjectsRequest listObjectRequest = new ListObjectsRequest()
					.withBucketName(amazonS3Service.getDefaultBucket())
					.withPrefix(getVehicleOrderPicBucketPrefix(vehicleId, Integer.toString(i)));
			ObjectListing objectListing = amazonS3Service.listObjects(listObjectRequest);
			String link = extractLatestPicture(objectListing);
			if (!StringUtility.isEmpty(link)) {
				links.add(link);
			}
		}
		return links;
	}

	@Override
	public String getPassengerProfilePic(String passengerId) throws ParseException {
		final ListObjectsRequest listObjectRequest = new ListObjectsRequest()
				.withBucketName(amazonS3Service.getDefaultBucket())
				.withPrefix(getDriverProfilePicBucketPrefix(passengerId));
		ObjectListing objectListing = amazonS3Service.listObjects(listObjectRequest);
		String link = extractLatestPicture(objectListing);
		return link;
	}

	private String extractLatestPicture(ObjectListing objectListing) throws ParseException {
		String url = "";
		S3ObjectSummary s3ObjectSummary = AWSS3Utility.getLatestObject(objectListing);
		if (s3ObjectSummary != null) {
			url = amazonS3Service.awsURI(s3ObjectSummary.getKey());
		}
		return url;
	}

	private String getDriverProfilePicBucketPrefix(String driverId) {
		StringBuilder filePrefix = new StringBuilder(DRIVER_PROFILE_PICS).append(SUFFIX).append(driverId)
				.append(SUFFIX);
		return filePrefix.toString();
	}

	private String getVehicleOrderPicBucketPrefix(String vehicleId, String order) {
		StringBuilder filePrefix = new StringBuilder(VEHICLE_DISPLAY_PICS).append(SUFFIX).append(vehicleId)
				.append(SUFFIX).append(order).append(SUFFIX);
		return filePrefix.toString();
	}

	private String getPassengerProfilePicBucketPrefix(String passengerId) {
		StringBuilder filePrefix = new StringBuilder(PASSENGER_PROFILE_PICS).append(SUFFIX).append(passengerId)
				.append(SUFFIX);
		return filePrefix.toString();
	}

	private String putDocument(MultipartFile file, String id, String expiryDate, String fileName) throws ProcessFailed {
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(file.getContentType());
			objectMetadata.addUserMetadata("id", id);
			objectMetadata.addUserMetadata("expiry", expiryDate);

			InputStream is = file.getInputStream();
			byte[] contentBytes = IOUtils.toByteArray(is);
			Long contentLength = Long.valueOf(contentBytes.length);
			objectMetadata.setContentLength(contentLength);
			fileName = AWSS3Utility.getFileNameWithExtension(file, fileName);

			PutObjectResult putObjectResult = amazonS3Service.save(fileName, file.getInputStream(), objectMetadata,
					CannedAccessControlList.PublicRead);
			//logger.info("Saved image with these details:" + putObjectResult.getETag());
		} catch (AmazonClientException | IOException ex) {
			loggingUtility.error(ex);
			// logger.error("Could not put image:" + name, e);
			throw new ProcessFailed("Unable to save file", ex);
		}
		return amazonS3Service.getDefaultBucket() + File.separator + fileName;
	}

	@Override
	public String getDefaultDriverProfilePic() {
		return defaultPic();
	}

	@Override
	public String getDefaultPassengerProfilePic() {
		return defaultPic();
	}

	private String defaultPic() {
		String defaultPic = amazonS3Service.awsURI("nammatstvpc-apps/Global/personplaceholder.jpg");
		return defaultPic;
	}

}
