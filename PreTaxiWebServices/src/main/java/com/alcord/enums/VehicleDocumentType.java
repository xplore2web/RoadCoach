package com.alcord.enums;

public enum VehicleDocumentType {

	RC("RC"), Insurance("Insurance"), CarPermit("CarPermit"), FitnessCertificate("FitnessCertificate"), RoadTax(
			"RoadTax"), Emission("Emission"), SaleLeaseAgreement("SaleLeaseAgreement");

	private String value;

	private VehicleDocumentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
