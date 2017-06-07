package com.alcord.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.alcord.model.Account;
import com.alcord.model.AccountRoleMapping;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.responsemappers.GenericResponse;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.AccountRoleMappingService;
import com.alcord.service.AccountService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/admin")
@io.swagger.annotations.Api(value = "Administration", description = "General account related details")
public class AdministrationController extends BaseController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRoleMappingService accountRoleMappingService;
	
	@RequestMapping(value = "/loggedinUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "reset password administration team")
	public ResponseEntity<GenericResponse> getLoggedinUser(HttpServletRequest httpServletRequest) {
		GenericResponse<AccountDetail> genericResponse = new GenericResponse<>();
		try {
			List<AccountDetail> accountList = new ArrayList<>();
			AccountDetail accountDetail = new AccountDetail();
			AccountRoleMapping accountRole = accountRoleMappingService.getByAccountId(getAccount().getId());
			accountDetail.setAccountName(getAccount().getAccountName());
			accountDetail.setAccountRole(accountRole.getFkeyRoleId().getRoleName());
			accountDetail.setEmailAddress(getAccount().getEmailAddress());
			accountDetail.setId(getAccount().getId().toString());
			accountDetail.setPhone(getAccount().getPhone());
			accountList.add(accountDetail);
			genericResponse.setDetails(accountList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("users_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Onboard administration team")
	public ResponseEntity<TransactionResponse> onboardingAdminTeam(@Valid @RequestBody AccountDetail accountDetails,
			BindingResult bindingResult, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				accountService.save(accountDetails);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("account_created_successfully", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Update administration team")
	public ResponseEntity<TransactionResponse> updateAdminTeam(@Valid @RequestBody AccountDetail accountDetails,
			@PathVariable("userId") String userId, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				Account account = accountService.getAccountById(UUID.fromString(userId));
				account = accountService.parseAccountDetailsToAccount(account, accountDetails);
				account.setUpdatedAt(new Date());
				accountService.update(account);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("account_updated_successfully", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/resetPassword/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "reset password administration team")
	public ResponseEntity<TransactionResponse> resetPasswordAdminTeam(@Valid @RequestBody AccountDetail accountDetails,
			@PathVariable("userId") String userId, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				Account account = accountService.getAccountById(UUID.fromString(userId));
				account = accountService.parseAccountDetailsToAccount(account, accountDetails);
				account.setUpdatedAt(new Date());
				accountService.update(account);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("reset_password_successfully", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/users/{team}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "reset password administration team")
	public ResponseEntity<GenericResponse> getUserByTeam(@PathVariable("team") String team,
			HttpServletRequest httpServletRequest) {
		GenericResponse<AccountDetail> genericResponse = new GenericResponse<>();
		try {
			List<AccountDetail> accountRoleList = accountRoleMappingService.getByRole(team);
			genericResponse.setDetails(accountRoleList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("users_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "reset password administration team")
	public ResponseEntity<GenericResponse> getUserByUser(@PathVariable("userId") String userId,
			HttpServletRequest httpServletRequest) {
		GenericResponse<Account> genericResponse = new GenericResponse<>();
		try {
			Account account = accountService.getAccountById(UUID.fromString(userId));
			List<Account> accountList = new ArrayList<>();
			accountList.add(account);
			genericResponse.setDetails(accountList);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("users_retrieved_successfully", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
	}

}
