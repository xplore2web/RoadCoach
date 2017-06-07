package com.alcord.modelmappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GCM {
	private Notification notification;

	@JsonProperty("notification")
	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
}