package com.alcord.modelmappers;

import java.io.Serializable;

public class LoginDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4232708300719647981L;
	private String accountName;
	private String accountPassword;
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

}
