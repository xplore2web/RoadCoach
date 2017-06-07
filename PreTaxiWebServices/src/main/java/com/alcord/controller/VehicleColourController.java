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

import com.alcord.model.VehicleColour;
import com.alcord.modelmappers.VehicleColourDetails;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.VehicleColourService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author arbin
 */
@RestController
@RequestMapping(value = "/api/v1/vehicleColour")
@io.swagger.annotations.Api(value = "vehicleColour", description = "Get All Operation")
public class VehicleColourController extends BaseController {
	@Autowired
	private VehicleColourService vehicleColourService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Save the vehicleColour")
	public ResponseEntity<TransactionResponse> saveVehicleColour(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleColourDetails vehicleColourDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleColour vehicleColour = new VehicleColour();
			vehicleColour.setVehicleColour(vehicleColourDetails.getVehicleColour());
			vehicleColourService.save(vehicleColour);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_colour_save_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update the vehicleColour")
	public ResponseEntity<TransactionResponse> updateVehicleColour(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleColourDetails vehicleColourDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleColour vehicleColour = vehicleColourService.getById(UUID.fromString(vehicleColourDetails.getId()));
			vehicleColour.setVehicleColour(vehicleColourDetails.getVehicleColour());
			vehicleColourService.update(vehicleColour);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_colour_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all the VehicleColour")
	public ResponseEntity<GenericResponse> getAllVehicleColour(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleColour> genericResponse = new GenericResponse();
		try {
			List<VehicleColour> vehicleColourList = vehicleColourService.getAllVehicleColour();
			genericResponse.setDetails(vehicleColourList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_colour_all_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "delete the vehicleColour ")
	public ResponseEntity<TransactionResponse> deleteVehicleColour(@RequestHeader("authorization") String authorization,
			@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			vehicleColourService.delete(UUID.fromString(id));

			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_colour_delete_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
