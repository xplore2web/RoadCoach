package com.alcord.enums;

public enum DeviceType {
	Android ("Android"),
	iOS("iOS");
	
	private String value;

	DeviceType(String displayName){
		this.value = displayName;
	}
	
	public String getDisplayName(){
		return value;
	}
}
