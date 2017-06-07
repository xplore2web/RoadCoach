package com.alcord.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.enums.TripStatus;
import com.alcord.model.Trip;
import com.alcord.modelmappers.TripAddtionalFareDetail;
import com.alcord.modelmappers.TripDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.BillingSerice;
import com.alcord.service.DriverTripService;
import com.alcord.service.TripService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/driverTrip")
@io.swagger.annotations.Api(value = "Driver Trips", description = "All Trip Details related to driver")
public class DriverTripController extends BaseController {

	@Autowired
	private DriverTripService driverTripService;
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BillingSerice billingSerice;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/acceptTrip", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Accept a trip when requested by a passenger. Provides OTP")
	public ResponseEntity<TransactionResponse> acceptTrip(@RequestHeader("authorization") String authorization,
			@RequestBody TripDetail tripDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			driverTripService.acceptTrip(tripDetail);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_update_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	
	@RequestMapping(value = "/pickupStart/{tripId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Pickup Start")
	public ResponseEntity<TransactionResponse> pickupStart(@RequestHeader("authorization") String authorization,
			@PathVariable("tripId") String tripId,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			Trip trip = tripService.getById(UUID.fromString(tripId));
			trip.setPickupStartTime(new Date());
			trip.setTripStatus(TripStatus.PICKUP_START.toString());
			trip.setUpdatedAt(new Date());
			tripService.update(trip);
			
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);

	}
	
	@RequestMapping(value = "/pickupEnd/{tripId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Pickup End")
	public ResponseEntity<TransactionResponse> pickupEnd(@RequestHeader("authorization") String authorization,
			@PathVariable("tripId") String tripId,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			Trip trip = tripService.getById(UUID.fromString(tripId));
			trip.setPickupStartTime(new Date());
			trip.setTripStatus(TripStatus.PICKUP_END.toString());
			trip.setUpdatedAt(new Date());
			tripService.update(trip);
			
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);

	}
	@RequestMapping(value = "/startTrip/{tripId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Start trip")
	public ResponseEntity<TransactionResponse> startTrip(@RequestHeader("authorization") String authorization,
			@PathVariable("tripId") String tripId,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			Trip trip = tripService.getById(UUID.fromString(tripId));
			trip.setPickupStartTime(new Date());
			trip.setTripStatus(TripStatus.TRIP_START.toString());
			trip.setUpdatedAt(new Date());
			tripService.update(trip);
			
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/endTrip/{tripId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Start trip")
	public ResponseEntity<TransactionResponse> endTrip(@RequestHeader("authorization") String authorization,
			@PathVariable("tripId") String tripId,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			Trip trip = tripService.getById(UUID.fromString(tripId));
			trip.setPickupStartTime(new Date());
			trip.setTripStatus(TripStatus.TRIP_END.toString());
			trip.setUpdatedAt(new Date());
			tripService.update(trip);
			
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);

	}
	
	@RequestMapping(value = "/addtionalFare", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Addtional fare")
	public ResponseEntity<TransactionResponse> addtionalFare(@RequestHeader("authorization") String authorization,
			@RequestBody TripAddtionalFareDetail tripAddtionalFareDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			billingSerice.addAddtionalFare(tripAddtionalFareDetail);		
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/all/trip/{start}/{end}/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get All trip from Driver's perspective. From and To Date")
	public ResponseEntity<GenericResponse> getAllRides(@RequestHeader("authorization") String authorization,
			@PathVariable("start") String startDate, @PathVariable("end") String endDate,@PathVariable("driverId") String driverId,  HttpServletRequest httpServletRequest) {
		GenericResponse<TripDetail> genericResponse = new GenericResponse<TripDetail>();
		try {
			List<TripDetail> tripDetailList = driverTripService.getAllTrip(startDate, endDate, UUID.fromString(driverId));
			genericResponse.setDetails(tripDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("trip_retrieve_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

}
