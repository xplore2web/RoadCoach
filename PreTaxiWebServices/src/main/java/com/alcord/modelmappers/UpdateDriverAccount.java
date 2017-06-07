package com.alcord.modelmappers;

public class UpdateDriverAccount {
	
	private DriverDetail driverDetail;
	private AddressDetail presentAddressDetail;
	private AddressDetail  permanentAddressDetail;
	
	public DriverDetail getDriverDetail() {
		return driverDetail;
	}
	public void setDriverDetail(DriverDetail driverDetail) {
		this.driverDetail = driverDetail;
	}
	public AddressDetail getPresentAddressDetail() {
		return presentAddressDetail;
	}
	public void setPresentAddressDetail(AddressDetail presentAddressDetail) {
		this.presentAddressDetail = presentAddressDetail;
	}
	public AddressDetail getPermanentAddressDetail() {
		return permanentAddressDetail;
	}
	public void setPermanentAddressDetail(AddressDetail permanentAddressDetail) {
		this.permanentAddressDetail = permanentAddressDetail;
	}
	
    	
	

}
