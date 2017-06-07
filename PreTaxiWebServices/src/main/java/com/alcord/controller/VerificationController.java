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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alcord.model.AccountCityStateMapping;
import com.alcord.model.City;
import com.alcord.model.State;
import com.alcord.model.Driver;
import com.alcord.model.DriverVehicleMapping;
import com.alcord.modelmappers.DocumentDataDetail;
import com.alcord.modelmappers.DocumentDetail;
import com.alcord.modelmappers.DriverDetail;
import com.alcord.modelmappers.VehicleDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.CityService;
import com.alcord.service.StateService;
import com.alcord.service.DocumentsService;
import com.alcord.service.DriverService;
import com.alcord.service.DriverVehicleService;
import com.alcord.service.EmailProviderService;
import com.alcord.service.VerificationService;
import com.alcord.utility.ErrorHandlingUtil;
import com.alcord.utility.ParseRequestUtililty;
import com.alcord.utility.RequestValidatorUtility;
import com.alcord.utility.StringUtility;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/verification")
@io.swagger.annotations.Api(value = "Verification", description = "All admin verification details")
public class VerificationController extends BaseController {
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DocumentsService documentsService;

	@Autowired
	private VerificationService verificationService;
	
	@Autowired
	private DriverVehicleService driverVehicleService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	
	@RequestMapping(value = "/addDriver", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Add a driver")
	public ResponseEntity<TransactionResponse> addDriver(@RequestHeader("authorization") String authorization,
			@RequestBody DriverDetail driverDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			verificationService.saveDriver(driverDetail);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_save_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/updateDriver", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update driver")
	public ResponseEntity<TransactionResponse> updateDriver(@RequestHeader("authorization") String authorization,
			@RequestBody DriverDetail driverDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			driverService.updateDriver(driverDetail);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/updateDriverDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Add a driver")
	public ResponseEntity<TransactionResponse> updateDriverDetails(@RequestHeader("authorization") String authorization,
			@RequestBody DriverDetail driverDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			driverService.updateDriver(driverDetail);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("driver_detail_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/addVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Add a vehicle")
	public ResponseEntity<TransactionResponse> addVehicle(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleDetail vehicleDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			UUID driverId = UUID.fromString(vehicleDetail.getDriverId());
			driverVehicleService.saveVehicleDetail(driverId, vehicleDetail);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_save_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/updateVehicle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Add a vehicle")
	public ResponseEntity<TransactionResponse> updateVehicle(@RequestHeader("authorization") String authorization,
			@RequestBody VehicleDetail vehicleDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			UUID driverId = UUID.fromString(vehicleDetail.getDriverId());
			driverVehicleService.updateVehicleDetail(driverId, vehicleDetail);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicle_update_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/list/{stateId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Details of the driver")
	public ResponseEntity<GenericResponse> getDriverList(@RequestHeader("authorization") String authorization, @PathVariable("stateId") UUID stateId, @RequestParam("driverName") String driverName,
														@RequestParam("badgeNumber") String badgeNumber, HttpServletRequest httpServletRequest) {
		GenericResponse<Driver> genericResponse = new GenericResponse<Driver>();
		List<Driver> driverList = new ArrayList<Driver>();
		try {
			if (!(StringUtility.isEmpty(driverName))){
				driverList = driverService.getByName(driverName, stateId);
			}
			if (!(StringUtility.isEmpty(badgeNumber))){
				driverList = driverService.getByBadgeNumber(badgeNumber, stateId);
			}
			
			genericResponse.setDetails(driverList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
		    messageSource.getMessage("drivers_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/vehicleList/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Details of the driver vehicles")
	public ResponseEntity<GenericResponse> getVehicleListByDriverId(
			@RequestHeader("authorization") String authorization, @PathVariable("driverId") UUID driverId, HttpServletRequest httpServletRequest) {
		GenericResponse<VehicleDetail> genericResponse = new GenericResponse<VehicleDetail>();
		List<VehicleDetail> driverList = new ArrayList<VehicleDetail>();
		try {
			driverList = driverVehicleService.getAllDriverVehicleByDriverId(driverId);

			genericResponse.setDetails(driverList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("vehicles_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/uploadDriverDocument/{driverId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update a personal document")
	public ResponseEntity<TransactionResponse> updatePersonalDocument(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId,@RequestParam("file") MultipartFile file,
			@RequestParam("detail") String documentDataDetailString, HttpServletRequest httpServletRequest) {
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
	
	@RequestMapping(value = "/uploadVehicleDocument/{vehicleId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update a vehicle document")
	public ResponseEntity<TransactionResponse> updateVehicleDocument(@RequestHeader("authorization") String authorization,
			@PathVariable("vehicleId") String vehicleId,
			@RequestParam("file") MultipartFile file,
			@RequestParam("detail") String documentDataDetailString, HttpServletRequest httpServletRequest) {
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
	
	@RequestMapping(value = "/driverDocument/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get driver documents")
	public ResponseEntity<GenericResponse> getDriverDocuments(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId, HttpServletRequest httpServletRequest) {
		GenericResponse<DocumentDetail> genericResponse = new GenericResponse<DocumentDetail>();
		List<DocumentDetail> documentDetailList = new ArrayList<DocumentDetail>();
		try {
			DocumentDetail documentDetail = new DocumentDetail();
			documentDetail.setDocuments(documentsService.getDocumentsForDriver(driverId));
			documentDetailList.add(documentDetail);
			genericResponse.setDetails(documentDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
				    messageSource.getMessage("driver_document_retrieve_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/vehicleDocument/{driverId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get driver documents")
	public ResponseEntity<GenericResponse> getVehicleDocuments(@RequestHeader("authorization") String authorization,
			@PathVariable("driverId") String driverId, HttpServletRequest httpServletRequest) {
		GenericResponse<DocumentDetail> genericResponse = new GenericResponse<DocumentDetail>();
		List<DocumentDetail> documentDetailList = new ArrayList<DocumentDetail>();
		try {
			List<DriverVehicleMapping> driverVehicleList = verificationService.getAllVehiclesByDriverId(driverId);
			if (driverVehicleList.size() != 0){
				for (DriverVehicleMapping driverVehicleMapping : driverVehicleList) {
					DocumentDetail documentDetail = new DocumentDetail();
					documentDetail.setDocuments(documentsService.getDocumentsForVehicle(driverVehicleMapping.getFkeyVehicleId().getId().toString()));
					documentDetailList.add(documentDetail);
				}
			}
			
			genericResponse.setDetails(documentDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
				    messageSource.getMessage("vehicle_document_retrieve_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all cities")
	public ResponseEntity<GenericResponse> getAllCities(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<City> genericResponse = new GenericResponse<City>();
		try {
			List<City> cityList = cityService.getAllCities();
			genericResponse.setDetails(cityList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
				    messageSource.getMessage("cities_retrieve_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/states", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get all states")
	public ResponseEntity<GenericResponse> getAllStates(@RequestHeader("authorization") String authorization, HttpServletRequest httpServletRequest) {
		GenericResponse<State> genericResponse = new GenericResponse<State>();
		try {
			List<State> stateList = stateService.getAllStates();
			genericResponse.setDetails(stateList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
				    messageSource.getMessage("states_retrieve_success", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
}
