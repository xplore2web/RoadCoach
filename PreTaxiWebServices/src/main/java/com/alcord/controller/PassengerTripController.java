package com.alcord.controller;

import java.util.Locale;

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

import com.alcord.modelmappers.TripDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.PassengerTripService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/passengerTrip")
@io.swagger.annotations.Api(value = "Passenger Trip", description = "All Details related to Passenger trips")
public class PassengerTripController extends BaseController {


	@Autowired
	private PassengerTripService passengerTripService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/all/trip/{start}/{end}/{passengerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get All Trip from Passenger's perspective. From and To Date")
	public ResponseEntity<GenericResponse> getAllRides(@RequestHeader("authorization") String authorization,
			@PathVariable("start") String startDate, @PathVariable("end") String endDate,@PathVariable("passengerId") String passengerId,  HttpServletRequest httpServletRequest) {
		GenericResponse<TripDetail> genericResponse = new GenericResponse<TripDetail>();
		try {
			/// to-do Ajit get trip details from view 
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("account_details_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/bookTrip/{passengerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Book trip")
	public ResponseEntity<TransactionResponse> bookTrip(@RequestHeader("authorization") String authorization,@PathVariable("passengerId") String passengerId,
			@RequestBody TripDetail tripDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				tripDetail.setPassengerId(getLoggedInPassenger().getId().toString());
				passengerTripService.bookTrip(tripDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("trip_booked_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/alert/{passengerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Alert emergency contact")
	public ResponseEntity<TransactionResponse> alertEmergencyContact(
			@RequestHeader("authorization") String authorization,@PathVariable("passengerId") String passengerId,HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				passengerTripService.alertEmergencyContact(getAccountId());
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource
						.getMessage("passenger_trip_alert_sent_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
