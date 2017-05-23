/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajit.controller;

import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.model.Account;
import com.ajit.modelmappers.UserLoginDetails;
import com.ajit.responsemappers.OperationStatusType;
import com.ajit.responsemappers.TransactionResponse;
import com.ajit.service.AccountService;
import com.ajit.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Ajit
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
	
//TODO make this call as login
	
	@RequestMapping(value = "/signUpAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "sign up the account")
	public ResponseEntity<TransactionResponse> signUpUser(@Valid @RequestBody UserLoginDetails accountDetails) {
		TransactionResponse transactionResponse = new TransactionResponse();
//		//  transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
//		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				accountService.save(accountDetails);
				transactionResponse
						.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("account_created_successfully", new String[] {}, Locale.US)));

			} catch (Throwable ex) {
				loggingUtility.error(ex);
//				transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
			}
//		}
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}

//	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	@ResponseBody
//	@ApiOperation(value = "Get App key for login")
//	public ResponseEntity<TransactionResponse> forgotPassword() {
//		TransactionResponse transactionResponse = new TransactionResponse();
//		try {
//			String appkey = env.getProperty("authentication.oauth.clientid") + ":"
//					+ env.getProperty("authentication.oauth.secret");
//			byte[] bytesEncodedAppkey = Base64.encodeBase64(appkey.getBytes());
//			String encodeWithBasic = "Basic" + " " + new String(bytesEncodedAppkey);
//			byte[] bytesEncodedAppkeyWithBasic = Base64.encodeBase64(encodeWithBasic.getBytes());
//			transactionResponse.setId(new String(bytesEncodedAppkeyWithBasic));
//			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
//					messageSource.getMessage("app_key_retrieved_successfully", new String[] {}, Locale.US)));
//		} catch (Throwable ex) {
//			loggingUtility.error(ex);
//			transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(ex.getMessage()));
//		}
//		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
//	}

}
