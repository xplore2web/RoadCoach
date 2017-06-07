/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.modelmappers;

import java.util.UUID;

/**
 *
 * @author ajit
 */
public class AccountDetail {

	private String id;
	private String firstName;
	private String lastName;
	private String accountName;
	private String accountPassword;
	private String accountStatus;
	private String accountRole;
	private String emailAddress;
	private String phone;
	private Double rating;
	private UUID fkAddressId1;
	private UUID fkAddressId2;
	private String badgeNumber;
	private UUID fkeyCityId;
	private UUID fkeyStateId;

	public String getId() {
		return id;
	}

	public void setId(String accountId) {
		this.id = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(String accountRole) {
		this.accountRole = accountRole;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public UUID getFkAddressId1() {
		return fkAddressId1;
	}

	public void setFkAddressId1(UUID fkAddressId1) {
		this.fkAddressId1 = fkAddressId1;
	}

	public UUID getFkAddressId2() {
		return fkAddressId2;
	}

	public void setFkAddressId2(UUID fkAddressId2) {
		this.fkAddressId2 = fkAddressId2;
	}

	public String getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	
	public UUID getFkeyCityId() {
		return fkeyCityId;
	}

	public void setFkeyCityId(UUID fkeyCityId) {
		this.fkeyCityId = fkeyCityId;
	}

	public UUID getFkeyStateId() {
		return fkeyStateId;
	}

	public void setFkeyStateId(UUID fkeyStateId) {
		this.fkeyStateId = fkeyStateId;
	}
	
}
