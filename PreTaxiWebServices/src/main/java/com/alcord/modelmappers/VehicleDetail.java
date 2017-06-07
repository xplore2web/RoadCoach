package com.alcord.modelmappers;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.alcord.utility.ValidationUtility;

public class VehicleDetail implements Serializable, Validator{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6696453656631360056L;
	private String id;
	private String vehicleModel;
	private String vehicleType;
	private String vehicleColour;
	private String engineNumber;
	private String chassisNumber;
	private String registrationNumber;
	private Boolean approved;
	private Integer NumberOfSeat;
	private String brand;
    private String insurancePolicyNumber;
    private String insuranceValidity;
    private String permitNumber;
    private String permitValidity;
	private String driverId;
	private String fitnessValidity;
	private String fitnessCertificateNumber;
	private String vehicleBrand;
	private List<String> vehiclePhoto;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleColour() {
		return vehicleColour;
	}
	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Integer getNumberOfSeat() {
		return NumberOfSeat;
	}
	public void setNumberOfSeat(Integer numberOfSeat) {
		NumberOfSeat = numberOfSeat;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}
	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}
	public String getInsuranceValidity() {
		return insuranceValidity;
	}
	public void setInsuranceValidity(String insuranceValidity) {
		this.insuranceValidity = insuranceValidity;
	}
	public String getPermitNumber() {
		return permitNumber;
	}
	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}
	public String getPermitValidity() {
		return permitValidity;
	}
	public void setPermitValidity(String permitValidity) {
		this.permitValidity = permitValidity;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getFitnessValidity() {
		return fitnessValidity;
	}
	public void setFitnessValidity(String fitnessValidity) {
		this.fitnessValidity = fitnessValidity;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getFitnessCertificateNumber() {
		return fitnessCertificateNumber;
	}
	public void setFitnessCertificateNumber(String fitnessCertificateNumber) {
		this.fitnessCertificateNumber = fitnessCertificateNumber;
  }   
	public List<String> getVehiclePhoto() {
		return vehiclePhoto;
	}
	public void setVehiclePhoto(List<String> vehiclePhoto) {
		this.vehiclePhoto = vehiclePhoto;
	}
	@Override
	public boolean supports(Class<?> type) {
		return VehicleDetail.class.isAssignableFrom(type);
	}
	@Override
	public void validate(Object arg0, Errors errors) {
		if (ValidationUtility.isEmpty(this.vehicleModel)) {
			errors.reject(vehicleModel, "Vehicle model missing");
		}
		if (ValidationUtility.isEmpty(this.vehicleType)) {
			errors.reject(vehicleType, "Vehicle type missing");
		}
		if (ValidationUtility.isEmpty(this.vehicleColour)) {
			errors.reject(vehicleColour, "Vehicle colour missing");
		}
		if (ValidationUtility.isEmpty(this.engineNumber)) {
			errors.reject(engineNumber, "Engine number missing");
		}
		if (ValidationUtility.isEmpty(this.chassisNumber)) {
			errors.reject(chassisNumber, "Chassis number missing");
		}
		if (ValidationUtility.isEmpty(this.registrationNumber)) {
			errors.reject(registrationNumber, "Registration number missing");
		}
		
	}
   
}
