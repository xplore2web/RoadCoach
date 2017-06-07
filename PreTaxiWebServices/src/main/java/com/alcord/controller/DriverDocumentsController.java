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

import com.alcord.modelmappers.DocumentDataDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.DocumentsService;
import com.alcord.utility.ErrorHandlingUtil;
import com.alcord.utility.ParseRequestUtililty;
import com.alcord.utility.RequestValidatorUtility;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/driver/documents")
@io.swagger.annotations.Api(value = "Documents", description = "Get all documents related to driver details")
public class DriverDocumentsController extends BaseController {

	@Autowired
	private DocumentsService documentsService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/personal/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all driver documents")
	public ResponseEntity<GenericResponse> personalDocuments(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId,  HttpServletRequest httpServletRequest) {
		GenericResponse<DocumentDataDetail> genericResponse = new GenericResponse<>();
		try {
			List<DocumentDataDetail> driverDocumentDetailList = documentsService.getDocumentsForDriver(driverId);
			genericResponse.setDetails(driverDocumentDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_retrieved_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/vehicle/{vehicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Vehicle Documents")
	public ResponseEntity<GenericResponse> getDocumentsForVehicle(@RequestHeader("authorization") String authorization,
			@PathVariable("vehicleId") String vehicleId,  HttpServletRequest httpServletRequest) {
		GenericResponse<DocumentDataDetail> genericResponse = new GenericResponse<DocumentDataDetail>();
		try {
			List<DocumentDataDetail> vehicleDocumentDetailList = documentsService.getDocumentsForVehicle(vehicleId);
			genericResponse.setDetails(vehicleDocumentDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_retrieved_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/personal/{driverId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update a personal document")
	public ResponseEntity<TransactionResponse> updatePersonalDocument(
			@RequestHeader("authorization") String authorization, @PathVariable("driverId") String driverId,
			@RequestParam("file") MultipartFile file, @RequestParam("detail") String documentDataDetailString,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			DocumentDataDetail documentDataDetail = ParseRequestUtililty.parse(documentDataDetailString);
			RequestValidatorUtility.validateDocumentDetail(documentDataDetail);

			String name = documentsService.updatePersonalDocument(driverId, documentDataDetail.getType(),
					documentDataDetail, file);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/vehicle/{vehicleId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update a vehicle document")
	public ResponseEntity<TransactionResponse> updateVehicleDocument(
			@RequestHeader("authorization") String authorization, @PathVariable("vehicleId") String vehicleId,
			@RequestParam("file") MultipartFile file, @RequestParam("detail") String documentDataDetailString,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {

			DocumentDataDetail documentDataDetail = ParseRequestUtililty.parse(documentDataDetailString);
			RequestValidatorUtility.validateDocumentDetail(documentDataDetail);

			String name = documentsService.updateVehicleDocument(vehicleId, documentDataDetail.getType(),
					documentDataDetail, file);
			transactionResponse.setId(name);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("document_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

}
