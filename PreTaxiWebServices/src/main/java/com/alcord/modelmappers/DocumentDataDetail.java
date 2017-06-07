package com.alcord.modelmappers;

public class DocumentDataDetail {
	private String id;
	private String name;
	private String value;
	private String type;
	private String location;
	private String validity;
	private String additionalData;
	private VehicleDetail vehicleDetails;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	public VehicleDetail getVehicleDetails() {
		return vehicleDetails;
	}
	public void setVehicleDetails(VehicleDetail vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	
}
