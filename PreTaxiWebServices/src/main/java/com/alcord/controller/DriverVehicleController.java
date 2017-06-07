package com.alcord.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.alcord.model.Vehicle;
import com.alcord.modelmappers.VehicleDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.DriverService;
import com.alcord.service.DriverVehicleService;
import com.alcord.service.VehicleService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/driverVehicles")
@io.swagger.annotations.Api(value = "Driver Vehicles", description = "All Details related to driver")
public class DriverVehicleController extends BaseController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private DriverVehicleService driverVehicleService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private DriverService driverSerice;

	@RequestMapping(value = "/all/approved/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get All Approved Vehicles of driver")
	public ResponseEntity<GenericResponse> allApprovedVehicles(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId, HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleDetail> genericResponse = new GenericResponse<VehicleDetail>();
		try {
			List<VehicleDetail> vehicleDetailList = driverVehicleService.getAllApprovedVehicleDetail(driverId);
			genericResponse.setDetails(vehicleDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource
					.getMessage("driver_vehicles_approved_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get All Vehicles of driver")
	public ResponseEntity<GenericResponse> allCars(@RequestHeader("authorization") String authorization,  HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleDetail> genericResponse = new GenericResponse<VehicleDetail>();
		try {
			List<VehicleDetail> vehicleDetailList = driverVehicleService.getAllVehicleDetail();
			genericResponse.setDetails(vehicleDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_vehicles_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/detail{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Details of Vehicle")
	public ResponseEntity<GenericResponse> vehicleDetail(@RequestHeader("authorization") String authorization,
			@PathVariable("id") String id,  HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleDetail> genericResponse = new GenericResponse<VehicleDetail>();
		try {
			List<VehicleDetail> vehicleDetailList = new ArrayList<>();
			VehicleDetail vehicleDetail = driverVehicleService.getVehicleDetail(UUID.fromString(id));
			vehicleDetailList.add(vehicleDetail);
			genericResponse.setDetails(vehicleDetailList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_vehicle_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/currentVehicle/{driverId}/{vehicleId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update current vehicle")
	public ResponseEntity<TransactionResponse> changePassword(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId, @PathVariable("vehicleId") String vehicleId, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				vehicleService.changeVehicle(UUID.fromString(driverId),UUID.fromString(vehicleId));
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("update_current_vehicle", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/add/{driverId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "add vehicle detail")
	public ResponseEntity<TransactionResponse> saveVehicle(@RequestHeader("authorization") String authorization,@PathVariable("driverId") String driverId,
			@Valid @RequestBody VehicleDetail vehicleDetail, BindingResult bindingResult,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		vehicleDetail.validate(vehicleDetail, bindingResult);
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				Driver driver = driverSerice.getById(UUID.fromString(driverId));
				if (driver == null) {
					throw new Throwable("You are not a registered driver");
				}
				driverVehicleService.saveVehicleDetail(UUID.fromString(driverId), vehicleDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("driver_vehicles_save_success", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/update/{driverId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "add vehicle detail")
	public ResponseEntity<TransactionResponse> updateVehicle(@RequestHeader("authorization") String authorization,@PathVariable("driverId") String driverId,
			@Valid @RequestBody VehicleDetail vehicleDetail, BindingResult bindingResult,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		vehicleDetail.validate(vehicleDetail, bindingResult);
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				Driver driver = driverSerice.getById(UUID.fromString(driverId));
				if (driver == null) {
					throw new Throwable("You are not a registered driver");
				}
				driverVehicleService.updateVehicleDetail(UUID.fromString(driverId), vehicleDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("driver_vehicles_save_success", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/approved/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Approved  vehicle")
	public ResponseEntity<TransactionResponse> approvedVehicle(@RequestHeader("authorization") String authorization,
			@PathVariable("id") String id,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			Vehicle vehicle = vehicleService.getById(UUID.fromString(id));
			vehicle.setApproved(true);
			vehicleService.update(vehicle);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_vehicles_approved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
