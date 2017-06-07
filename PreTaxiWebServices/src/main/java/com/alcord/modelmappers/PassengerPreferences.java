package com.alcord.modelmappers;

public class PassengerPreferences {
	private String id;
	private String addressId;
	private String passengerId;
	private boolean notification;
	private boolean emergencyContactNotification;
	public PassengerPreferences(){
		
	}
	public PassengerPreferences(String id, String addressId, String passengerId, boolean notification,
			boolean emergencyContactNotification) {
		super();
		this.id = id;
		this.addressId = addressId;
		this.passengerId = passengerId;
		this.notification = notification;
		this.emergencyContactNotification = emergencyContactNotification;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	/**
	 * @return the passengerId
	 */
	public String getPassengerId() {
		return passengerId;
	}
	/**
	 * @param passengerId the passengerId to set
	 */
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	/**
	 * @return the notification
	 */
	public boolean isNotification() {
		return notification;
	}
	/**
	 * @param notification the notification to set
	 */
	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	/**
	 * @return the emergencyContactNotification
	 */
	public boolean isEmergencyContactNotification() {
		return emergencyContactNotification;
	}
	/**
	 * @param emergencyContactNotification the emergencyContactNotification to set
	 */
	public void setEmergencyContactNotification(boolean emergencyContactNotification) {
		this.emergencyContactNotification = emergencyContactNotification;
	}
}
