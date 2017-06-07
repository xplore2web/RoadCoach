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

import com.alcord.model.VehicleBrand;
import com.alcord.modelmappers.VehicleBrandDetails;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.VehicleBrandService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author arbin
 */
@RestController
@RequestMapping(value = "/api/v1/vehicleBrand")
@io.swagger.annotations.Api(value = "vehicleBrand", description = "Get All VehicleBrand")
public class VehicleBrandController extends BaseController {
	@Autowired
	private VehicleBrandService vehicleBrandService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Save the vehicleBrand")
	public ResponseEntity<TransactionResponse> saveVehicleModel(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleBrandDetails vehicleBrandDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleBrand vehicleBrand = new VehicleBrand();
			vehicleBrand.setVehicleBrand(vehicleBrandDetails.getVehicleBrand());
			vehicleBrandService.save(vehicleBrand);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_brand_save_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update the vehicleBrand")
	public ResponseEntity<TransactionResponse> updateVehicleColour(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleBrandDetails vehicleBrandDetails, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			VehicleBrand vehicleBrand = vehicleBrandService.getById(UUID.fromString(vehicleBrandDetails.getId()));
			vehicleBrand.setVehicleBrand(vehicleBrandDetails.getVehicleBrand());
			vehicleBrandService.update(vehicleBrand);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_brand_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all the vehicleBrand")
	public ResponseEntity<GenericResponse> getAllVehicleColour(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleBrand> genericResponse = new GenericResponse();
		try {
			List<VehicleBrand> vehicleBrandList = vehicleBrandService.getAllVehicleBrand();
			genericResponse.setDetails(vehicleBrandList);

			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_brand_all_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "delete the vehicleBrand ")
	public ResponseEntity<TransactionResponse> deleteVehicleColour(@RequestHeader("authorization") String authorization,
			@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			vehicleBrandService.delete(UUID.fromString(id));

			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_brand_delete_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}

		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
