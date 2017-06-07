package com.alcord.modelmappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
	private String text;

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}