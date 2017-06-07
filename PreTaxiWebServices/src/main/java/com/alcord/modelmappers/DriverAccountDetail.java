package com.alcord.modelmappers;

public class DriverAccountDetail {
	
	private DriverDetail driverDetail;
	private VehicleDetail vehicleDetail;
	private AddressDetail presentAddress;
	private AddressDetail permanentAddress;
	
	public DriverDetail getDriverDetail() {
		return driverDetail;
	}
	public void setDriverDetail(DriverDetail driverDetail) {
		this.driverDetail = driverDetail;
	}
	public VehicleDetail getVehicleDetail() {
		return vehicleDetail;
	}
	public void setVehicleDetail(VehicleDetail vehicleDetail) {
		this.vehicleDetail = vehicleDetail;
	}
	public AddressDetail getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(AddressDetail presentAddress) {
		this.presentAddress = presentAddress;
	}
	public AddressDetail getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(AddressDetail permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	
    
	
}
