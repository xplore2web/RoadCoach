package com.alcord.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.responsemappers.TransactionResponse;
import com.alcord.utility.ErrorHandlingUtil;
import com.alcord.utility.StringUtility;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Akmal
 */
@RestController
@RequestMapping(value = "/api/v1/oauth")
@io.swagger.annotations.Api(value = "OAuth", description = "OAuth related services")
public class OAuthController extends BaseController {

	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/revoke-token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Logout")
	public ResponseEntity<TransactionResponse> logout(@RequestHeader("authorization") String authorization) {
		TransactionResponse transactionResponse = new TransactionResponse();
		if (!StringUtility.isEmpty(authorization)) {
			String tokenValue = authorization.replace("Bearer", "").trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
		}
		transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(
				messageSource.getMessage("logged_out_successfully", new String[] {}, Locale.US)));
		return new ResponseEntity<>(transactionResponse, HttpStatus.ACCEPTED);
	}
}
