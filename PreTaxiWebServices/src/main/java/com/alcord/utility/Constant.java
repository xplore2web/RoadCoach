package com.alcord.utility;

public interface Constant {

	// Account Role
	Integer ROLE_ADMIN = 1;
	Integer ROLE_DRIVER = 2;
	Integer ROLE_PASSENGER = 3;
	Integer ROLE_VERIFICATION_SUPERVISER = 4;
	Integer ROLE_VERIFICATION_ANALYST = 5;
	Integer ROLE_ACCOUNTS_SUPERVISER = 6;
	Integer ROLE_ACCOUNTS_ANALYST = 7;

	//Documents
	// 2 months
	Integer MINIMUM_VALIDITY = 6;
	String UNEXPIRED_DATE = "12-12-2999";

	// Max Pictures
	Integer MAX_PIC_LIMIT = 5;
}
