package com.alcord.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alcord.amazon.AmazonS3Service;
import com.alcord.dao.DriverDao;
import com.alcord.enums.DriverDocumentType;
import com.alcord.enums.VehicleDocumentType;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Address;
import com.alcord.model.Driver;
import com.alcord.modelmappers.DocumentDataDetail;
import com.alcord.service.DocumentsService;
import com.alcord.service.DriverService;
import com.alcord.service.DriverVehicleService;
import com.alcord.utility.AWSS3Utility;
import com.alcord.utility.Constant;
import com.alcord.utility.LoggingUtility;
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
public class DocumentsServiceImpl extends BaseServiceImpl implements DocumentsService {

	private static final String SUFFIX = "/";
	private static final String DRIVERS = "Drivers";
	private static final String VEHICLES = "Vehicles";

	@Autowired
	AmazonS3Service amazonS3Service;
	@Autowired
	DriverDao driverDao;
	@Autowired
	DriverService driverService;
	@Autowired
	DriverVehicleService driverVehicleService;
	
	private String getBucketPrefix(String driverId, DriverDocumentType driverDocumentType) {
		StringBuilder filePrefix = new StringBuilder(DRIVERS).append(SUFFIX).append(driverId).append(SUFFIX);
		filePrefix.append(driverDocumentType.name()).append(SUFFIX);
		return filePrefix.toString();
	}

	private String getBucketPrefix(String driverId) {
		StringBuilder filePrefix = new StringBuilder(DRIVERS).append(SUFFIX).append(driverId).append(SUFFIX);
		return filePrefix.toString();
	}
	
	private String getBucketPrefix(String vehicleId, VehicleDocumentType vehicleDocumentType) {
		StringBuilder filePrefix = new StringBuilder(VEHICLES).append(SUFFIX).append(vehicleId).append(SUFFIX);
		filePrefix.append(vehicleDocumentType.name()).append(SUFFIX);
		return filePrefix.toString();
	}

	public String putDocument(MultipartFile file, String name, String vehicleId,
			VehicleDocumentType vehicleDocumentType, String expiryDate) throws ProcessFailed {
		String fileName = "";
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(file.getContentType());
			objectMetadata.addUserMetadata("vehicleId", vehicleId);
			objectMetadata.addUserMetadata("type", vehicleDocumentType.getValue());
			objectMetadata.addUserMetadata("expiry", expiryDate);
			objectMetadata.addUserMetadata("name", name);

			InputStream is = file.getInputStream();
			byte[] contentBytes = IOUtils.toByteArray(is);
			Long contentLength = Long.valueOf(contentBytes.length);
			objectMetadata.setContentLength(contentLength);

			if (!StringUtility.isEmpty(name) && name.length() > 10) {
				name = name.substring(0, 10);
			}
			// Removing escaped characters, re-strict the length to 10 and also
			// appending a epoch time.
			name = Jsoup.parse(vehicleDocumentType.getValue()).text() + new Date().getTime();
			fileName = getBucketPrefix(vehicleId, vehicleDocumentType) + name;
			fileName = AWSS3Utility.getFileNameWithExtension(file, fileName);
			PutObjectResult putObjectResult = amazonS3Service.save(
					fileName, file.getInputStream(), objectMetadata,
					CannedAccessControlList.PublicRead);
			loggingUtility.log("Saved image with these details:" + putObjectResult.getETag());
		} catch (AmazonClientException | IOException e) {
			loggingUtility.log("Could not put image:" + name, e);
			throw new ProcessFailed("Unable to save file", e);
		}
		return amazonS3Service.getDefaultBucket() + File.separator + fileName;
	}

	public String putDocument(MultipartFile file, String name, String driverId, DriverDocumentType driverDocumentType,
			String dateString)
			throws ProcessFailed {
		String fileName = "";
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(file.getContentType());
			objectMetadata.addUserMetadata("driverId", driverId);
			objectMetadata.addUserMetadata("type", driverDocumentType.getValue());
			objectMetadata.addUserMetadata("expiry", dateString);
			objectMetadata.addUserMetadata("name", name);
			
			InputStream is = file.getInputStream();
			byte[] contentBytes = IOUtils.toByteArray(is);
			Long contentLength = Long.valueOf(contentBytes.length);
			objectMetadata.setContentLength(contentLength);

			if (!StringUtility.isEmpty(name) && name.length() > 10) {
				name = name.substring(0, 10);
			}
			// Removing escaped characters, re-strict the length to 10 and also
			// appending a epoch time.
			name = Jsoup.parse(driverDocumentType.getValue()).text() + new Date().getTime();
			fileName = getBucketPrefix(driverId, driverDocumentType) + name;
			fileName = AWSS3Utility.getFileNameWithExtension(file, fileName);
			PutObjectResult putObjectResult = amazonS3Service.save(fileName, file.getInputStream(), objectMetadata,
					CannedAccessControlList.PublicRead);
			loggingUtility.log("Saved image with these details:" + putObjectResult.getETag());
		} catch (AmazonClientException | IOException e) {
			loggingUtility.log("Could not put image:" + name, e);
			throw new ProcessFailed("Unable to save file", e);
		}
		return amazonS3Service.getDefaultBucket() + File.separator + fileName;
	}

	private DocumentDataDetail extractLatestDocument(ObjectListing objectListing,
			VehicleDocumentType vehicleDocumentType) throws ParseException {

		S3ObjectSummary s3ObjectSummary = AWSS3Utility.getLatestObject(objectListing);
		DocumentDataDetail documentDataDetail = parseS3ObjectSummary(s3ObjectSummary);

		return documentDataDetail;
	}

	private DocumentDataDetail parseS3ObjectSummary(S3ObjectSummary s3ObjectSummary) throws ParseException {
		DocumentDataDetail documentDataDetail = null;

		if (s3ObjectSummary != null) {
			documentDataDetail = new DocumentDataDetail();
			documentDataDetail.setLocation(amazonS3Service.awsURI(s3ObjectSummary.getKey()));
			documentDataDetail.setValue(s3ObjectSummary.getKey());

			ObjectMetadata objectMetadata = amazonS3Service.getObjectMetadata(s3ObjectSummary.getKey());
			Map userMetadataMap = objectMetadata.getUserMetadata();
			documentDataDetail.setType(userMetadataMap.get("type").toString());
			documentDataDetail.setValidity(userMetadataMap.get("expiry").toString());
			documentDataDetail.setName(userMetadataMap.get("name").toString());
			if (userMetadataMap.containsKey("driverId")) {
				documentDataDetail.setAdditionalData((String) userMetadataMap.get("driverId"));
			}

			if (userMetadataMap.containsKey("vehicleId")) {
				documentDataDetail.setAdditionalData((String) userMetadataMap.get("vehicleId"));
			}

		}
		return documentDataDetail;

	}

	private DocumentDataDetail extractLatestDocument(ObjectListing objectListing, DriverDocumentType driverDocumentType)
			throws ParseException {


		S3ObjectSummary s3ObjectSummary = getLatestObject(objectListing);
		DocumentDataDetail documentDataDetail = parseS3ObjectSummary(s3ObjectSummary);
		return documentDataDetail;
	}

	public static S3ObjectSummary getLatestObject(ObjectListing objectListing) {
		if (objectListing != null && objectListing.getObjectSummaries() != null
				&& objectListing.getObjectSummaries().size() > 0) {
			List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
			if (s3ObjectSummaries.size() == 1) {
				return s3ObjectSummaries.get(0);
			} else {
				// Apparently the list is already sorted by lastModified.
				// So I will go ahead and pick up the latest
				return s3ObjectSummaries.get(s3ObjectSummaries.size() - 1);
			}

		}
		return null;
	}
	@Override
	public List<DocumentDataDetail> getDocumentsForDriver(String driverId) throws ProcessFailed, ParseException {

		List<DocumentDataDetail> documents = new ArrayList<>();

		for (int i = 0; i < DriverDocumentType.values().length; i++) {
			DriverDocumentType driverDocumentType = DriverDocumentType.values()[i];
			final ListObjectsRequest listObjectRequest = new ListObjectsRequest()
					.withBucketName(amazonS3Service.getDefaultBucket())
					.withPrefix(getBucketPrefix(driverId, driverDocumentType));
			ObjectListing objectListing = amazonS3Service.listObjects(listObjectRequest);
			DocumentDataDetail documentDataDetail = extractLatestDocument(objectListing, driverDocumentType);
			if (documentDataDetail == null) {
				documentDataDetail = new DocumentDataDetail();
				documentDataDetail.setType(driverDocumentType.getValue());
			}
			documents.add(documentDataDetail);
		}

		return documents;
	}

	@Override
	public List<DocumentDataDetail> getDocumentsForVehicle(String vehicleId) throws ProcessFailed, ParseException {
		List<DocumentDataDetail> documents = new ArrayList<>();

		for (int i = 0; i < VehicleDocumentType.values().length; i++) {
			VehicleDocumentType vehicleDocumentType = VehicleDocumentType.values()[i];
			final ListObjectsRequest listObjectRequest = new ListObjectsRequest()
					.withBucketName(amazonS3Service.getDefaultBucket())
					.withPrefix(getBucketPrefix(vehicleId, vehicleDocumentType));
			ObjectListing objectListing = amazonS3Service.listObjects(listObjectRequest);
			DocumentDataDetail documentDataDetail = extractLatestDocument(objectListing, vehicleDocumentType);
			if (documentDataDetail != null){
				documentDataDetail.setVehicleDetails(driverVehicleService.getVehicleDetail(UUID.fromString(vehicleId)));
			}
			
			if (documentDataDetail == null) {
				documentDataDetail = new DocumentDataDetail();
				documentDataDetail.setType(vehicleDocumentType.getValue());
			}
			documents.add(documentDataDetail);
		}
		return documents;
	}

	@Override
	public String updatePersonalDocument(String driverId, String documentType, DocumentDataDetail documentDataDetail,
			MultipartFile file) throws ProcessFailed, ParseException {
		return putDocument(file, documentDataDetail.getName(), driverId, DriverDocumentType.valueOf(documentType),
				documentDataDetail.getValidity());

	}

	@Override
	public String updateVehicleDocument(String vehicleId, String documentType, DocumentDataDetail documentDataDetail,
			MultipartFile file) throws ProcessFailed, ParseException {
		return putDocument(file, documentDataDetail.getName(), vehicleId, VehicleDocumentType.valueOf(documentType),
				documentDataDetail.getValidity());
	}
}
