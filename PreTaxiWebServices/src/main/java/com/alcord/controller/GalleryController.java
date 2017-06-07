package com.alcord.controller;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alcord.exception.ProcessFailed;
import com.alcord.modelmappers.DocumentDataDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.GalleryService;
import com.alcord.utility.Constant;
import com.alcord.utility.ErrorHandlingUtil;
import com.alcord.utility.ParseRequestUtililty;
import com.alcord.utility.RequestValidatorUtility;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/gallery")
@io.swagger.annotations.Api(value = "Gallery", description = "For All Pictures related")
public class GalleryController extends BaseController {

	@Autowired
	private GalleryService galleryService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/driverProfile/{driverId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update Driver Pic")
	public ResponseEntity<TransactionResponse> updateDriverPic(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId, @RequestParam("file") MultipartFile file,
			@RequestParam("detail") String documentDataDetailString,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {

			DocumentDataDetail documentDataDetail = ParseRequestUtililty.parse(documentDataDetailString);
			RequestValidatorUtility.validateDocumentDetail(documentDataDetail);

			String name = galleryService.updateDriverPicture(driverId, documentDataDetail, file);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_retrieved_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/vehicle/{vehicleId}/{order}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Vehicle Documents")
	public ResponseEntity<TransactionResponse> updateVehiclePicture(
			@RequestHeader("authorization") String authorization, @PathVariable("vehicleId") String vehicleId,
			@PathVariable("order") String orderString, @RequestParam("file") MultipartFile file,
			@RequestParam("detail") String documentDataDetailString,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			DocumentDataDetail documentDataDetail = ParseRequestUtililty.parse(documentDataDetailString);
			RequestValidatorUtility.validateDocumentDetail(documentDataDetail);
			Integer maxLimit = Constant.MAX_PIC_LIMIT - 1;

			try {
				Integer order = Integer.parseInt(orderString);
				if (order < 0 && order > Constant.MAX_PIC_LIMIT) {
					throw new ProcessFailed("Max limit should be 0 to " + maxLimit);
				}
			} catch (Exception ex) {
				loggingUtility.error(ex);
				throw new ProcessFailed("Order should always be 0 - " + maxLimit);
			}

			String name = galleryService.updateVehiclePicture(vehicleId, orderString, documentDataDetail, file);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_update_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/passengerProfile/{passengerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update a personal document")
	public ResponseEntity<TransactionResponse> updatePersonalDocument(
			@RequestHeader("authorization") String authorization, @PathVariable("passengerId") String passengerId,
			@RequestParam("file") MultipartFile file, @RequestParam("detail") String documentDataDetailString,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			DocumentDataDetail documentDataDetail = ParseRequestUtililty.parse(documentDataDetailString);
			RequestValidatorUtility.validateDocumentDetail(documentDataDetail);

			String name = galleryService.updatePassengerProfilePicture(passengerId, documentDataDetail, file);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/driverProfile/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get profile pic of driver")
	public ResponseEntity<TransactionResponse> getDriverProfilePic(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {

			String name = galleryService.getDriverProfilePic(driverId);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/passengerProfile/{passengerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get profile pic of passenger")
	public ResponseEntity<TransactionResponse> getPassengerProfilePic(
			@RequestHeader("authorization") String authorization, @PathVariable("passengerId") String passengerId,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {

			String name = galleryService.getPassengerProfilePic(passengerId);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/vehicle/{vehicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get vehicle pictures")
	public ResponseEntity<GenericResponse<String>> getVehiclePictures(
			@RequestHeader("authorization") String authorization, @PathVariable("vehicleId") String vehicleId,  HttpServletRequest httpServletRequest) {
		GenericResponse<String> genericResponse = new GenericResponse<String>();
		try {

			List<String> pictureList = galleryService.getVehiclePictures(vehicleId);
			genericResponse.setDetails(pictureList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_retrieved_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

}
