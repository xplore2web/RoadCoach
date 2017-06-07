package com.alcord.enums;

public enum DriverDocumentType {

	BankPassBook("BankPassBook"), Check("Check"), IFSCCode("IFSCCode"), PANCard("PANCard"), DrivingLicense(
			"DrivingLicense"), AadharCard("AadharCard"), Badge("Badge"), AddressProof("AddressProof"),
	VoterID("VoterID"), PoliceClearanceCertificate("PoliceClearanceCertificate"),
	NoObjectionCertificate("NoObjectionCertificate"), DLFRONT("DLFRONT"), DLBACK("DLBACK"), Passport("Passport");

	private String value;

	private DriverDocumentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
