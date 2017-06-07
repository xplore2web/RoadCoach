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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.model.Passenger;
import com.alcord.modelmappers.EmergencyContactDetail;
import com.alcord.modelmappers.PassengerDetail;
import com.alcord.modelmappers.PassengerPreferenceDetail;
import com.alcord.modelmappers.PassengerPreferences;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.PassengerAccountService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/passengerAccount")
@io.swagger.annotations.Api(value = "Passenger Account", description = "All details related to passenger")
public class PassengerAccountController extends BaseController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PassengerAccountService passengerAccountService;

	@RequestMapping(value = "/profile/{passengerId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update passenger profile")
	public ResponseEntity<TransactionResponse> updatePassengerProfile(
			@RequestHeader("authorization") String authorization, @PathVariable("passengerId") String passengerId,
			@RequestBody PassengerDetail passengerDetail, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				passengerDetail.setId(passengerId);
				passengerAccountService.updateProfile(passengerDetail);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("update_passenger_profile", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/detail/{passengerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get account profile of passenger")
	public ResponseEntity<GenericResponse<PassengerDetail>> getPassengerDetailByPassengerId(
			@RequestHeader("authorization") String authorization,
			@PathVariable("passengerId") String passengerId, HttpServletRequest httpServletRequest) {
		GenericResponse<PassengerDetail> genericResponse = new GenericResponse<PassengerDetail>();
		try {
			List<PassengerDetail> passengerDetailList = new ArrayList<>();
			Passenger passenger = passengerAccountService.getPassengerProfile(passengerId);
			PassengerDetail passengerDetail = passengerAccountService.parsePassengerToPassengerDetail(passenger);
			passengerDetailList.add(passengerDetail);
			genericResponse.setDetails(passengerDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("profile_retrieve_success", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Details of the logged in passenger")
	public ResponseEntity<GenericResponse> getPassengerDetail(@RequestHeader("authorization") String authorization,
			HttpServletRequest httpServletRequest) {
		GenericResponse<PassengerDetail> genericResponse = new GenericResponse<PassengerDetail>();
		try {
			List<PassengerDetail> passengerAccountDetailList = new ArrayList<>();
			Passenger passenger = getLoggedInPassenger();
			if (passenger == null) {
				throw new Exception("You are not registered as a passenger. Please download the passenger app");
			}
			PassengerDetail passengerDetail = passengerAccountService.parsePassengerToPassengerDetail(passenger);
			passengerAccountDetailList.add(passengerDetail);
			genericResponse.setDetails(passengerAccountDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("passenger_status_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/preferences/{passengerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get Passenger preferences")
	public ResponseEntity<GenericResponse> getAccountPreferences(@RequestHeader("authorization") String authorization,
			@PathVariable("passengerId") String passengerId, HttpServletRequest httpServletRequest) {
		GenericResponse<PassengerPreferenceDetail> genericResponse = new GenericResponse<PassengerPreferenceDetail>();
		try {
			List<PassengerPreferenceDetail> passengerPreferenceDetailList = passengerAccountService.getAllPassengerPreferenceDetailByPassengerId(UUID.fromString(passengerId));
			genericResponse.setDetails(passengerPreferenceDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("passenger_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
	@RequestMapping(value = "/emergencyContact/{passengerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get emergency contact of passenger")
	public ResponseEntity<GenericResponse<EmergencyContactDetail>> getEmergencyContactByPassengerId(
			@RequestHeader("authorization") String authorization,
			@PathVariable("passengerId") String passengerId, HttpServletRequest httpServletRequest) {
		GenericResponse<EmergencyContactDetail> genericResponse = new GenericResponse<EmergencyContactDetail>();
		try {
			List<EmergencyContactDetail> emergencyContactDetailList = passengerAccountService.getAllEmergencyContactByPassengerId(UUID.fromString(passengerId));
			
			genericResponse.setDetails(emergencyContactDetailList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("passenger_emergency_contact_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/emergencyContact/{passengerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "save emergency contact")
	public ResponseEntity<TransactionResponse> saveEmergencyContact(
			@RequestHeader("authorization") String authorization,
			@RequestBody EmergencyContactDetail emergencyContactDetail, @PathVariable("passengerId") String passengerId,
			HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				emergencyContactDetail.setPassengerId(passengerId);
				passengerAccountService.addEmergencyContact(emergencyContactDetail);

				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("passenger_emergency_contact_save_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/emergencyContact/{passengerId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update emergency contact")
	public ResponseEntity<TransactionResponse> updateEmergencyContact(
			@RequestHeader("authorization") String authorization, @PathVariable("passengerId") String passengerId,
			@RequestBody EmergencyContactDetail emergencyContactDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				emergencyContactDetail.setPassengerId(passengerId);
				passengerAccountService.updateEmergencyContact(emergencyContactDetail);

				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("passenger_emergency_contact_update_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/emergencyContact/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "delete emergency contact")
	public ResponseEntity<TransactionResponse> deleteEmergencyContact(
			@RequestHeader("authorization") String authorization, @PathVariable("id") String id,
			@RequestBody EmergencyContactDetail emergencyContactDetail,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				passengerAccountService.deleteEmergencyContact(UUID.fromString(id));

				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("passenger_emergency_contact_delete_successfully", new String[] {}, Locale.US)));
			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/preferences/{passengerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "save Passenger preferences")
	public ResponseEntity<GenericResponse> addPreferences(@RequestHeader("authorization") String authorization,
			@PathVariable("passengerId") String passengerId, HttpServletRequest httpServletRequest,
			@RequestBody PassengerPreferenceDetail passengerPreferenceDetail) {
		GenericResponse<PassengerPreferences> genericResponse = new GenericResponse<PassengerPreferences>();
		try {
			passengerPreferenceDetail.setPassengerId(passengerId);
			passengerAccountService.addPreferences(passengerPreferenceDetail);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("passenger_preference_save_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
	@RequestMapping(value = "/preferences/{passengerId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "update Passenger preferences")
	public ResponseEntity<GenericResponse> updatePreferences(@RequestHeader("authorization") String authorization,@PathVariable("passengerId") String passengerId,  HttpServletRequest httpServletRequest,@RequestBody PassengerPreferenceDetail passengerPreferenceDetail) {
		GenericResponse<PassengerPreferences> genericResponse = new GenericResponse<PassengerPreferences>();
		try {
			passengerPreferenceDetail.setPassengerId(passengerId);
			passengerAccountService.updatePreferences(passengerPreferenceDetail);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("passenger_preference_update_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

}
