/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.responsemappers;

import com.alcord.modelmappers.DriverStatus;

/**
 *
 * @author ajit @ alcord
 */
public class DriverStatusResponse extends BaseResponse {

	private DriverStatus driverStatus;

	public DriverStatusResponse() {
		super();
	}

	public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}

}
