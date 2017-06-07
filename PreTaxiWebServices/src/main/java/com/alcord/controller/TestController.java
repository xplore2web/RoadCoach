/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.enums.DeviceType;
import com.alcord.google.GoogleMapsService;
import com.alcord.invoice.PDFInvoiceService;
import com.alcord.model.AvailableDriverDetails;
import com.alcord.modelmappers.GCM;
import com.alcord.modelmappers.Notification;
import com.alcord.modelmappers.PushRequest;
import com.alcord.modelmappers.RideAvailableDriversDetails;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.AccountService;
import com.alcord.service.EmailProviderService;
import com.alcord.service.PushNotificationService;
import com.alcord.service.TripService;
import com.alcord.utility.ErrorHandlingUtil;
import com.alcord.utility.ListUtility;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Ajit
 */
@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController extends BaseController {

	@Autowired
	private TripService tripService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private Environment env;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private EmailProviderService emailProviderService;
	@Autowired
	private PDFInvoiceService pdfInvoiceService;
	@Autowired
	private GoogleMapsService googleMapsService;

	@RequestMapping(value = "/{token}/{message}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Testing Tokens")
	public ResponseEntity<TransactionResponse> test(@PathVariable("token") String token,
			@PathVariable("message") String message, HttpServletRequest servletRequest,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			PublishRequest publishRequest = new PublishRequest();

			ObjectMapper mapper = new ObjectMapper();
			PushRequest pushRequest = new PushRequest();
			pushRequest.setDef(message);

			GCM gcm = new GCM();
			Notification notification = new Notification();
			notification.setText(message);
			gcm.setNotification(notification);
			pushRequest.setGcm(gcm);

			String jsonInString = mapper.writeValueAsString(pushRequest);
			publishRequest.setMessage(jsonInString);
			publishRequest.setMessageStructure("json");
			System.out.println("Publist request:" + publishRequest.toString());
			PublishResult publishResult = pushNotificationService.publishRequestToToken(token, DeviceType.Android,
					publishRequest);
			System.out.println(publishResult.toString());
			System.out.println(publishResult.getSdkResponseMetadata().toString());
			transactionResponse.setId(publishResult.getMessageId());
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("app_key_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/googledistance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Testing Tokens")
	public ResponseEntity<TransactionResponse> test(HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {

			String[] origins = { "New York, NY, USA", "Bangalore, Karnataka, India", "Framingham, MA USA" };
			String[] destinations = { "New Jersey, NJ, USA", "Mumbai, Maharashtra, India", "Natick, MA, USA" };
			DistanceMatrix distanceMatrix = googleMapsService.getDistanceMatrix(origins, destinations, new DateTime());
			System.out.println(distanceMatrix.toString());

			// transactionResponse.setId(sendEmailResult.getMessageId());
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("app_key_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/convertaddress", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Testing Tokens")
	public ResponseEntity<TransactionResponse> convertAddress(HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {

			String[] origins = { "New York, NY, USA", "Bangalore, Karnataka, India", "Framingham, MA USA" };
			String[] destinations = { "New Jersey, NJ, USA", "Mumbai, Maharashtra, India", "Natick, MA, USA" };

			GeocodingResult[] result = googleMapsService.addressToLatLng(origins[0]);
			System.out.println(result.toString());

			// transactionResponse.setId(sendEmailResult.getMessageId());
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("app_key_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/driversInRadius", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Testing Tokens")
	public ResponseEntity<GenericResponse> driversInRadius(HttpServletRequest servletRequest) {
		GenericResponse<RideAvailableDriversDetails> genericResponse = new GenericResponse<RideAvailableDriversDetails>();
		try {

			List<AvailableDriverDetails> availableDriverDetails = tripService.getAllDriversInPassengerArea(13.026236,
					77.609179, 1);
			if (!ListUtility.isEmpty(availableDriverDetails)) {
				LatLng tripSource = new LatLng(13.026236, 77.609179);
				LatLng tripDestination = new LatLng(13.026236, 77.609179);
				RideAvailableDriversDetails rideAvailableDriversDetails = tripService
						.getDrivingETADetails(tripSource, tripDestination, availableDriverDetails);
				if (!ListUtility.isEmpty(rideAvailableDriversDetails.getAvailableDriversDrivingETDetails())) {
					genericResponse.addDetail(rideAvailableDriversDetails);
					genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
							messageSource.getMessage("app_key_retrieved_successfully", new String[] {}, Locale.US)));
				} else {
					genericResponse.setOperationStatus(
							ErrorHandlingUtil.dataErrorValidation("Could not find a single vehicle"));
				}
			} else {
				genericResponse
						.setOperationStatus(ErrorHandlingUtil.dataErrorValidation("Could not find a single vehicle"));
			}

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

}
