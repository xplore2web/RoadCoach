package com.ajit.controller;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.model.Account;
import com.ajit.utility.LoggingUtility;
import com.ajit.utility.StringUtility;

@RestController
public class BaseController {

	@Autowired
	LoggingUtility loggingUtility;

//	public Account getAccount() {
//		Account loginAccount = SecurityUtils.getCurrentlyLoggedInAccount();
//		return loginAccount;
//	}
//
//	public Integer getAccountId() {
//		return getAccount().getId();
//	}

	public String getDeviceType(HttpServletRequest servletRequest) {
		String deviceType = "";
		Enumeration<String> headersNames = servletRequest.getHeaderNames();

		for (String param : Collections.list(headersNames))
			if (!StringUtility.isEmpty(param) && param.equalsIgnoreCase("X-DeviceType")) {
				deviceType = servletRequest.getHeader("X-DeviceType");
				break;
			}
		return deviceType;
	}

}
