package com.alcord.controller;

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

import com.alcord.model.VehicleType;
import com.alcord.modelmappers.VehicleTypeDetails;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.VehicleTypeService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author arbin
 */
@RestController
@RequestMapping(value = "/api/v1/vehicleType")
@io.swagger.annotations.Api(value = "vehicleType", description = "Get All VehicleType")
public class VehicleTypeController extends BaseController {
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Save the vehicleType")
	public ResponseEntity<TransactionResponse> saveVehicleType(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleTypeDetails vehicleTypeDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleType vehicleType = new VehicleType();
			vehicleType.setVehicleTypeName(vehicleTypeDetails.getVehicleType());
			vehicleTypeService.save(vehicleType);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_type_save_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update the vehicleType")
	public ResponseEntity<TransactionResponse> updateVehicleColour(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleTypeDetails vehicleTypeDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleType vehicleType = vehicleTypeService.getById(UUID.fromString(vehicleTypeDetails.getId()));
			vehicleType.setVehicleTypeName(vehicleTypeDetails.getVehicleType());
			vehicleTypeService.update(vehicleType);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_type_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all the VehicleType")
	public ResponseEntity<GenericResponse> getAllVehicleColour(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleType> genericResponse = new GenericResponse();
		try {
			List<VehicleType> vehicleTypeList = vehicleTypeService.getAllVehicleType();
			genericResponse.setDetails(vehicleTypeList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_type_all_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "delete the vehicleType ")
	public ResponseEntity<TransactionResponse> deleteVehicleType(@RequestHeader("authorization") String authorization,
			@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			vehicleTypeService.delete(UUID.fromString(id));

			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_type_delete_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
