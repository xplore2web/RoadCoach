package com.alcord.controller;

import java.util.ArrayList;
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
import com.alcord.modelmappers.TripCancelDetail;
import com.alcord.modelmappers.TripDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.DriverTripService;
import com.alcord.service.TripService;
import com.alcord.utility.Authorities;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/trip")
public class TripController extends BaseController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private TripService tripService;

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "cancel trip")
	public ResponseEntity<TransactionResponse> cancelTrip(@RequestHeader("authorization") String authorization,
			@RequestBody TripCancelDetail tripCancelDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				Trip trip = tripService.getById(UUID.fromString(tripCancelDetail.getTripId()));
				trip.setIsCancelled(true);
				if (getAccountRole().equalsIgnoreCase(Authorities.ROLE_DRIVER.toString())) {
					trip.setCancelledBy(TripStatus.TRIP_CANCELLED_DRIVER.toString());
				} else if (getAccountRole().equalsIgnoreCase(Authorities.ROLE_PASSENGER.toString())) {
					trip.setCancelledBy(TripStatus.TRIP_CANCELLED_PASSENGER.toString());
				}
				trip.setUpdatedAt(new Date());
				tripService.update(trip);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("trip_cancelled_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/detail/{tripId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Trip Details")
	public ResponseEntity<GenericResponse> getTripDetail(@RequestHeader("authorization") String authorization,
			@PathVariable("tripId") String tripId,  HttpServletRequest httpServletRequest) {
		GenericResponse<TripDetail> genericResponse = new GenericResponse();
		try {
			List<TripDetail> tripDetailList = new ArrayList<>();
			// to-do Ajit complete this method after view
			TripDetail tripDetail = new TripDetail();
			tripDetailList.add(tripDetail);
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
