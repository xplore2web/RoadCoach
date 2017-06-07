package com.alcord.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.enums.RatingStatus;
import com.alcord.modelmappers.RatingDetail;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.RatingService;
import com.alcord.utility.Authorities;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/rating")
@io.swagger.annotations.Api(value = "Trip Ratings", description = "All Ratings Related to Passenger")
public class RatingController extends BaseController {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Submit trip  Rating")
	public ResponseEntity<TransactionResponse> submitPassengerRating(
			@RequestHeader("authorization") String authorization, @RequestBody RatingDetail ratingDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				if (getAccountRole().equalsIgnoreCase(Authorities.ROLE_DRIVER.toString())) {
					ratingDetail.setRatingBy(RatingStatus.RATING_BY_DRIVER.toString());
				} else if (getAccountRole().equalsIgnoreCase(Authorities.ROLE_PASSENGER.toString())) {
					ratingDetail.setRatingBy(RatingStatus.RATING_BY_PASSENGER.toString());
				}
				ratingService.submitRating(ratingDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("rating_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
}
