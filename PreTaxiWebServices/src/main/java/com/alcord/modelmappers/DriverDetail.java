package com.alcord.modelmappers;

import java.util.List;

public class DriverDetail {
	private String id;
	private String accountId;
	private String firstName;
	private String lastName;
	private List<AddressDetail> address;
	private String photo;
	private Double rating;
	private String currentStatus;
	private Double currentPoint;
	private String phone;
	private String badgeNumber;
	private String emailAddress;
	private String password;
	private String cityId;
	private String stateId;
	private String panNumber;
	private Long bankAccountNumber;
	private String ifscCode;
	private String driverLicenseNumber;
	private String policeClearanceSerialId;
	private String driverLicenseValidity;
	private String passportNumber;
	private String passportValidity;
	private String voterId;
	private String aadharNumber;
	
	public String getDriverLicenseValidity() {
		return driverLicenseValidity;
	}
	public void setDriverLicenseValidity(String driverLicenseValidity) {
		this.driverLicenseValidity = driverLicenseValidity;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getPassportValidity() {
		return passportValidity;
	}
	public void setPassportValidity(String passportValidity) {
		this.passportValidity = passportValidity;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPoliceClearanceSerialId() {
		return policeClearanceSerialId;
	}
	public void setPoliceClearanceSerialId(String policeClearanceSerialId) {
		this.policeClearanceSerialId = policeClearanceSerialId;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public Long getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(Long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBadgeNumber() {
		return badgeNumber;
	}
	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Double getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(Double currentPoint) {
		this.currentPoint = currentPoint;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AddressDetail> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDetail> address) {
		this.address = address;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}
}
