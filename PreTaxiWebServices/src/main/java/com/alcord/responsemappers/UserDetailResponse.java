/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.responsemappers;

import com.alcord.modelmappers.AccountDetail;

/**
 *
 * @author ajit @ Intbit
 */
public class UserDetailResponse extends BaseResponse {

	private AccountDetail userDetail;

	public UserDetailResponse() {
		super();
	}

	public AccountDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(AccountDetail userDetail) {
		this.userDetail = userDetail;
	}

}
