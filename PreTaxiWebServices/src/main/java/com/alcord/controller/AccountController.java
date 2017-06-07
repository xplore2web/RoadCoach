package com.alcord.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

import com.alcord.modelmappers.ChangePasswordDetail;
import com.alcord.responsemappers.OperationStatusType;
import com.alcord.responsemappers.TransactionResponse;
import com.alcord.service.AccountService;
import com.alcord.utility.ErrorHandlingUtil;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author ajit
 */
@RestController
@RequestMapping(value = "/api/v1/account")
@io.swagger.annotations.Api(value = "Account", description = "General account related details")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/changePassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Change password")
	public ResponseEntity<TransactionResponse> changePassword(@RequestHeader("authorization") String authorization,
			
			@Valid @RequestBody ChangePasswordDetail changePassword, BindingResult bindingResult,HttpServletRequest httpServletRequest)	
	{

		TransactionResponse transactionResponse = new TransactionResponse();
		changePassword.validate(changePassword, bindingResult);
		transactionResponse.setOperationStatus(ErrorHandlingUtil.requestErrorValidation(bindingResult));
		if (transactionResponse.getOperationStatus().getStatusCode() == OperationStatusType.Success) {
			try {
				accountService.changePassword(changePassword);
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
