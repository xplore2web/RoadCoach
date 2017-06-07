package com.alcord.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.modelmappers.RatingDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.service.RatingService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/driverRating")
@io.swagger.annotations.Api(value = "Driver Ratings", description = "All ratings related to driver")
public class DriverRatingController extends BaseController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/all/ratings/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all ratings for the driver")
	public ResponseEntity<GenericResponse> getAllRatings(@RequestHeader("authorization") String authorization,@PathVariable("driverId") String driverId,  HttpServletRequest httpServletRequest) {
		GenericResponse<RatingDetail> genericResponse = new GenericResponse<RatingDetail>();
		try {
			List<RatingDetail> ratingDetailList = ratingService.getAllRatingDetailByDriverId(UUID.fromString(driverId));
			genericResponse.setDetails(ratingDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("rating_details_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/rating/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Rating Detail by rating id")
	public ResponseEntity<GenericResponse> getRatingById(@RequestHeader("authorization") String authorization, @PathVariable("id") String id,  HttpServletRequest httpServletRequest) {
		GenericResponse<RatingDetail> genericResponse = new GenericResponse<RatingDetail>();
		try {
			List<RatingDetail> ratingDetailList = new ArrayList<>();
			RatingDetail ratingDetail = ratingService.getRatingById(UUID.fromString(id));
			ratingDetailList.add(ratingDetail);
			genericResponse.setDetails(ratingDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("rating_details_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

}
