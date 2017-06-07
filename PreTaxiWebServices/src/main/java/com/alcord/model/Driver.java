/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author ajit
 */
@Entity
@Table(name = "driver")
public class Driver implements Serializable {




	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "id")
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@NotNull
	private UUID id;
	@Size(max = 50)
	@Column(name = "first_name")
	private String firstName;
	@Size(max = 50)
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "rating")
	private Double rating;
	@Size(max = 100)
	@Column(name = "current_status")
	private String currentStatus;
	@JoinColumn(name = "fkey_account_id", referencedColumnName = "id")
	@ManyToOne
	private Account fkeyAccountId;
	@JoinColumn(name = "fkey_present_address_id", referencedColumnName = "id")
	@ManyToOne
	private Address fkeyPresentAddressId;
	@JoinColumn(name = "fkey_permanent_address_id", referencedColumnName = "id")
	@ManyToOne
	private Address fkeyPermanentAddressId;
	@JoinColumn(name = "fkey_state_id", referencedColumnName = "id")
	@ManyToOne
	private State fkeyStateId;
	@Size(max = 50)
	@Column(name = "badge_number")
	private String badgeNumber;
	@Size(max = 50)
	@Column(name = "driver_license_number")
	private String driverLicenseNumber;
	@Size(max = 50)
	@Column(name = "pan_number")
	private String panNumber;
	@Size(max = 50)
	@Column(name = "ifsc_code")
	private String ifscCode;
	@Size(max = 50)
	@Column(name = "police_clearance_serial_id")
	private String policeClearanceSerialId;	
	@Size(max = 50)
	@Column(name = "driver_license_validity")
	private String driverLicenseValidity;
	@Size(max = 50)
	@Column(name = "passport_number")
	private String passportNumber;
	@Size(max = 50)
	@Column(name = "passport_validity")
	private String passportValidity;
	@Size(max = 50)
	@Column(name = "voter_id")
	private String voterId;
	@Size(max = 50)
	@Column(name = "aadhar_number")
	private String aadharNumber;
    @Column(name = "bank_account_number")
    private BigInteger bankAccountNumber;

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

	public Driver() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	
	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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

	public Account getFkeyAccountId() {
		return fkeyAccountId;
	}

	public void setFkeyAccountId(Account fkeyAccountId) {
		this.fkeyAccountId = fkeyAccountId;
	}

	public Address getFkeyPresentAddressId() {
		return fkeyPresentAddressId;
	}

	public void setFkeyPresentAddressId(Address fkeyPresentAddressId) {
		this.fkeyPresentAddressId = fkeyPresentAddressId;
	}

	public Address getFkeyPermanentAddressId() {
		return fkeyPermanentAddressId;
	}

	public void setFkeyPermanentAddressId(Address fkeyPermanentAddressId) {
		this.fkeyPermanentAddressId = fkeyPermanentAddressId;
	}

	public State getFkeyStateId() {
		return fkeyStateId;
	}

	public void setFkeyStateId(State fkeyStateId) {
		this.fkeyStateId = fkeyStateId;
	}
	
	public String getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

    public BigInteger getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(BigInteger bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


}
