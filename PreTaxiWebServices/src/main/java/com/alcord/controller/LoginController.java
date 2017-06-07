/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.plexus.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.model.Account;
import com.alcord.modelmappers.AccountActivationKey;
import com.alcord.modelmappers.AccountDetail;
import com.alcord.modelmappers.UserDetail;
import com.alcord.modelmappers.ForgotPassword;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.AccountService;
import com.alcord.service.TripService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Akmal
 */
@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private Environment env;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/signUpUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "sign up the account")
	public ResponseEntity<TransactionResponse> signUpUser(@Valid @RequestBody UserDetail userDetails,
			BindingResult bindingResult,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				accountService.saveUser(userDetails);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("account_created_successfully", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/signUpAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "sign up the account")
	public ResponseEntity<TransactionResponse> signUpUser(@Valid @RequestBody AccountDetail accountDetails,
			BindingResult bindingResult,  HttpServletRequest httpServletRequest) {
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

	@RequestMapping(value = "/accountActivation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Account Activation")
	public ResponseEntity<TransactionResponse> accountActivation(
			@RequestBody AccountActivationKey accountActivationKey,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			Account account = accountService.getAccountByActivationKey(accountActivationKey.getActivationKey());
			account.setActivated(true);
			accountService.update(account);
			transactionResponse.setOperationStatus(ErrorHandlingUtil
					.dataNoErrorValidation(messageSource.getMessage("account_activated", new String[] {}, Locale.US)));

		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil
					.dataErrorValidation(messageSource.getMessage("account_invalid", new String[] {}, Locale.US)));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/appKey", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get App key for login")
	public ResponseEntity<TransactionResponse> getAppKey( HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			String appkey = env.getProperty("authentication.oauth.clientid") + ":"
					+ env.getProperty("authentication.oauth.secret");
			byte[] bytesEncodedAppkey = Base64.encodeBase64(appkey.getBytes());
			String encodeWithBasic = "Basic" + " " + new String(bytesEncodedAppkey);
			byte[] bytesEncodedAppkeyWithBasic = Base64.encodeBase64(encodeWithBasic.getBytes());
			transactionResponse.setId(new String(bytesEncodedAppkeyWithBasic));
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
					messageSource.getMessage("app_key_retrieved_successfully", new String[] {}, Locale.US)));
		} catch (Throwable ex) {
			loggingUtility.error(ex);
			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Forgot password")
	public ResponseEntity<TransactionResponse> changePassword(@RequestHeader("authorization") String authorization,
			@Valid @RequestBody ForgotPassword forgotPassword, BindingResult bindingResult,  HttpServletRequest httpServletRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		forgotPassword.validate(forgotPassword, bindingResult);
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				accountService.sendForgotPasswordMail(forgotPassword.getMobileNumber());
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
						messageSource.getMessage("password_changed_successfully", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
}
