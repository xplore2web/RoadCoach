package com.alcord.controller;

import java.util.Collections;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alcord.model.Account;
import com.alcord.model.AccountRoleMapping;
import com.alcord.model.Driver;
import com.alcord.model.Passenger;
import com.alcord.service.AccountRoleMappingService;
import com.alcord.service.DriverService;
import com.alcord.service.PassengerAccountService;
import com.alcord.utility.LoggingUtility;
import com.alcord.utility.SecurityUtils;
import com.alcord.utility.StringUtility;

@RestController
public class BaseController {

	@Autowired
	LoggingUtility loggingUtility;
	@Autowired
	private DriverService driverService;
	@Autowired
	private PassengerAccountService passengerAccountService;
	@Autowired
	private AccountRoleMappingService accountRoleMappingService;

	public Account getAccount() {
		Account loginAccount = SecurityUtils.getCurrentlyLoggedInAccount();
		return loginAccount;
	}

	public UUID getAccountId() {

		return getAccount().getId();
	}

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

	public Driver getLoggedInDriver() {
		Driver driver = driverService.getByAccountId(getAccountId());
		return driver;
	}

	public Passenger getLoggedInPassenger() {
		Passenger passenger = passengerAccountService.getByAccountId(getAccountId());
		return passenger;
	}

	public String getAccountRole() {
		AccountRoleMapping accountRoleMapping = accountRoleMappingService.getByAccountId(getAccountId());
		return accountRoleMapping.getFkeyRoleId().getRoleName();
	}

}
