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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.model.Driver;
import com.alcord.modelmappers.DriverAccountDetail;
import com.alcord.modelmappers.DriverLocationDetail;
import com.alcord.modelmappers.DriverSlabDetail;
import com.alcord.modelmappers.DriverStatus;
import com.alcord.modelmappers.DriverTodayDetail;
import com.alcord.modelmappers.UpdateDriverAccount;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.DriverService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/driverAccount")
@io.swagger.annotations.Api(value = "Driver Account", description = "Get all account details")
public class DriverAccountController extends BaseController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private DriverService driverService;
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Details of the logged in driver")
	public ResponseEntity<GenericResponse> getLoggedInDriverAccountDetail(@RequestHeader("authorization") String authorization,
			HttpServletRequest httpServletRequest) {
		GenericResponse<DriverAccountDetail> genericResponse = new GenericResponse<DriverAccountDetail>();
		try {
			List<DriverAccountDetail> driverAccountDetailList = new ArrayList<>();
			Driver driver = getLoggedInDriver();
			if (driver == null) {
				throw new Exception(messageSource.getMessage("driver_not_registered", new String[] {}, Locale.US));
			}
			DriverAccountDetail driverAccountDetail = driverService
					.getDriverAccountDetailsBydriverId(driver.getId());

			driverAccountDetailList.add(driverAccountDetail);

			genericResponse.setDetails(driverAccountDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_account_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/detail/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Details of the driver by diver id")
	public ResponseEntity<GenericResponse> getDriverAccountDetail(@RequestHeader("authorization") String authorization,
			@RequestHeader("driverId") String driverId,
			HttpServletRequest httpServletRequest) {
		GenericResponse<DriverAccountDetail> genericResponse = new GenericResponse<DriverAccountDetail>();
		try {
			List<DriverAccountDetail> driverAccountDetailList = new ArrayList<>();
			DriverAccountDetail driverAccountDetail = driverService
					.getDriverAccountDetailsBydriverId(UUID.fromString(driverId));

			driverAccountDetailList.add(driverAccountDetail);

			genericResponse.setDetails(driverAccountDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_account_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Driver's status")
	public ResponseEntity<GenericResponse> geDriverStatus(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<DriverStatus> genericResponse = new GenericResponse<DriverStatus>();
		try {
			List<DriverStatus> driverStatusList = driverService.getAllDriverStatus();

			genericResponse.setDetails(driverStatusList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_status_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update Driver's status")
	public ResponseEntity<TransactionResponse> updateUserStatus(@RequestHeader("authorization") String authorization, 
			@RequestBody DriverStatus driverStatus, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				driverService.updateStatus(driverStatus);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("update_driver_status", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/todayDetails/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Driver's status")
	public ResponseEntity<GenericResponse> getTodayDetails(@RequestHeader("authorization") String authorization, @PathVariable("driverId") String driverId,  HttpServletRequest httpServletRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			List<DriverTodayDetail> driverTodayDetailList = new ArrayList<>();
			DriverTodayDetail driverTodayDetail = driverService.getDriverTodayDetail(UUID.fromString(driverId));

			driverTodayDetailList.add(driverTodayDetail);
			genericResponse.setDetails(driverTodayDetailList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource
					.getMessage("driver_today_details_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/currentLocation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "save Driver's location")
	public ResponseEntity<TransactionResponse> driverCurrentLoaction(
			@RequestHeader("authorization") String authorization,
			@RequestBody DriverLocationDetail driverLocationDetail, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		driverLocationDetail.validate(driverLocationDetail, bindingResult);
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				driverService.saveCurrentLocation(driverLocationDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource
						.getMessage("driver_current_location_save_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/slab", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "save Driver's slab")
	public ResponseEntity<TransactionResponse> slab(@RequestHeader("authorization") String authorization,
			@RequestBody DriverSlabDetail driverSlabDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				driverService.saveSlab(driverSlabDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("driver_slab_save_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/detail", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update Driver's account")
	public ResponseEntity<TransactionResponse> updateAccountDetail(@RequestHeader("authorization") String authorization,
			@RequestBody UpdateDriverAccount updateDriverAccount,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				driverService.updateAccount(updateDriverAccount);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("update_driver_account", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
}
