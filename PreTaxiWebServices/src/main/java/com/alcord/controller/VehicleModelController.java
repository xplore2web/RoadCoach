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

import com.alcord.model.VehicleModel;
import com.alcord.modelmappers.VehicleModelDetails;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.VehicleModelService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author arbin
 */
@RestController
@RequestMapping(value = "/api/v1/vehicleModel")
@io.swagger.annotations.Api(value = "vehicleModel", description = "Get All VehicleModel")
public class VehicleModelController extends BaseController {
	@Autowired
	private VehicleModelService vehicleModelService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Save the vehicleModel")
	public ResponseEntity<TransactionResponse> saveVehicleModel(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleModelDetails vehicleModelDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleModel vehicleModel = new VehicleModel();
			vehicleModel.setVehicleModel(vehicleModelDetails.getVehicleModel());
			vehicleModelService.save(vehicleModel);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_model_save_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update the vehicleModel")
	public ResponseEntity<TransactionResponse> updateVehicleColour(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleModelDetails vehicleModelDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleModel vehicleModel = vehicleModelService.getById(UUID.fromString(vehicleModelDetails.getId()));
			vehicleModel.setVehicleModel(vehicleModelDetails.getVehicleModel());
			vehicleModelService.update(vehicleModel);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_model_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all the vehicleModel")
	public ResponseEntity<GenericResponse> getAllVehicleColour(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleModel> genericResponse = new GenericResponse();
		try {
			List<VehicleModel> vehicleModelList = vehicleModelService.getAllVehicleModel();
			genericResponse.setDetails(vehicleModelList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_model_all_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "delete the vehicleModel ")
	public ResponseEntity<TransactionResponse> deleteVehicleColour(@RequestHeader("authorization") String authorization,
			@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			vehicleModelService.delete(UUID.fromString(id));

			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_model_delete_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
