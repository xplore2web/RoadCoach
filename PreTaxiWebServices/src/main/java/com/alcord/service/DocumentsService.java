package com.alcord.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alcord.exception.ProcessFailed;
import com.alcord.modelmappers.DocumentDataDetail;

public interface DocumentsService {
	
	/**
	 * 
	 * @param tripId
	 * @return TripDetail
	 * @throws ProcessFailed
	 * @throws ParseException
	 */
	public List<DocumentDataDetail> getDocumentsForDriver(String driverId) throws ProcessFailed, ParseException;
	
	/**
     * 
     * @param tripId
     * @return TripDetail
     * @throws ProcessFailed 
     */
	public List<DocumentDataDetail> getDocumentsForVehicle(String vehicleId) throws ProcessFailed, ParseException;
	
	/**
	 * 
	 * @param documentDataDetail
	 * @param documentId
	 * @throws ProcessFailed
	 * @throws ParseException
	 */
	public String updatePersonalDocument(String driverId, String documentType, DocumentDataDetail documentDataDetail,
			MultipartFile file) throws ProcessFailed, ParseException;
	
	/**
	 * 
	 * @param documentDetail
	 * @param documentId
	 * @param documentType
	 * @param file
	 * @return
	 * @throws ProcessFailed
	 */
	public String updateVehicleDocument(String vehicleId, String documentType, DocumentDataDetail documentDataDetail,
			MultipartFile file)
			throws ProcessFailed, ParseException;

}
